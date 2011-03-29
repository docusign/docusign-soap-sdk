class GetStatusAndDocsController < ApplicationController
  attr_accessor :envelopes, :token

  def show
    params.each do |k, v|
      if v.eql?('Download')
        eid = k.split('+')[1]
        if logged_in?
          #Request the PDF
          begin
            @file = ds_connection.request_pdf(:envelopeID => eid).request_pdf_result
            @path = Rails.root.to_s + "/tmp/" + eid +".pdf"
            File.open(@path, 'w+') do |f|
              #There is a bug in Soap4R that double 64 encodes the bytes
              f.write Base64.decode64(Base64.decode64(@file.pDFBytes))
            end

            #Send the resulting file to download
            send_file(@path, :type=>"application/pdf", :x_sendfile=>true) and return
          rescue Exception =>e
            redirect_to :controller => 'error', :action => 'show', :message => e.message and return
          end
        else
          redirect_to root_path
        end
      elsif v.eql?('Start Signing')
        #The user wants to start the signing process for a captive signer
        signing_params = k.split('&')
        uname = signing_params[2].split('+')[1]
        cid = signing_params[3].split('+')[1]
        email = signing_params[1].split('+')[1]
        eid = signing_params[0].split('+')[1]

        start_signing(uname, cid, email, eid) and return
      end
    end
  end

  def start_signing(username, clientid, email, envelopeid)

    #Create the assertion using the current time, HTTP authentication and the demo information
    assertion = Docusign::RequestRecipientTokenAuthenticationAssertion.new.tap do |a|
      a.assertion_id           = Time.now.to_i.to_s
      a.authentication_instant = Time.now
      a.authentication_method  = Docusign::RequestRecipientTokenAuthenticationAssertionAuthenticationMethod::HTTPBasicAuth
      a.security_domain        = "#{request.domain}:80"
    end

    #Create the clientURLs to which the iframe will redirect upon every event
    urls = Docusign::RequestRecipientTokenClientURLs.new.tap do |u|
      u.onViewingComplete = root_url + 'pop.html?status=viewing_complete'
      u.onSigningComplete = root_url + 'pop.html?status=signing_complete'
      u.onCancel = root_url + 'pop.html?status=cancel'
      u.onDecline = root_url + 'pop.html?status=decline'
      u.onSessionTimeout = root_url + 'pop.html?status=session_timeout'
      u.onTTLExpired = root_url + 'pop.html?status=ttl_expired'
      u.onException = root_url + 'pop.html?status=exception'
      u.onAccessCodeFailed = root_url + 'pop.html?status=access_code_failed'
      u.onIdCheckFailed = root_url + 'pop.html?status=id_check_failed'
    end

    #Get the URL that will be the src of the iframe
    if logged_in?
      begin
        @token = ds_connection.request_recipient_token(:envelopeID => envelopeid,
                                                       :clientUserID => clientid,
                                                       :username => username,
                                                       :email => email,
                                                       :authenticationAssertion => assertion,
                                                       :clientURLs => urls
        ).request_recipient_token_result
        render "sign"
      rescue Exception =>e
        redirect_to :controller => 'error', :action => 'show', :message => e.message and return
      end
    else
      redirect_to root_path
    end
  end

  def list
    if !session[:envelope_ids].nil? && logged_in?
      #Create a filter to specify the envelopes we have created during this session
      filter = Docusign::EnvelopeStatusFilter.new.tap do |f|
        f.envelopeIds = session[:envelope_ids].split(',')
        f.accountId = session[:account_id]
      end

      #Request these envelopes' information
      begin
        @envelopes = ds_connection.request_statuses_ex(:envelopeStatusFilter => filter).requestStatusesExResult.envelopeStatuses
        render "list"
      rescue Exception =>e
        redirect_to :controller => 'error', :action => 'show', :message => e.message and return
      end
    elsif session[:envelope_ids].nil?
      render "list"
    else
      redirect_to root_path
    end
  end
end
