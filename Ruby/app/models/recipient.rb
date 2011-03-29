class Recipient
  include ActiveModel::Validations

  attr_accessor :username, :email, :ident, :embedded, :security, :accesscode, :rolename

  def initialize(name, email, ident, embedded, security, code, role)
    @username = name
    @email = email
    @ident = ident
    @embedded = embedded
    @security = security
    @accesscode = code
    @rolename = role
  end
end