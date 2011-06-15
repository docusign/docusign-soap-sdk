
package net.docusign.credential;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LoginResult" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="ErrorCode" type="{http://www.docusign.net/API/Credential}ErrorCode" minOccurs="0"/>
 *                   &lt;element name="AuthenticationMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Accounts" type="{http://www.docusign.net/API/Credential}ArrayOfAccount" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "loginResult"
})
@XmlRootElement(name = "LoginResponse")
public class LoginResponse {

    @XmlElement(name = "LoginResult")
    protected LoginResponse.LoginResult loginResult;

    /**
     * Gets the value of the loginResult property.
     * 
     * @return
     *     possible object is
     *     {@link LoginResponse.LoginResult }
     *     
     */
    public LoginResponse.LoginResult getLoginResult() {
        return loginResult;
    }

    /**
     * Sets the value of the loginResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link LoginResponse.LoginResult }
     *     
     */
    public void setLoginResult(LoginResponse.LoginResult value) {
        this.loginResult = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="ErrorCode" type="{http://www.docusign.net/API/Credential}ErrorCode" minOccurs="0"/>
     *         &lt;element name="AuthenticationMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Accounts" type="{http://www.docusign.net/API/Credential}ArrayOfAccount" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "success",
        "errorCode",
        "authenticationMessage",
        "accounts"
    })
    public static class LoginResult {

        @XmlElement(name = "Success")
        protected boolean success;
        @XmlElement(name = "ErrorCode")
        protected ErrorCode errorCode;
        @XmlElement(name = "AuthenticationMessage")
        protected String authenticationMessage;
        @XmlElement(name = "Accounts")
        protected ArrayOfAccount accounts;

        /**
         * Gets the value of the success property.
         * 
         */
        public boolean isSuccess() {
            return success;
        }

        /**
         * Sets the value of the success property.
         * 
         */
        public void setSuccess(boolean value) {
            this.success = value;
        }

        /**
         * Gets the value of the errorCode property.
         * 
         * @return
         *     possible object is
         *     {@link ErrorCode }
         *     
         */
        public ErrorCode getErrorCode() {
            return errorCode;
        }

        /**
         * Sets the value of the errorCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link ErrorCode }
         *     
         */
        public void setErrorCode(ErrorCode value) {
            this.errorCode = value;
        }

        /**
         * Gets the value of the authenticationMessage property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAuthenticationMessage() {
            return authenticationMessage;
        }

        /**
         * Sets the value of the authenticationMessage property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAuthenticationMessage(String value) {
            this.authenticationMessage = value;
        }

        /**
         * Gets the value of the accounts property.
         * 
         * @return
         *     possible object is
         *     {@link ArrayOfAccount }
         *     
         */
        public ArrayOfAccount getAccounts() {
            return accounts;
        }

        /**
         * Sets the value of the accounts property.
         * 
         * @param value
         *     allowed object is
         *     {@link ArrayOfAccount }
         *     
         */
        public void setAccounts(ArrayOfAccount value) {
            this.accounts = value;
        }

    }

}
