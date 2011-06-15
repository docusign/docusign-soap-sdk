
package net.docusign.api_3_0;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for EnvelopeStatusFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnvelopeStatusFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UserInfo" type="{http://www.docusign.net/API/3.0}UserInfo" minOccurs="0"/>
 *         &lt;element name="AccountId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BeginDateTime" type="{http://www.docusign.net/API/3.0}EnvelopeStatusFilterBeginDateTime"/>
 *         &lt;element name="EndDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Statuses" type="{http://www.docusign.net/API/3.0}ArrayOfEnvelopeStatusCode" minOccurs="0"/>
 *         &lt;element name="EnvelopeIds" type="{http://www.docusign.net/API/3.0}ArrayOfString2" minOccurs="0"/>
 *         &lt;element name="StartAtIndex" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="ACStatus" type="{http://www.docusign.net/API/3.0}EnvelopeACStatusCode" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnvelopeStatusFilter", propOrder = {
    "userInfo",
    "accountId",
    "beginDateTime",
    "endDateTime",
    "statuses",
    "envelopeIds",
    "startAtIndex",
    "acStatus"
})
public class EnvelopeStatusFilter {

    @XmlElement(name = "UserInfo")
    protected UserInfo userInfo;
    @XmlElement(name = "AccountId")
    protected String accountId;
    @XmlElement(name = "BeginDateTime", required = true, nillable = true)
    protected EnvelopeStatusFilterBeginDateTime beginDateTime;
    @XmlElement(name = "EndDateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDateTime;
    @XmlElement(name = "Statuses")
    protected ArrayOfEnvelopeStatusCode statuses;
    @XmlElement(name = "EnvelopeIds")
    protected ArrayOfString2 envelopeIds;
    @XmlElement(name = "StartAtIndex", required = true, nillable = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger startAtIndex;
    @XmlElement(name = "ACStatus")
    protected EnvelopeACStatusCode acStatus;

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
     * Gets the value of the beginDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link EnvelopeStatusFilterBeginDateTime }
     *     
     */
    public EnvelopeStatusFilterBeginDateTime getBeginDateTime() {
        return beginDateTime;
    }

    /**
     * Sets the value of the beginDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnvelopeStatusFilterBeginDateTime }
     *     
     */
    public void setBeginDateTime(EnvelopeStatusFilterBeginDateTime value) {
        this.beginDateTime = value;
    }

    /**
     * Gets the value of the endDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDateTime() {
        return endDateTime;
    }

    /**
     * Sets the value of the endDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDateTime(XMLGregorianCalendar value) {
        this.endDateTime = value;
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

    /**
     * Gets the value of the envelopeIds property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString2 }
     *     
     */
    public ArrayOfString2 getEnvelopeIds() {
        return envelopeIds;
    }

    /**
     * Sets the value of the envelopeIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString2 }
     *     
     */
    public void setEnvelopeIds(ArrayOfString2 value) {
        this.envelopeIds = value;
    }

    /**
     * Gets the value of the startAtIndex property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStartAtIndex() {
        return startAtIndex;
    }

    /**
     * Sets the value of the startAtIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStartAtIndex(BigInteger value) {
        this.startAtIndex = value;
    }

    /**
     * Gets the value of the acStatus property.
     * 
     * @return
     *     possible object is
     *     {@link EnvelopeACStatusCode }
     *     
     */
    public EnvelopeACStatusCode getACStatus() {
        return acStatus;
    }

    /**
     * Sets the value of the acStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnvelopeACStatusCode }
     *     
     */
    public void setACStatus(EnvelopeACStatusCode value) {
        this.acStatus = value;
    }

}
