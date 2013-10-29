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
package net.docusign.credential;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the net.docusign.credential package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.docusign.credential
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link ArrayOfAccount }
     * 
     */
    public ArrayOfAccount createArrayOfAccount() {
        return new ArrayOfAccount();
    }

    /**
     * Create an instance of {@link GetAuthenticationToken }
     * 
     */
    public GetAuthenticationToken createGetAuthenticationToken() {
        return new GetAuthenticationToken();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link RequestCorrectToken }
     * 
     */
    public RequestCorrectToken createRequestCorrectToken() {
        return new RequestCorrectToken();
    }

    /**
     * Create an instance of {@link RequestSenderToken }
     * 
     */
    public RequestSenderToken createRequestSenderToken() {
        return new RequestSenderToken();
    }

    /**
     * Create an instance of {@link RequestCorrectTokenResponse }
     * 
     */
    public RequestCorrectTokenResponse createRequestCorrectTokenResponse() {
        return new RequestCorrectTokenResponse();
    }

    /**
     * Create an instance of {@link LoginResponse.LoginResult }
     * 
     */
    public LoginResponse.LoginResult createLoginResponseLoginResult() {
        return new LoginResponse.LoginResult();
    }

    /**
     * Create an instance of {@link Ping }
     * 
     */
    public Ping createPing() {
        return new Ping();
    }

    /**
     * Create an instance of {@link RequestSenderTokenResponse }
     * 
     */
    public RequestSenderTokenResponse createRequestSenderTokenResponse() {
        return new RequestSenderTokenResponse();
    }

    /**
     * Create an instance of {@link PingResponse }
     * 
     */
    public PingResponse createPingResponse() {
        return new PingResponse();
    }

    /**
     * Create an instance of {@link GetAuthenticationTokenResponse }
     * 
     */
    public GetAuthenticationTokenResponse createGetAuthenticationTokenResponse() {
        return new GetAuthenticationTokenResponse();
    }

    /**
     * Create an instance of {@link ArrayOfAccount.Account }
     * 
     */
    public ArrayOfAccount.Account createArrayOfAccountAccount() {
        return new ArrayOfAccount.Account();
    }

}
