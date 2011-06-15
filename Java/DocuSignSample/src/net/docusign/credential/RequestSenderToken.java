
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
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnvelopeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReturnURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "email",
    "password",
    "accountID",
    "envelopeID",
    "returnURL"
})
@XmlRootElement(name = "RequestSenderToken")
public class RequestSenderToken {

    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "AccountID")
    protected String accountID;
    @XmlElement(name = "EnvelopeID")
    protected String envelopeID;
    @XmlElement(name = "ReturnURL")
    protected String returnURL;

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the accountID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountID() {
        return accountID;
    }

    /**
     * Sets the value of the accountID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountID(String value) {
        this.accountID = value;
    }

    /**
     * Gets the value of the envelopeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnvelopeID() {
        return envelopeID;
    }

    /**
     * Sets the value of the envelopeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnvelopeID(String value) {
        this.envelopeID = value;
    }

    /**
     * Gets the value of the returnURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnURL() {
        return returnURL;
    }

    /**
     * Sets the value of the returnURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnURL(String value) {
        this.returnURL = value;
    }

}
