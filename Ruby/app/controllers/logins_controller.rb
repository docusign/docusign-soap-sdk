class LoginsController < ApplicationController

  attr_accessor :email, :int_key, :password

  def new
    #Clear stale session variables
    session[:email] = nil
    session[:password] = nil
    session[:int_key] = nil
    session[:account_id] = nil
    session[:logged_in] = nil
    session[:recipientCount] = nil #TODO:Get rid of this one altogether
    session[:status] = nil
    session[:envelope_ids] = nil

    #Read as much from the configuration file as possible
    @int_key =  Docusign::Config[:integrators_key]
    @email =    Docusign::Config[:user_name]
    @password =  Docusign::Config[:password]
    account_id = Docusign::Config[:account_id]

    #If we have all the information necessary, go ahead and store them in the session
    if !account_id.empty? && !@int_key.empty? && !@email.empty? && !@password.empty?
      session[:email] = @email
      session[:password] = @password
      session[:int_key] = @int_key
      session[:account_id] = account_id

      #Ensure we can contact DocuSign with the specified credentials
      result = Docusign::Base.credentials("["+session[:int_key] + "]" + session[:email], session[:password], Docusign::Config[:credential_endpoint_url])
      if result.success?
        session[:logged_in] = true
        redirect_to send_document_path
      else
        redirect_to :controller => 'error', :action => 'show', :message => "Could not connect to DocuSign with the credentials specified in the docusign.yml file." and return
      end
    end
  end

  def create

    #Log into the users account with the specified credentials
    result = Docusign::Base.credentials("["+params['Integrator Key'] + "]" + params[:email], params[:password], Docusign::Config[:credential_endpoint_url])

    if result.success?

      #Store the results in the session
      session[:email] = params[:email]
      session[:password] = params[:password]
      session[:int_key] = params['Integrator Key']
      session[:account_id] = result.accounts[0].accountID .to_s
      session[:logged_in] = true

      #Ensure we can contact DocuSign
      response = ds_connection.ping(1)
      if response.pingResult
        redirect_to send_document_path
      else
        redirect_to :controller => 'error', :action => 'show', :message => "Could not connect to DocuSign." and return
      end

    else
      redirect_to :controller => 'error', :action => 'show', :message => result.authentication_message
    end
  end
end
