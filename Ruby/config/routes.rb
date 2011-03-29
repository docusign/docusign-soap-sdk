DocuSignSample::Application.routes.draw do
  root :to => "logins", :action => "new"
  resources :logins, :only => [:new, :create]
  match "logins/create" => "logins#create"

  resources :send_document, :only => [:create, :send_envelope]
  match "send_document" => "send_document#create"
  match "send_document/send_envelope" => "send_document#send_envelope"

  resources :send_template, :only => [:create, :edit]
  match "send_template/" => "send_template#create"
  match "send_template/send_template" =>"send_template#send_template"
  match "send_template/get_template" => "send_template#get_template"
  match "send_template/edit" => "send_template#edit"

  resources:embed_docusign, :only => [:new, :edit]
  match "embed_docusign/" => "embed_docusign#new"
  match "embed_docusign/create" =>"embed_docusign#create"

  resources :get_status_and_docs, :only => [:list, :show, :download]
  match "get_status_and_docs/" => "get_status_and_docs#list"
  match "get_status_and_docs/show" => "get_status_and_docs#show"

  resources :error, :only => [:show]
  match "error/" => "error#show"
  match "error/show" => "error#show"
end
