
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for EnvelopeStatusChangeFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnvelopeStatusChangeFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AccountId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserInfo" type="{http://www.docusign.net/API/3.0}UserInfo" minOccurs="0"/>
 *         &lt;element name="StatusChangedSince" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Statuses" type="{http://www.docusign.net/API/3.0}ArrayOfEnvelopeStatusCode" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnvelopeStatusChangeFilter", propOrder = {
    "accountId",
    "userInfo",
    "statusChangedSince",
    "statuses"
})
public class EnvelopeStatusChangeFilter {

    @XmlElement(name = "AccountId")
    protected String accountId;
    @XmlElement(name = "UserInfo")
    protected UserInfo userInfo;
    @XmlElement(name = "StatusChangedSince", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar statusChangedSince;
    @XmlElement(name = "Statuses")
    protected ArrayOfEnvelopeStatusCode statuses;

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
     * Gets the value of the userInfo property.
     * 
     * @return
     *     possible object is
     *     {@link UserInfo }
     *     
     */
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * Sets the value of the userInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserInfo }
     *     
     */
    public void setUserInfo(UserInfo value) {
        this.userInfo = value;
    }

    /**
     * Gets the value of the statusChangedSince property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStatusChangedSince() {
        return statusChangedSince;
    }

    /**
     * Sets the value of the statusChangedSince property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStatusChangedSince(XMLGregorianCalendar value) {
        this.statusChangedSince = value;
    }

    /**
     * Gets the value of the statuses property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEnvelopeStatusCode }
     *     
     */
    public ArrayOfEnvelopeStatusCode getStatuses() {
        return statuses;
    }

    /**
     * Sets the value of the statuses property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEnvelopeStatusCode }
     *     
     */
    public void setStatuses(ArrayOfEnvelopeStatusCode value) {
        this.statuses = value;
    }

}
