
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RecipientStatusEsignAgreementInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RecipientStatusEsignAgreementInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AccountEsignId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserEsignId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AgreementDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecipientStatusEsignAgreementInformation", propOrder = {
    "accountEsignId",
    "userEsignId",
    "agreementDate"
})
public class RecipientStatusEsignAgreementInformation {

    @XmlElement(name = "AccountEsignId")
    protected String accountEsignId;
    @XmlElement(name = "UserEsignId")
    protected String userEsignId;
    @XmlElement(name = "AgreementDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar agreementDate;

    /**
     * Gets the value of the accountEsignId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountEsignId() {
        return accountEsignId;
    }

    /**
     * Sets the value of the accountEsignId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountEsignId(String value) {
        this.accountEsignId = value;
    }

    /**
     * Gets the value of the userEsignId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserEsignId() {
        return userEsignId;
    }

    /**
     * Sets the value of the userEsignId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserEsignId(String value) {
        this.userEsignId = value;
    }

    /**
     * Gets the value of the agreementDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAgreementDate() {
        return agreementDate;
    }

    /**
     * Sets the value of the agreementDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAgreementDate(XMLGregorianCalendar value) {
        this.agreementDate = value;
    }

}
