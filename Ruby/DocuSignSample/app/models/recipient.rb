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