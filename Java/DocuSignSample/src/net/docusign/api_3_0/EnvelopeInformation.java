
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnvelopeInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnvelopeInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TransactionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Asynchronous" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AccountId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EmailBlurb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SigningLocation" type="{http://www.docusign.net/API/3.0}SigningLocationCode" minOccurs="0"/>
 *         &lt;element name="CustomFields" type="{http://www.docusign.net/API/3.0}ArrayOfCustomField" minOccurs="0"/>
 *         &lt;element name="VaultingOptions" type="{http://www.docusign.net/API/3.0}VaultingOptions" minOccurs="0"/>
 *         &lt;element name="AutoNavigation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="EnvelopeIdStamping" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AuthoritativeCopy" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Notification" type="{http://www.docusign.net/API/3.0}Notification" minOccurs="0"/>
 *         &lt;element name="EnforceSignerVisibility" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="EnableWetSign" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AllowRecipientRecursion" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AllowMarkup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="EventNotification" type="{http://www.docusign.net/API/3.0}EventNotification" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnvelopeInformation", propOrder = {
    "transactionID",
    "asynchronous",
    "accountId",
    "emailBlurb",
    "subject",
    "signingLocation",
    "customFields",
    "vaultingOptions",
    "autoNavigation",
    "envelopeIdStamping",
    "authoritativeCopy",
    "notification",
    "enforceSignerVisibility",
    "enableWetSign",
    "allowRecipientRecursion",
    "allowMarkup",
    "eventNotification"
})
public class EnvelopeInformation {

    @XmlElement(name = "TransactionID")
    protected String transactionID;
    @XmlElement(name = "Asynchronous")
    protected Boolean asynchronous;
    @XmlElement(name = "AccountId")
    protected String accountId;
    @XmlElement(name = "EmailBlurb")
    protected String emailBlurb;
    @XmlElement(name = "Subject")
    protected String subject;
    @XmlElement(name = "SigningLocation")
    protected SigningLocationCode signingLocation;
    @XmlElement(name = "CustomFields")
    protected ArrayOfCustomField customFields;
    @XmlElement(name = "VaultingOptions")
    protected VaultingOptions vaultingOptions;
    @XmlElement(name = "AutoNavigation")
    protected Boolean autoNavigation;
    @XmlElement(name = "EnvelopeIdStamping")
    protected Boolean envelopeIdStamping;
    @XmlElement(name = "AuthoritativeCopy")
    protected Boolean authoritativeCopy;
    @XmlElement(name = "Notification")
    protected Notification notification;
    @XmlElement(name = "EnforceSignerVisibility")
    protected Boolean enforceSignerVisibility;
    @XmlElement(name = "EnableWetSign")
    protected Boolean enableWetSign;
    @XmlElement(name = "AllowRecipientRecursion")
    protected Boolean allowRecipientRecursion;
    @XmlElement(name = "AllowMarkup")
    protected Boolean allowMarkup;
    @XmlElement(name = "EventNotification")
    protected EventNotification eventNotification;

    /**
     * Gets the value of the transactionID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionID() {
        return transactionID;
    }

    /**
     * Sets the value of the transactionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionID(String value) {
        this.transactionID = value;
    }

    /**
     * Gets the value of the asynchronous property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAsynchronous() {
        return asynchronous;
    }

    /**
     * Sets the value of the asynchronous property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAsynchronous(Boolean value) {
        this.asynchronous = value;
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
     * Gets the value of the emailBlurb property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailBlurb() {
        return emailBlurb;
    }

    /**
     * Sets the value of the emailBlurb property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailBlurb(String value) {
        this.emailBlurb = value;
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
     * Gets the value of the vaultingOptions property.
     * 
     * @return
     *     possible object is
     *     {@link VaultingOptions }
     *     
     */
    public VaultingOptions getVaultingOptions() {
        return vaultingOptions;
    }

    /**
     * Sets the value of the vaultingOptions property.
     * 
     * @param value
     *     allowed object is
     *     {@link VaultingOptions }
     *     
     */
    public void setVaultingOptions(VaultingOptions value) {
        this.vaultingOptions = value;
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
     * Gets the value of the envelopeIdStamping property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEnvelopeIdStamping() {
        return envelopeIdStamping;
    }

    /**
     * Sets the value of the envelopeIdStamping property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnvelopeIdStamping(Boolean value) {
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
     * Gets the value of the notification property.
     * 
     * @return
     *     possible object is
     *     {@link Notification }
     *     
     */
    public Notification getNotification() {
        return notification;
    }

    /**
     * Sets the value of the notification property.
     * 
     * @param value
     *     allowed object is
     *     {@link Notification }
     *     
     */
    public void setNotification(Notification value) {
        this.notification = value;
    }

    /**
     * Gets the value of the enforceSignerVisibility property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEnforceSignerVisibility() {
        return enforceSignerVisibility;
    }

    /**
     * Sets the value of the enforceSignerVisibility property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnforceSignerVisibility(Boolean value) {
        this.enforceSignerVisibility = value;
    }

    /**
     * Gets the value of the enableWetSign property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEnableWetSign() {
        return enableWetSign;
    }

    /**
     * Sets the value of the enableWetSign property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnableWetSign(Boolean value) {
        this.enableWetSign = value;
    }

    /**
     * Gets the value of the allowRecipientRecursion property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowRecipientRecursion() {
        return allowRecipientRecursion;
    }

    /**
     * Sets the value of the allowRecipientRecursion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowRecipientRecursion(Boolean value) {
        this.allowRecipientRecursion = value;
    }

    /**
     * Gets the value of the allowMarkup property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowMarkup() {
        return allowMarkup;
    }

    /**
     * Sets the value of the allowMarkup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowMarkup(Boolean value) {
        this.allowMarkup = value;
    }

    /**
     * Gets the value of the eventNotification property.
     * 
     * @return
     *     possible object is
     *     {@link EventNotification }
     *     
     */
    public EventNotification getEventNotification() {
        return eventNotification;
    }

    /**
     * Sets the value of the eventNotification property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventNotification }
     *     
     */
    public void setEventNotification(EventNotification value) {
        this.eventNotification = value;
    }

}
