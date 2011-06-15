
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RecipientEsign complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RecipientEsign">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Esign" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ReservedRecipientEmail" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ReservedRecipientNames" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecipientEsign", propOrder = {
    "userName",
    "email",
    "esign",
    "reservedRecipientEmail",
    "reservedRecipientNames"
})
public class RecipientEsign {

    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "Esign")
    protected boolean esign;
    @XmlElement(name = "ReservedRecipientEmail")
    protected Boolean reservedRecipientEmail;
    @XmlElement(name = "ReservedRecipientNames")
    protected String reservedRecipientNames;

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

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
     * Gets the value of the esign property.
     * 
     */
    public boolean isEsign() {
        return esign;
    }

    /**
     * Sets the value of the esign property.
     * 
     */
    public void setEsign(boolean value) {
        this.esign = value;
    }

    /**
     * Gets the value of the reservedRecipientEmail property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReservedRecipientEmail() {
        return reservedRecipientEmail;
    }

    /**
     * Sets the value of the reservedRecipientEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReservedRecipientEmail(Boolean value) {
        this.reservedRecipientEmail = value;
    }

    /**
     * Gets the value of the reservedRecipientNames property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReservedRecipientNames() {
        return reservedRecipientNames;
    }

    /**
     * Sets the value of the reservedRecipientNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReservedRecipientNames(String value) {
        this.reservedRecipientNames = value;
    }

}
