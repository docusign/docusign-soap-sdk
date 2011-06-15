
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RecipientEsignList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RecipientEsignList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AccountId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecipientEsign" type="{http://www.docusign.net/API/3.0}ArrayOfRecipientEsign" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecipientEsignList", propOrder = {
    "accountId",
    "recipientEsign"
})
public class RecipientEsignList {

    @XmlElement(name = "AccountId")
    protected String accountId;
    @XmlElement(name = "RecipientEsign")
    protected ArrayOfRecipientEsign recipientEsign;

    /**
     * Gets the value of the accountId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Sets the value of the accountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountId(String value) {
        this.accountId = value;
    }

    /**
     * Gets the value of the recipientEsign property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRecipientEsign }
     *     
     */
    public ArrayOfRecipientEsign getRecipientEsign() {
        return recipientEsign;
    }

    /**
     * Sets the value of the recipientEsign property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRecipientEsign }
     *     
     */
    public void setRecipientEsign(ArrayOfRecipientEsign value) {
        this.recipientEsign = value;
    }

}
