
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for EnvelopeStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnvelopeStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecipientStatuses" type="{http://www.docusign.net/API/3.0}ArrayOfRecipientStatus"/>
 *         &lt;element name="TimeGenerated" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EnvelopeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.docusign.net/API/3.0}EnvelopeStatusCode"/>
 *         &lt;element name="Created" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Deleted" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Sent" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Delivered" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Signed" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Completed" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Declined" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="TimedOut" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ACStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ACStatusDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ACHolder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ACHolderEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ACHolderLocation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SigningLocation" type="{http://www.docusign.net/API/3.0}SigningLocationCode"/>
 *         &lt;element name="SenderIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnvelopePDFHash" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomFields" type="{http://www.docusign.net/API/3.0}ArrayOfCustomField" minOccurs="0"/>
 *         &lt;element name="VaultingDetails" type="{http://www.docusign.net/API/3.0}VaultingDetails" minOccurs="0"/>
 *         &lt;element name="AutoNavigation" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="EnvelopeIdStamping" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AuthoritativeCopy" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="EnvelopeAttachment" type="{http://www.docusign.net/API/3.0}ArrayOfAttachment" minOccurs="0"/>
 *         &lt;element name="DocumentStatuses" type="{http://www.docusign.net/API/3.0}ArrayOfDocumentStatus" minOccurs="0"/>
 *         &lt;element name="FormData" type="{http://www.docusign.net/API/3.0}FormData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnvelopeStatus", propOrder = {
    "recipientStatuses",
    "timeGenerated",
    "envelopeID",
    "subject",
    "userName",
    "email",
    "status",
    "created",
    "deleted",
    "sent",
    "delivered",
    "signed",
    "completed",
    "declined",
    "timedOut",
    "acStatus",
    "acStatusDate",
    "acHolder",
    "acHolderEmail",
    "acHolderLocation",
    "signingLocation",
    "senderIPAddress",
    "envelopePDFHash",
    "customFields",
    "vaultingDetails",
    "autoNavigation",
    "envelopeIdStamping",
    "authoritativeCopy",
    "envelopeAttachment",
    "documentStatuses",
    "formData"
})
public class EnvelopeStatus {

    @XmlElement(name = "RecipientStatuses", required = true, nillable = true)
    protected ArrayOfRecipientStatus recipientStatuses;
    @XmlElement(name = "TimeGenerated", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timeGenerated;
    @XmlElement(name = "EnvelopeID")
    protected String envelopeID;
    @XmlElement(name = "Subject")
    protected String subject;
    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "Status", required = true)
    protected EnvelopeStatusCode status;
    @XmlElement(name = "Created", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar created;
    @XmlElement(name = "Deleted")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar deleted;
    @XmlElement(name = "Sent")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sent;
    @XmlElement(name = "Delivered")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar delivered;
    @XmlElement(name = "Signed")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar signed;
    @XmlElement(name = "Completed")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar completed;
    @XmlElement(name = "Declined")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar declined;
    @XmlElement(name = "TimedOut")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timedOut;
    @XmlElement(name = "ACStatus")
    protected String acStatus;
    @XmlElement(name = "ACStatusDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar acStatusDate;
    @XmlElement(name = "ACHolder")
    protected String acHolder;
    @XmlElement(name = "ACHolderEmail")
    protected String acHolderEmail;
    @XmlElement(name = "ACHolderLocation")
    protected String acHolderLocation;
    @XmlElement(name = "SigningLocation", required = true)
    protected SigningLocationCode signingLocation;
    @XmlElement(name = "SenderIPAddress")
    protected String senderIPAddress;
    @XmlElement(name = "EnvelopePDFHash")
    protected String envelopePDFHash;
    @XmlElement(name = "CustomFields")
    protected ArrayOfCustomField customFields;
    @XmlElement(name = "VaultingDetails")
    protected VaultingDetails vaultingDetails;
    @XmlElement(name = "AutoNavigation")
    protected boolean autoNavigation;
    @XmlElement(name = "EnvelopeIdStamping")
    protected boolean envelopeIdStamping;
    @XmlElement(name = "AuthoritativeCopy")
    protected Boolean authoritativeCopy;
    @XmlElement(name = "EnvelopeAttachment")
    protected ArrayOfAttachment envelopeAttachment;
    @XmlElement(name = "DocumentStatuses")
    protected ArrayOfDocumentStatus documentStatuses;
    @XmlElement(name = "FormData")
    protected FormData formData;

    /**
     * Gets the value of the recipientStatuses property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRecipientStatus }
     *     
     */
    public ArrayOfRecipientStatus getRecipientStatuses() {
        return recipientStatuses;
    }

    /**
     * Sets the value of the recipientStatuses property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRecipientStatus }
     *     
     */
    public void setRecipientStatuses(ArrayOfRecipientStatus value) {
        this.recipientStatuses = value;
    }

    /**
     * Gets the value of the timeGenerated property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeGenerated() {
        return timeGenerated;
    }

    /**
     * Sets the value of the timeGenerated property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeGenerated(XMLGregorianCalendar value) {
        this.timeGenerated = value;
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
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
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
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link EnvelopeStatusCode }
     *     
     */
    public EnvelopeStatusCode getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnvelopeStatusCode }
     *     
     */
    public void setStatus(EnvelopeStatusCode value) {
        this.status = value;
    }

    /**
     * Gets the value of the created property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreated() {
        return created;
    }

    /**
     * Sets the value of the created property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreated(XMLGregorianCalendar value) {
        this.created = value;
    }

    /**
     * Gets the value of the deleted property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeleted() {
        return deleted;
    }

    /**
     * Sets the value of the deleted property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeleted(XMLGregorianCalendar value) {
        this.deleted = value;
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
     * Gets the value of the completed property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCompleted() {
        return completed;
    }

    /**
     * Sets the value of the completed property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCompleted(XMLGregorianCalendar value) {
        this.completed = value;
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
     * Gets the value of the timedOut property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimedOut() {
        return timedOut;
    }

    /**
     * Sets the value of the timedOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimedOut(XMLGregorianCalendar value) {
        this.timedOut = value;
    }

    /**
     * Gets the value of the acStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACStatus() {
        return acStatus;
    }

    /**
     * Sets the value of the acStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACStatus(String value) {
        this.acStatus = value;
    }

    /**
     * Gets the value of the acStatusDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getACStatusDate() {
        return acStatusDate;
    }

    /**
     * Sets the value of the acStatusDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setACStatusDate(XMLGregorianCalendar value) {
        this.acStatusDate = value;
    }

    /**
     * Gets the value of the acHolder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACHolder() {
        return acHolder;
    }

    /**
     * Sets the value of the acHolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACHolder(String value) {
        this.acHolder = value;
    }

    /**
     * Gets the value of the acHolderEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACHolderEmail() {
        return acHolderEmail;
    }

    /**
     * Sets the value of the acHolderEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACHolderEmail(String value) {
        this.acHolderEmail = value;
    }

    /**
     * Gets the value of the acHolderLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACHolderLocation() {
        return acHolderLocation;
    }

    /**
     * Sets the value of the acHolderLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACHolderLocation(String value) {
        this.acHolderLocation = value;
    }

    /**
     * Gets the value of the signingLocation property.
     * 
     * @return
     *     possible object is
     *     {@link SigningLocationCode }
     *     
     */
    public SigningLocationCode getSigningLocation() {
        return signingLocation;
    }

    /**
     * Sets the value of the signingLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link SigningLocationCode }
     *     
     */
    public void setSigningLocation(SigningLocationCode value) {
        this.signingLocation = value;
    }

    /**
     * Gets the value of the senderIPAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderIPAddress() {
        return senderIPAddress;
    }

    /**
     * Sets the value of the senderIPAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderIPAddress(String value) {
        this.senderIPAddress = value;
    }

    /**
     * Gets the value of the envelopePDFHash property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnvelopePDFHash() {
        return envelopePDFHash;
    }

    /**
     * Sets the value of the envelopePDFHash property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnvelopePDFHash(String value) {
        this.envelopePDFHash = value;
    }

    /**
     * Gets the value of the customFields property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCustomField }
     *     
     */
    public ArrayOfCustomField getCustomFields() {
        return customFields;
    }

    /**
     * Sets the value of the customFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCustomField }
     *     
     */
    public void setCustomFields(ArrayOfCustomField value) {
        this.customFields = value;
    }

    /**
     * Gets the value of the vaultingDetails property.
     * 
     * @return
     *     possible object is
     *     {@link VaultingDetails }
     *     
     */
    public VaultingDetails getVaultingDetails() {
        return vaultingDetails;
    }

    /**
     * Sets the value of the vaultingDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link VaultingDetails }
     *     
     */
    public void setVaultingDetails(VaultingDetails value) {
        this.vaultingDetails = value;
    }

    /**
     * Gets the value of the autoNavigation property.
     * 
     */
    public boolean isAutoNavigation() {
        return autoNavigation;
    }

    /**
     * Sets the value of the autoNavigation property.
     * 
     */
    public void setAutoNavigation(boolean value) {
        this.autoNavigation = value;
    }

    /**
     * Gets the value of the envelopeIdStamping property.
     * 
     */
    public boolean isEnvelopeIdStamping() {
        return envelopeIdStamping;
    }

    /**
     * Sets the value of the envelopeIdStamping property.
     * 
     */
    public void setEnvelopeIdStamping(boolean value) {
        this.envelopeIdStamping = value;
    }

    /**
     * Gets the value of the authoritativeCopy property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAuthoritativeCopy() {
        return authoritativeCopy;
    }

    /**
     * Sets the value of the authoritativeCopy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAuthoritativeCopy(Boolean value) {
        this.authoritativeCopy = value;
    }

    /**
     * Gets the value of the envelopeAttachment property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAttachment }
     *     
     */
    public ArrayOfAttachment getEnvelopeAttachment() {
        return envelopeAttachment;
    }

    /**
     * Sets the value of the envelopeAttachment property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAttachment }
     *     
     */
    public void setEnvelopeAttachment(ArrayOfAttachment value) {
        this.envelopeAttachment = value;
    }

    /**
     * Gets the value of the documentStatuses property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDocumentStatus }
     *     
     */
    public ArrayOfDocumentStatus getDocumentStatuses() {
        return documentStatuses;
    }

    /**
     * Sets the value of the documentStatuses property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDocumentStatus }
     *     
     */
    public void setDocumentStatuses(ArrayOfDocumentStatus value) {
        this.documentStatuses = value;
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

}
