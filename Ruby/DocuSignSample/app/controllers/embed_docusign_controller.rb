class EmbedDocusignController < ApplicationController
  require 'time'
  attr_accessor :token1, :token2
  def new
    #If we have finished signing for the first recipient, go to the next one if necessary
    if params['status'].eql?('signing_complete1') && session[:recipientCount] > 1
      sign_second
      #If there is no second signer, or if the second signer has finished signing, redirect to the status page
    elsif (params['status'].eql?('signing_complete1') && session[:recipientCount] == 1) ||
        params['status'].eql?('signing_complete2') || !params['status'].nil?
      session[:status] = nil
      redirect_to get_status_and_docs_path
    else
      render "create"
    end
  end

  def create
    #Create the envelope with no custom parameters
    @e = Envelope.new(nil)

    if !params['OneSigner'].nil?
      #Create a standard envelope with only one signer, using the logged in user as the recipient
      @e.create_documents_and_tabs(1)
      @e.create_default_recipients(1, session[:email])
    else
      #Create a standard envelope with two signers, using the logged in user as one recipient
      @e.create_documents_and_tabs(2)
      @e.create_default_recipients(2, session[:email])
    end

    if logged_in?
      #Fill out the envelope information
      @envelope = Docusign::Envelope.new.tap do |env|
        env.accountId = session[:account_id]
        env.documents = @e.documents
        env.recipients = @e.recipients
        env.tabs = @e.tabs
        env.subject = "DocuSign API SDK Example"
        env.emailBlurb = "This envelope demonstrates embedded signing"
      end

      #Send the envelope
      begin
        status = ds_connection.createAndSendEnvelope(:envelope => @envelope).createAndSendEnvelopeResult
      rescue Exception =>e
        redirect_to :controller => 'error', :action => 'show', :message => e.message and return
      end

      #Store the status variable in the session briefly
      session[:status] = status
      session[:recipientCount] = status.recipientStatuses.length

      #If the envelope has been sent successfully, add the id to the session's list
      if status.status == Docusign::EnvelopeStatusCode::Sent
        if session[:envelope_ids].nil?
          session[:envelope_ids] = status.envelopeID
        else
          session[:envelope_ids] = session[:envelope_ids] +"," + status.envelopeID
        end
        sign_first
      end
    else
      redirect_to root_path
    end
  end

  def sign_first

    @message = "Have the first signer fill out the Envelope"

    #Create the assertion using the current time, HTTP authentication and the demo information
    assertion = Docusign::RequestRecipientTokenAuthenticationAssertion.new.tap do |a|
      a.assertionID           = Time.now.to_i.to_s
      a.authenticationInstant = Time.now.iso8601
      a.authenticationMethod  = Docusign::RequestRecipientTokenAuthenticationAssertionAuthenticationMethod::HTTPBasicAuth
      a.securityDomain        = "#{request.domain}:80"
    end

    #Create the clientURLs to which the iframe will redirect upon every event
    urls = Docusign::RequestRecipientTokenClientURLs.new.tap do |u|
      u.onViewingComplete = root_url + 'pop.html?source=embed&status=viewing_complete1'
      u.onSigningComplete = root_url + 'pop.html?source=embed&status=signing_complete1'
      u.onCancel = root_url + 'pop.html?source=embed&status=cancel1'
      u.onDecline = root_url + 'pop.html?source=embed&status=decline1'
      u.onSessionTimeout = root_url + 'pop.html?source=embed&status=session_timeout1'
      u.onTTLExpired = root_url + 'pop.html?source=embed&status=ttl_expired1'
      u.onException = root_url + 'pop.html?source=embed&status=exception1'
      u.onAccessCodeFailed = root_url + 'pop.html?source=embed&status=access_code_failed1'
      u.onIdCheckFailed = root_url + 'pop.html?source=embed&status=id_check_failed1'
    end

    #Get the URL that will be the src of the iframe
    if logged_in?
      begin
        @token = ds_connection.requestRecipientToken(:envelopeID => session[:status].envelopeID,
                                                       :clientUserID => session[:status].recipientStatuses[0].clientUserId,
                                                       :username => session[:status].recipientStatuses[0].userName,
                                                       :email => session[:status].recipientStatuses[0].email,
                                                       :authenticationAssertion => assertion,
                                                       :clientURLs => urls
        ).requestRecipientTokenResult
        render "sign"
      rescue Exception =>e
        redirect_to :controller => 'error', :action => 'show', :message => e.message and return
      end
    else
      redirect_to root_path
    end
  end

  def sign_second

    @message = "The first signer has completed the Envelope. Now the second signer will be asked to fill out details in the Envelope."

    #Create the assertion using the current time, HTTP authentication and the demo information
    assertion = Docusign::RequestRecipientTokenAuthenticationAssertion.new.tap do |a|
      a.assertion_id           = Time.now.to_i.to_s
      a.authentication_instant = Time.now
      a.authentication_method  = Docusign::RequestRecipientTokenAuthenticationAssertionAuthenticationMethod::HTTPBasicAuth
      a.security_domain        = "#{request.domain}:80"
    end

    #Create the clientURLs to which the iframe will redirect upon every event
    urls = Docusign::RequestRecipientTokenClientURLs.new.tap do |u|
      u.onViewingComplete = root_url + 'pop.html?source=embed&status=viewing_complete2'
      u.onSigningComplete = root_url + 'pop.html?source=embed&status=signing_complete2'
      u.onCancel = root_url + 'pop.html?source=embed&status=cancel2'
      u.onDecline = root_url + 'pop.html?source=embed&status=decline2'
      u.onSessionTimeout = root_url + 'pop.html?source=embed&status=session_timeout2'
      u.onTTLExpired = root_url + 'pop.html?source=embed&status=ttl_expired2'
      u.onException = root_url + 'pop.html?source=embed&status=exception2'
      u.onAccessCodeFailed = root_url + 'pop.html?source=embed&status=access_code_failed2'
      u.onIdCheckFailed = root_url + 'pop.html?source=embed&status=id_check_failed2'
    end

    #Get the URL that will be the src of the iframe
    if logged_in?
      begin
        @token = ds_connection.requestRecipientToken(:envelopeID => session[:status].envelopeID,
                                                       :clientUserID => session[:status].recipientStatuses[1].clientUserId,
                                                       :username => session[:status].recipientStatuses[1].userName,
                                                       :email => session[:status].recipientStatuses[1].email,
                                                       :authenticationAssertion => assertion,
                                                       :clientURLs => urls
        ).requestRecipientTokenResult
        render "sign"
      rescue Exception =>e
        redirect_to :controller => 'error', :action => 'show', :message => e.message
      end
    end
  end
end
