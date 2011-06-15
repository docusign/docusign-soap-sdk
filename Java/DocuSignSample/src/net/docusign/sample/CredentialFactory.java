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
package net.docusign.sample;

import net.docusign.credential.CredentialSoap;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * Factory to setup credential jaxws client.
 */
public class CredentialFactory {

    /**
	 * Builds the API interface in order to use the Docusign Credential API.
	 *
	 * @param webserviceEndpoint the endpoint for the credential webservice
	 * @return the credential api stub
	 */
	public CredentialSoap getCredential(String webserviceEndpoint) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		setupService(factory, webserviceEndpoint);

		CredentialSoap credentialProxy = (CredentialSoap) factory.create();

		return credentialProxy;
	}

	/**
	 * Set service class and webservice url.
	 *
	 * @param factory
	 * @param webserviceEndpoint the endpoint for the credential webservice
	 */
	protected void setupService(JaxWsProxyFactoryBean factory, String webserviceEndpoint) {
		factory.setServiceClass(CredentialSoap.class);
		factory.setAddress(webserviceEndpoint);
	}
}
