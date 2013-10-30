##
# @copyright Copyright (C) DocuSign, Inc.  All rights reserved.
#
# This source code is intended only as a supplement to DocuSign SDK
# and/or on-line documentation.
# 
# This sample is designed to demonstrate DocuSign features and is not intended
# for production use. Code and policy for a production application must be
# developed to meet the specific data and security requirements of the
# application.
#
# THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
# KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
# IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
# PARTICULAR PURPOSE.
#
 
class SendTemplateController < ApplicationController
  attr_accessor :templates, :signers, :template_id, :token

  def create
    if params[:event].eql?('Send')
      render "get_templates"
    elsif logged_in?
      #Request the templates from the account
      begin
        @templates = ds_connection.requestTemplates(:accountID => session[:account_id], :includeAdvancedTemplates => true ).requestTemplatesResult
        render "get_template"
      rescue Exception =>e
        redirect_to :controller => 'error', :action => 'show', :message => e.message and return
      end
    else
      redirect_to root_path
    end
  end

  def get_template
    if params[:LoadTemplate].eql?('Select Template')
      @template_id = params[:tname][:tid]

      #Request a particular template from the account
      begin
        template = ds_connection.requestTemplate(:templateID => @template_id, :includeDocumentBytes => false).requestTemplateResult
      rescue Exception =>e
        redirect_to :controller => 'error', :action => 'show', :message => e.message and return
      end

      #Construct the recipient information to display during envelope customization
      recipients = template.envelope.recipients
      @name = template.envelopeTemplateDefinition.name
      @signers = Array.new
      if !recipients.nil?
        recipients.each do |r|
          access_code = ""
          security = "None"
          if !r.accessCode.empty?
            security = "Access Code"
            access_code = r.accessCode
          end
          if r.requireIDLookup
            security = "ID Check"
          end
          if !r.phoneAuthentication.nil?
            security = "Phone Authentication"
          end
          signer = Recipient.new(r.userName, r.email, r.iD, false, security, access_code, r.roleName)
          @signers << signer
        end
        render "create"
      end

    end
  end

  def send_template
    #Create the envelope with custom parameters
    @envelope = Envelope.new(params)

    #Determine if the user wants the embedded sending experience
    if params.has_key?('SendNow')
      @send = true
    elsif params.has_key?('EditFirst')
      @send = false
    end

    #Create the envelope information
    @envelope.fill_envelope_info

    #Create the recipients that the user specified
    @envelope.create_custom_recipients(params)

    #Create the arrays we will need for storing template information
    template_references = Array.new
    role_assignments = Array.new

    #Map the recipients to the template roles
    @envelope.recipients.each do |r|
      role_assignment = Docusign::TemplateReferenceRoleAssignment.new.tap do |ra|
        ra.role_name = r.roleName
        ra.recipient_id = r.iD
      end
      role_assignments << role_assignment
    end

    #Create one template reference for each template we are using
    template_reference = Docusign::TemplateReference.new.tap do |tr|
      tr.template_location = Docusign::TemplateLocationCode.new("Server")
      tr.template = params['template_id']
      tr.role_assignments = role_assignments
    end
    template_references << template_reference

    #Fill out the envelope information from the object
    envelope_info = Docusign::EnvelopeInformation.new.tap do |ei|
      ei.subject = @envelope.subject
      ei.email_blurb = @envelope.email_blurb
      ei.notification = @envelope.notification
      ei.account_id = session[:account_id]
    end

    if @send && logged_in?
      #Create and send the envelope
      begin
        status = ds_connection.createEnvelopeFromTemplates(:templateReferences => template_references, :recipients => @envelope.recipients,
                                                              :envelopeInformation => envelope_info, :activateEnvelope => true).createEnvelopeFromTemplatesResult
      rescue Exception =>e
        redirect_to :controller => 'error', :action => 'show', :message => e.message and return
      end

      #If the envelope has been sent successfully, add the id to the session's list
      if session[:envelope_ids].nil?
        session[:envelope_ids] = status.envelopeID
      else
        session[:envelope_ids] = session[:envelope_ids] +"," + status.envelopeID
      end

      redirect_to get_status_and_docs_path

    elsif !@send && logged_in?
      #Create the envelope, but don't send it
      begin
        status = ds_connection.createEnvelopeFromTemplates(:templateReferences => template_references, :recipients => @envelope.recipients,
                                                              :envelopeInformation => envelope_info, :activateEnvelope => false).createEnvelopeFromTemplatesResult
      rescue Exception =>e
        redirect_to :controller => 'error', :action => 'show', :message => e.message and return
      end

      #If the envelope has been created successfully, add the id to the session's list
      if session[:envelope_ids].nil?
        session[:envelope_ids] = status.envelopeID
      else
        session[:envelope_ids] = session[:envelope_ids] +"," + status.envelopeID
      end

      #Get the URL that will allow the sender to edit the envelope before sending
      begin
        @token = ds_connection.requestSenderToken(:accountID => session[:account_id], :envelopeID => status.envelopeID,
                                                    :returnURL => root_url+'pop.html?source=template').requestSenderTokenResult

        render 'edit'
      rescue Exception =>e
        redirect_to :controller => 'error', :action => 'show', :message => e.message and return
      end
    else
      redirect_to root_path
    end
  end
end
