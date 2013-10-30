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

DocuSignSample::Application.routes.draw do
  root :to => "logins", :action => "new"

  resources :application, :only => [:clear_session]
  match "application/clear_session" => "application#clear_session"

  resources :logins, :only => [:new, :create]
  match "logins/create" => "logins#create"

  resources :send_document, :only => [:create, :send_envelope, :logout]
  match "send_document" => "send_document#create"
  match "send_document/send_envelope" => "send_document#send_envelope"
  match "send_document/logout" => "send_document#logout"

  resources :send_template, :only => [:create, :edit, :logout]
  match "send_template/" => "send_template#create"
  match "send_template/send_template" =>"send_template#send_template"
  match "send_template/get_template" => "send_template#get_template"
  match "send_template/edit" => "send_template#edit"
  match "send_template/logout" => "send_template#logout"

  resources:embed_docusign, :only => [:new, :edit, :logout]
  match "embed_docusign/" => "embed_docusign#new"
  match "embed_docusign/create" =>"embed_docusign#create"
  match "embed_docusign/logout" => "embed_docusign#logout"

  resources :get_status_and_docs, :only => [:list, :show, :download, :logout]
  match "get_status_and_docs/" => "get_status_and_docs#list"
  match "get_status_and_docs/show" => "get_status_and_docs#show"
  match "get_status_and_docs/logout" => "get_status_and_docs#logout"

  resources :error, :only => [:show, :logout]
  match "error/" => "error#show"
  match "error/show" => "error#show"
  match "error/logout" => "error#logout"
end
