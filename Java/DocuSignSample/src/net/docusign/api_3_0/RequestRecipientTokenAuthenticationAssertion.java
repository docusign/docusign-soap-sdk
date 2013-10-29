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
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RequestRecipientTokenAuthenticationAssertion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestRecipientTokenAuthenticationAssertion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AssertionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AuthenticationInstant" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="AuthenticationMethod" type="{http://www.docusign.net/API/3.0}RequestRecipientTokenAuthenticationAssertionAuthenticationMethod"/>
 *         &lt;element name="SecurityDomain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestRecipientTokenAuthenticationAssertion", propOrder = {
    "assertionID",
    "authenticationInstant",
    "authenticationMethod",
    "securityDomain"
})
public class RequestRecipientTokenAuthenticationAssertion {

    @XmlElement(name = "AssertionID")
    protected String assertionID;
    @XmlElement(name = "AuthenticationInstant", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar authenticationInstant;
    @XmlElement(name = "AuthenticationMethod", required = true)
    protected RequestRecipientTokenAuthenticationAssertionAuthenticationMethod authenticationMethod;
    @XmlElement(name = "SecurityDomain")
    protected String securityDomain;

    /**
     * Gets the value of the assertionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssertionID() {
        return assertionID;
    }

    /**
     * Sets the value of the assertionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssertionID(String value) {
        this.assertionID = value;
    }

    /**
     * Gets the value of the authenticationInstant property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAuthenticationInstant() {
        return authenticationInstant;
    }

    /**
     * Sets the value of the authenticationInstant property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAuthenticationInstant(XMLGregorianCalendar value) {
        this.authenticationInstant = value;
    }

    /**
     * Gets the value of the authenticationMethod property.
     * 
     * @return
     *     possible object is
     *     {@link RequestRecipientTokenAuthenticationAssertionAuthenticationMethod }
     *     
     */
    public RequestRecipientTokenAuthenticationAssertionAuthenticationMethod getAuthenticationMethod() {
        return authenticationMethod;
    }

    /**
     * Sets the value of the authenticationMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestRecipientTokenAuthenticationAssertionAuthenticationMethod }
     *     
     */
    public void setAuthenticationMethod(RequestRecipientTokenAuthenticationAssertionAuthenticationMethod value) {
        this.authenticationMethod = value;
    }

    /**
     * Gets the value of the securityDomain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityDomain() {
        return securityDomain;
    }

    /**
     * Sets the value of the securityDomain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityDomain(String value) {
        this.securityDomain = value;
    }

}
