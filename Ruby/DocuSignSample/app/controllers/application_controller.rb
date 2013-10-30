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

class ApplicationController < ActionController::Base
  include Intercession
  helper :all
  protect_from_forgery
  attr_accessor :username

  #Method to ensure that the user is logged in before every call to DocuSign
  def logged_in?
    if session[:int_key].nil? || session[:email].nil? || session[:password].nil? || session[:logged_in].nil?
      false
    else
      true
    end
  end

  #Method to clear all the session variables and redirect to login
  def clear_session
    session[:int_key] = nil
    session[:email] = nil
    session[:password] = nil
    session[:account_id] = nil
    redirect_to root_url
  end

  #This defines the proxy connection to DocuSign
  def ds_connection
    if session[:int_key].nil? || session[:email].nil? || session[:password].nil?
      redirect_to new_login_path
    else
      @connection ||= Docusign::Base.login(
          :integrators_key => session[:int_key],
          :email           => session[:email],
          :password        => session[:password],
          :endpoint_url    => Docusign::Config[:default_endpoint_url],
          :wiredump_dev    => STDOUT #This echoes all SOAP calls to standard out. Change this to log somewhere else, or comment out to eliminate logging.
      )
    end
  end

  #This defines the proxy connection to the DocuSign credential service
  def ds_credential_connection
    Docusign::Credential::CredentialSoap.new.tap do |c|
      c.endpoint_url = Docusign::Config[:credential_endpoint_url]
    end
  end
end
