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

import java.util.HashMap;
import java.util.Map;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPBinding;

import net.docusign.api_3_0.APIServiceSoap;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.handler.WSHandlerConstants;

/**
 * Sets up the CXF client to enable calling the webservice operations.  This setup includes
 * both WS-Security and MTOM for soap attachments.
 */
public class DocusignWebserviceFactory {
    private static final String INTEGRATORS_KEY_FORMAT = "[%s]%s"; 
	private JaxWsProxyFactoryBean factory;
	protected String integratorsId;
	protected String userId;
	protected String email;
	
	public String getIntegratorsKey() {
	    return String.format(INTEGRATORS_KEY_FORMAT, integratorsId, email);
	}
	
	public void setIntegratorsId(String integratorsId) {
	    this.integratorsId = integratorsId;
	}
	
	public void setUserId(String userId) {
	    this.userId = userId;
	}
	
	public void setEmail(String email) {
	    this.email = email;
	}
	
	public DocusignWebserviceFactory setupClient(String webserviceEndpoint) {
		factory = new JaxWsProxyFactoryBean();
		setupService(factory, webserviceEndpoint);

		return this;
	}

	/**
	 * Builds the API interface in order to use the Docusign API for your account.
	 *
	 * @param docusignAPICredentials
	 * @return
	 */
	public APIServiceSoap authorizeAPI(DocusignAPICredentials docusignAPICredentials) {
		setupWSSecurity(docusignAPICredentials, factory);
    	APIServiceSoap docusignAPIProxy = (APIServiceSoap) factory.create();

    	Client client = ClientProxy.getClient(docusignAPIProxy);
    	HTTPConduit httpConduit = (HTTPConduit) client.getConduit();

    	HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

    	httpClientPolicy.setAutoRedirect(true);
    	httpClientPolicy.setAllowChunking(false);

    	httpConduit.setClient(httpClientPolicy);

    	enableMTOM(docusignAPIProxy);
    	return docusignAPIProxy;
	}

	/**
	 * Set service class and webservice url.
	 *
	 * @param factory
	 */
	protected void setupService(JaxWsProxyFactoryBean factory, String webserviceEndpoint) {
		factory.setServiceClass(APIServiceSoap.class);
    	factory.setAddress(webserviceEndpoint);

    	factory.getInInterceptors().add(new LoggingInInterceptor());
    	factory.getOutInterceptors().add(new LoggingOutInterceptor());
	}

	/**
	 * Add WSS4J out interceptor.
	 *
	 * @param docusignAPICredentials
	 * @param factory
	 */
	protected void setupWSSecurity(DocusignAPICredentials docusignAPICredentials, JaxWsProxyFactoryBean factory) {
    	WSS4JOutInterceptor securityInterceptor = new WSS4JOutInterceptor(buildWSS4JProperties(docusignAPICredentials));
		factory.getOutInterceptors().add(securityInterceptor);
	}

	/**
	 * Build WSS4j properties using standard Username / PasswordText via the UsernameToken.
	 *
	 * @param docusignAPICredentials
	 * @return
	 */
	protected Map<String, Object> buildWSS4JProperties(DocusignAPICredentials docusignAPICredentials) {
	    this.email = docusignAPICredentials.getUserEmail();
	    
		Map<String,Object> securityInterceptorProperties = new HashMap<String,Object>();
		securityInterceptorProperties.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
		
		setUsernameToken(securityInterceptorProperties);
		
		securityInterceptorProperties.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);

		Map<Integer, String> passwordUsageMap = new HashMap<Integer, String>();
		passwordUsageMap.put(WSPasswordCallback.USERNAME_TOKEN, docusignAPICredentials.getPassword());

		securityInterceptorProperties.put(WSHandlerConstants.PW_CALLBACK_REF, new PasswordCallbackHandler(passwordUsageMap));

		return securityInterceptorProperties;
	}

    protected void setUsernameToken(Map<String, Object> securityProperties) {
        if(integratorsId != null) {
		    securityProperties.put(WSHandlerConstants.USER, getIntegratorsKey());
		} else if(userId != null) {
		    securityProperties.put(WSHandlerConstants.USER, userId);
		} else {
		    throw new IllegalStateException("Docusign user id or integrators key not provided.  Please consult documentation.");
		}
    }

	/**
	 * Enable the Message Transmission Optimization Mechanism for optimizing binary data in SOAP responses.
	 *
	 * @see <a href="http://www.w3.org/TR/soap12-mtom/">MTOM Specification</a>
	 * @param docusignAPIProxy
	 */
	protected void enableMTOM(APIServiceSoap docusignAPIProxy) {
		SOAPBinding soapBinding = (SOAPBinding) ((BindingProvider) docusignAPIProxy).getBinding();
    	soapBinding.setMTOMEnabled(true);
	}
}
