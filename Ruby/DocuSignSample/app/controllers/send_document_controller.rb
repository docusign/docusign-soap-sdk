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

class SendDocumentController < ApplicationController
  attr_accessor :token

  def create
    render "create"
  end

  def send_envelope
    #Create the envelope with custom parameters
    @e = Envelope.new(params)

    #Determine if the user wants the embedded sending experience
    if params.has_key?('SendNow')
      @send = true
    elsif params.has_key?('EditFirst')
      @send = false
    end

    #Create the recipients that the user specified
    @e.create_custom_recipients(params)

    #Create the documents and tabs using the options and files that the user specified
    @e.create_documents_and_tabs(@e.recipients.length)

    #Fill out the envelope information
    envelope = Docusign::Envelope.new.tap do |env|
      env.accountId = session[:account_id]
      env.documents = @e.documents
      env.recipients = @e.recipients
      env.tabs = @e.tabs
      env.subject = @e.subject
      env.emailBlurb = @e.email_blurb
      if !@e.notification.nil?
        env.notification = @e.notification
      end
      if @e.markup
        env.allowMarkup = true
      end
      if @e.paper
        env.enableWetSign = true
      end
    end

    if @send && logged_in?
      #Create and send the envelope
      begin
        status = ds_connection.createAndSendEnvelope(:envelope => envelope).createAndSendEnvelopeResult
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
        status = ds_connection.createEnvelope(:envelope => envelope).createEnvelopeResult
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
                                                    :returnURL => root_url+'pop.html?source=document').requestSenderTokenResult

        render 'edit'
      rescue Exception =>e
        redirect_to :controller => 'error', :action => 'show', :message => e.message and return
      end
    else
      redirect_to root_path
    end
  end
end
