# Read configuration options from config/docusign.yml

require 'docusign'

Docusign::Config = HashWithIndifferentAccess.new(YAML.load_file("#{Rails.root}/config/docusign.yml")[Rails.env])
Docusign::Config[:verify_mode] = OpenSSL::SSL::VERIFY_PEER
Docusign::Config[:ca_file] = '/usr/local/share/ca-bundle.crt'
