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

# Be sure to restart your server when you modify this file.

DocuSignSample::Application.config.session_store :active_record_store, :key => '_DocuSignSample_session'

# Use the database for sessions instead of the cookie-based default,
# which shouldn't be used to store highly confidential information
# (create the session table with "rails generate session_migration")
# DocuSignSample::Application.config.session_store :active_record_store
