
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RecipientStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RecipientStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Type" type="{http://www.docusign.net/API/3.0}RecipientTypeCode"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RoutingOrder" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *         &lt;element name="Sent" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Delivered" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Signed" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Declined" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="DeclineReason" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Status" type="{http://www.docusign.net/API/3.0}RecipientStatusCode"/>
 *         &lt;element name="RecipientIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClientUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AutoNavigation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IDCheckInformation" type="{http://www.docusign.net/API/3.0}IDCheckInformation" minOccurs="0"/>
 *         &lt;element name="RecipientAuthenticationStatus" type="{http://www.docusign.net/API/3.0}AuthenticationStatus" minOccurs="0"/>
 *         &lt;element name="CustomFields" type="{http://www.docusign.net/API/3.0}ArrayOfString1" minOccurs="0"/>
 *         &lt;element name="TabStatuses" type="{http://www.docusign.net/API/3.0}ArrayOfTabStatus" minOccurs="0"/>
 *         &lt;element name="RecipientAttachment" type="{http://www.docusign.net/API/3.0}ArrayOfAttachment" minOccurs="0"/>
 *         &lt;element name="AccountStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EsignAgreementInformation" type="{http://www.docusign.net/API/3.0}RecipientStatusEsignAgreementInformation" minOccurs="0"/>
 *         &lt;element name="FormData" type="{http://www.docusign.net/API/3.0}FormData" minOccurs="0"/>
 *         &lt;element name="RecipientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecipientStatus", propOrder = {
    "type",
    "email",
    "userName",
    "routingOrder",
    "sent",
    "delivered",
    "signed",
    "declined",
    "declineReason",
    "status",
    "recipientIPAddress",
    "clientUserId",
    "autoNavigation",
    "idCheckInformation",
    "recipientAuthenticationStatus",
    "customFields",
    "tabStatuses",
    "recipientAttachment",
    "accountStatus",
    "esignAgreementInformation",
    "formData",
    "recipientId"
})
public class RecipientStatus {

    @XmlElement(name = "Type", required = true)
    protected RecipientTypeCode type;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "RoutingOrder")
    @XmlSchemaType(name = "unsignedShort")
    protected int routingOrder;
    @XmlElement(name = "Sent")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sent;
    @XmlElement(name = "Delivered")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar delivered;
    @XmlElement(name = "Signed")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar signed;
    @XmlElement(name = "Declined")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar declined;
    @XmlElement(name = "DeclineReason", required = true, nillable = true)
    protected String declineReason;
    @XmlElement(name = "Status", required = true)
    protected RecipientStatusCode status;
    @XmlElement(name = "RecipientIPAddress")
    protected String recipientIPAddress;
    @XmlElement(name = "ClientUserId")
    protected String clientUserId;
    @XmlElement(name = "AutoNavigation")
    protected Boolean autoNavigation;
    @XmlElement(name = "IDCheckInformation")
    protected IDCheckInformation idCheckInformation;
    @XmlElement(name = "RecipientAuthenticationStatus")
    protected AuthenticationStatus recipientAuthenticationStatus;
    @XmlElement(name = "CustomFields")
    protected ArrayOfString1 customFields;
    @XmlElement(name = "TabStatuses")
    protected ArrayOfTabStatus tabStatuses;
    @XmlElement(name = "RecipientAttachment")
    protected ArrayOfAttachment recipientAttachment;
    @XmlElement(name = "AccountStatus")
    protected String accountStatus;
    @XmlElement(name = "EsignAgreementInformation")
    protected RecipientStatusEsignAgreementInformation esignAgreementInformation;
    @XmlElement(name = "FormData")
    protected FormData formData;
    @XmlElement(name = "RecipientId")
    protected String recipientId;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link RecipientTypeCode }
     *     
     */
    public RecipientTypeCode getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipientTypeCode }
     *     
     */
    public void setType(RecipientTypeCode value) {
        this.type = value;
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
     * Gets the value of the routingOrder property.
     * 
     */
    public int getRoutingOrder() {
        return routingOrder;
    }

    /**
     * Sets the value of the routingOrder property.
     * 
     */
    public void setRoutingOrder(int value) {
        this.routingOrder = value;
    }

    /**
     * Gets the value of the sent property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSent() {
        return sent;
    }

    /**
     * Sets the value of the sent property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSent(XMLGregorianCalendar value) {
        this.sent = value;
    }

    /**
     * Gets the value of the delivered property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDelivered() {
        return delivered;
    }

    /**
     * Sets the value of the delivered property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDelivered(XMLGregorianCalendar value) {
        this.delivered = value;
    }

    /**
     * Gets the value of the signed property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSigned() {
        return signed;
    }

    /**
     * Sets the value of the signed property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSigned(XMLGregorianCalendar value) {
        this.signed = value;
    }

    /**
     * Gets the value of the declined property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeclined() {
        return declined;
    }

    /**
     * Sets the value of the declined property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeclined(XMLGregorianCalendar value) {
        this.declined = value;
    }

    /**
     * Gets the value of the declineReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeclineReason() {
        return declineReason;
    }

    /**
     * Sets the value of the declineReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeclineReason(String value) {
        this.declineReason = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link RecipientStatusCode }
     *     
     */
    public RecipientStatusCode getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipientStatusCode }
     *     
     */
    public void setStatus(RecipientStatusCode value) {
        this.status = value;
    }

    /**
     * Gets the value of the recipientIPAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecipientIPAddress() {
        return recipientIPAddress;
    }

    /**
     * Sets the value of the recipientIPAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecipientIPAddress(String value) {
        this.recipientIPAddress = value;
    }

    /**
     * Gets the value of the clientUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientUserId() {
        return clientUserId;
    }

    /**
     * Sets the value of the clientUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientUserId(String value) {
        this.clientUserId = value;
    }

    /**
     * Gets the value of the autoNavigation property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutoNavigation() {
        return autoNavigation;
    }

    /**
     * Sets the value of the autoNavigation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoNavigation(Boolean value) {
        this.autoNavigation = value;
    }

    /**
     * Gets the value of the idCheckInformation property.
     * 
     * @return
     *     possible object is
     *     {@link IDCheckInformation }
     *     
     */
    public IDCheckInformation getIDCheckInformation() {
        return idCheckInformation;
    }

    /**
     * Sets the value of the idCheckInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link IDCheckInformation }
     *     
     */
    public void setIDCheckInformation(IDCheckInformation value) {
        this.idCheckInformation = value;
    }

    /**
     * Gets the value of the recipientAuthenticationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link AuthenticationStatus }
     *     
     */
    public AuthenticationStatus getRecipientAuthenticationStatus() {
        return recipientAuthenticationStatus;
    }

    /**
     * Sets the value of the recipientAuthenticationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthenticationStatus }
     *     
     */
    public void setRecipientAuthenticationStatus(AuthenticationStatus value) {
        this.recipientAuthenticationStatus = value;
    }

    /**
     * Gets the value of the customFields property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString1 }
     *     
     */
    public ArrayOfString1 getCustomFields() {
        return customFields;
    }

    /**
     * Sets the value of the customFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString1 }
     *     
     */
    public void setCustomFields(ArrayOfString1 value) {
        this.customFields = value;
    }

    /**
     * Gets the value of the tabStatuses property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTabStatus }
     *     
     */
    public ArrayOfTabStatus getTabStatuses() {
        return tabStatuses;
    }

    /**
     * Sets the value of the tabStatuses property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTabStatus }
     *     
     */
    public void setTabStatuses(ArrayOfTabStatus value) {
        this.tabStatuses = value;
    }

    /**
     * Gets the value of the recipientAttachment property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAttachment }
     *     
     */
    public ArrayOfAttachment getRecipientAttachment() {
        return recipientAttachment;
    }

    /**
     * Sets the value of the recipientAttachment property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAttachment }
     *     
     */
    public void setRecipientAttachment(ArrayOfAttachment value) {
        this.recipientAttachment = value;
    }

    /**
     * Gets the value of the accountStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountStatus() {
        return accountStatus;
    }

    /**
     * Sets the value of the accountStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountStatus(String value) {
        this.accountStatus = value;
    }

    /**
     * Gets the value of the esignAgreementInformation property.
     * 
     * @return
     *     possible object is
     *     {@link RecipientStatusEsignAgreementInformation }
     *     
     */
    public RecipientStatusEsignAgreementInformation getEsignAgreementInformation() {
        return esignAgreementInformation;
    }

    /**
     * Sets the value of the esignAgreementInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipientStatusEsignAgreementInformation }
     *     
     */
    public void setEsignAgreementInformation(RecipientStatusEsignAgreementInformation value) {
        this.esignAgreementInformation = value;
    }

    /**
     * Gets the value of the formData property.
     * 
     * @return
     *     possible object is
     *     {@link FormData }
     *     
     */
    public FormData getFormData() {
        return formData;
    }

    /**
     * Sets the value of the formData property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormData }
     *     
     */
    public void setFormData(FormData value) {
        this.formData = value;
    }

    /**
     * Gets the value of the recipientId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecipientId() {
        return recipientId;
    }

    /**
     * Sets the value of the recipientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecipientId(String value) {
        this.recipientId = value;
    }

}
