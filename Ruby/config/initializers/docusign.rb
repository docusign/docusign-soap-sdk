# Read configuration options from config/docusign.yml

require 'docusign'

Docusign::Config = HashWithIndifferentAccess.new(YAML.load_file("#{Rails.root}/config/docusign.yml")[Rails.env])