/**
 * @copyright Copyright (C) DocuSign, Inc.  All rights reserved.
 *
 * This source code is intended only as a supplement to DocuSign SDK
 * and/or on-line documentation.
 * 
 * This sample is designed to demonstrate DocuSign features and is not intended
 * for production use. Code and policy for a production application must be
 * developed to meet the specific data and security requirements of the
 * application.
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 */
package net.docusign.sample;

/*
Copyright (C) DocuSign, Inc.  All rights reserved.

This source code is intended only as a supplement to DocuSign SDK and/or on-line documentation.

This sample is designed to demonstrate DocuSign features and is not intended for production use. 
Code and policy for a production application must be developed to meet the specific data and 
security requirements of the application.

THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESSED 
OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR 
FITNESS FOR A PARTICULAR PURPOSE.
 */
import java.io.IOException;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

/**
 * {@link CallbackHandler} that provides plain-text passwords for {@link WSPasswordCallback}s.  The available
 * usage types supported are {@link WSPasswordCallback#USERNAME_TOKEN}, to support docusign password, and {@link WSPasswordCallback#SIGNATURE},
 * to support X.509 keystore password (optional).
 */
public class PasswordCallbackHandler implements CallbackHandler {
	private Map<Integer, String> passwordUsageMap;
	
	public PasswordCallbackHandler(Map<Integer, String> passwordUsageMap) {
		this.passwordUsageMap = passwordUsageMap;
	}
	
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
        	if (callbacks[i] instanceof WSPasswordCallback) {
        		WSPasswordCallback pc = (WSPasswordCallback)callbacks[i];

        		pc.setPassword(passwordUsageMap.get(Integer.valueOf(pc.getUsage())));
        	}
        }
	}
}
