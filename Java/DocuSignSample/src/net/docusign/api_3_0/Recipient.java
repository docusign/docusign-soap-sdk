
package net.docusign.api_3_0;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Recipient complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Recipient">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SignerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Type" type="{http://www.docusign.net/API/3.0}RecipientTypeCode"/>
 *         &lt;element name="AccessCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AddAccessCodeToEmail" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="RequireIDLookup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IDCheckConfigurationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeliveryMethod" type="{http://www.docusign.net/API/3.0}DeliveryMethod" minOccurs="0"/>
 *         &lt;element name="FaxNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LiveIDRecipientAuthentication" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="FacebookRecipientAuthentication" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="LinkedinRecipientAuthentication" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="GoogleRecipientAuthentication" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SalesforceRecipientAuthentication" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="TwitterRecipientAuthentication" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="YahooRecipientAuthentication" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="OpenIDRecipientAuthentication" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AnySocialIDRecipientAuthentication" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="PhoneAuthentication" type="{http://www.docusign.net/API/3.0}RecipientPhoneAuthentication" minOccurs="0"/>
 *         &lt;element name="SignatureInfo" type="{http://www.docusign.net/API/3.0}RecipientSignatureInfo" minOccurs="0"/>
 *         &lt;element name="CaptiveInfo" type="{http://www.docusign.net/API/3.0}RecipientCaptiveInfo" minOccurs="0"/>
 *         &lt;element name="CustomFields" type="{http://www.docusign.net/API/3.0}ArrayOfString1" minOccurs="0"/>
 *         &lt;element name="RoutingOrder" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
 *         &lt;element name="IDCheckInformationInput" type="{http://www.docusign.net/API/3.0}IDCheckInformationInput" minOccurs="0"/>
 *         &lt;element name="AutoNavigation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="RecipientAttachment" type="{http://www.docusign.net/API/3.0}ArrayOfAttachment" minOccurs="0"/>
 *         &lt;element name="Note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RoleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TemplateLocked" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="TemplateRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="TemplateAccessCodeRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="DefaultRecipient" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="EmailNotification" type="{http://www.docusign.net/API/3.0}RecipientEmailNotification" minOccurs="0"/>
 *         &lt;element name="AgentCanEditEmail" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AgentCanEditName" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SignInEachLocation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ExcludedDocuments" type="{http://www.docusign.net/API/3.0}ArrayOfPositiveInteger" minOccurs="0"/>
 *         &lt;element name="RequireSignerCertificate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InheritEmailNotificationConfiguration" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Recipient", propOrder = {
    "id",
    "userName",
    "signerName",
    "email",
    "type",
    "accessCode",
    "addAccessCodeToEmail",
    "requireIDLookup",
    "idCheckConfigurationName",
    "deliveryMethod",
    "faxNumber",
    "liveIDRecipientAuthentication",
    "facebookRecipientAuthentication",
    "linkedinRecipientAuthentication",
    "googleRecipientAuthentication",
    "salesforceRecipientAuthentication",
    "twitterRecipientAuthentication",
    "yahooRecipientAuthentication",
    "openIDRecipientAuthentication",
    "anySocialIDRecipientAuthentication",
    "phoneAuthentication",
    "signatureInfo",
    "captiveInfo",
    "customFields",
    "routingOrder",
    "idCheckInformationInput",
    "autoNavigation",
    "recipientAttachment",
    "note",
    "roleName",
    "templateLocked",
    "templateRequired",
    "templateAccessCodeRequired",
    "defaultRecipient",
    "emailNotification",
    "agentCanEditEmail",
    "agentCanEditName",
    "signInEachLocation",
    "excludedDocuments",
    "requireSignerCertificate",
    "inheritEmailNotificationConfiguration"
})
public class Recipient {

    @XmlElement(name = "ID")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger id;
    @XmlElement(name = "UserName", required = true, nillable = true)
    protected String userName;
    @XmlElement(name = "SignerName")
    protected String signerName;
    @XmlElement(name = "Email", required = true, nillable = true)
    protected String email;
    @XmlElement(name = "Type", required = true)
    protected RecipientTypeCode type;
    @XmlElement(name = "AccessCode", required = true, nillable = true)
    protected String accessCode;
    @XmlElement(name = "AddAccessCodeToEmail")
    protected Boolean addAccessCodeToEmail;
    @XmlElement(name = "RequireIDLookup")
    protected Boolean requireIDLookup;
    @XmlElement(name = "IDCheckConfigurationName")
    protected String idCheckConfigurationName;
    @XmlElement(name = "DeliveryMethod")
    protected DeliveryMethod deliveryMethod;
    @XmlElement(name = "FaxNumber")
    protected String faxNumber;
    @XmlElement(name = "LiveIDRecipientAuthentication")
    protected Boolean liveIDRecipientAuthentication;
    @XmlElement(name = "FacebookRecipientAuthentication")
    protected Boolean facebookRecipientAuthentication;
    @XmlElement(name = "LinkedinRecipientAuthentication")
    protected Boolean linkedinRecipientAuthentication;
    @XmlElement(name = "GoogleRecipientAuthentication")
    protected Boolean googleRecipientAuthentication;
    @XmlElement(name = "SalesforceRecipientAuthentication")
    protected Boolean salesforceRecipientAuthentication;
    @XmlElement(name = "TwitterRecipientAuthentication")
    protected Boolean twitterRecipientAuthentication;
    @XmlElement(name = "YahooRecipientAuthentication")
    protected Boolean yahooRecipientAuthentication;
    @XmlElement(name = "OpenIDRecipientAuthentication")
    protected Boolean openIDRecipientAuthentication;
    @XmlElement(name = "AnySocialIDRecipientAuthentication")
    protected Boolean anySocialIDRecipientAuthentication;
    @XmlElement(name = "PhoneAuthentication")
    protected RecipientPhoneAuthentication phoneAuthentication;
    @XmlElement(name = "SignatureInfo")
    protected RecipientSignatureInfo signatureInfo;
    @XmlElement(name = "CaptiveInfo")
    protected RecipientCaptiveInfo captiveInfo;
    @XmlElement(name = "CustomFields")
    protected ArrayOfString1 customFields;
    @XmlElement(name = "RoutingOrder")
    @XmlSchemaType(name = "unsignedShort")
    protected Integer routingOrder;
    @XmlElement(name = "IDCheckInformationInput")
    protected IDCheckInformationInput idCheckInformationInput;
    @XmlElement(name = "AutoNavigation")
    protected Boolean autoNavigation;
    @XmlElement(name = "RecipientAttachment")
    protected ArrayOfAttachment recipientAttachment;
    @XmlElement(name = "Note")
    protected String note;
    @XmlElement(name = "RoleName")
    protected String roleName;
    @XmlElement(name = "TemplateLocked")
    protected Boolean templateLocked;
    @XmlElement(name = "TemplateRequired")
    protected Boolean templateRequired;
    @XmlElement(name = "TemplateAccessCodeRequired")
    protected Boolean templateAccessCodeRequired;
    @XmlElement(name = "DefaultRecipient")
    protected Boolean defaultRecipient;
    @XmlElement(name = "EmailNotification")
    protected RecipientEmailNotification emailNotification;
    @XmlElement(name = "AgentCanEditEmail")
    protected Boolean agentCanEditEmail;
    @XmlElement(name = "AgentCanEditName")
    protected Boolean agentCanEditName;
    @XmlElement(name = "SignInEachLocation")
    protected Boolean signInEachLocation;
    @XmlElement(name = "ExcludedDocuments")
    protected ArrayOfPositiveInteger excludedDocuments;
    @XmlElement(name = "RequireSignerCertificate")
    protected String requireSignerCertificate;
    @XmlElement(name = "InheritEmailNotificationConfiguration")
    protected Boolean inheritEmailNotificationConfiguration;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setID(BigInteger value) {
        this.id = value;
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
     * Gets the value of the signerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignerName() {
        return signerName;
    }

    /**
     * Sets the value of the signerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignerName(String value) {
        this.signerName = value;
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
     * Gets the value of the accessCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessCode() {
        return accessCode;
    }

    /**
     * Sets the value of the accessCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessCode(String value) {
        this.accessCode = value;
    }

    /**
     * Gets the value of the addAccessCodeToEmail property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAddAccessCodeToEmail() {
        return addAccessCodeToEmail;
    }

    /**
     * Sets the value of the addAccessCodeToEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAddAccessCodeToEmail(Boolean value) {
        this.addAccessCodeToEmail = value;
    }

    /**
     * Gets the value of the requireIDLookup property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRequireIDLookup() {
        return requireIDLookup;
    }

    /**
     * Sets the value of the requireIDLookup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRequireIDLookup(Boolean value) {
        this.requireIDLookup = value;
    }

    /**
     * Gets the value of the idCheckConfigurationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDCheckConfigurationName() {
        return idCheckConfigurationName;
    }

    /**
     * Sets the value of the idCheckConfigurationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDCheckConfigurationName(String value) {
        this.idCheckConfigurationName = value;
    }

    /**
     * Gets the value of the deliveryMethod property.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryMethod }
     *     
     */
    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    /**
     * Sets the value of the deliveryMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryMethod }
     *     
     */
    public void setDeliveryMethod(DeliveryMethod value) {
        this.deliveryMethod = value;
    }

    /**
     * Gets the value of the faxNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaxNumber() {
        return faxNumber;
    }

    /**
     * Sets the value of the faxNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaxNumber(String value) {
        this.faxNumber = value;
    }

    /**
     * Gets the value of the liveIDRecipientAuthentication property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLiveIDRecipientAuthentication() {
        return liveIDRecipientAuthentication;
    }

    /**
     * Sets the value of the liveIDRecipientAuthentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLiveIDRecipientAuthentication(Boolean value) {
        this.liveIDRecipientAuthentication = value;
    }

    /**
     * Gets the value of the facebookRecipientAuthentication property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFacebookRecipientAuthentication() {
        return facebookRecipientAuthentication;
    }

    /**
     * Sets the value of the facebookRecipientAuthentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFacebookRecipientAuthentication(Boolean value) {
        this.facebookRecipientAuthentication = value;
    }

    /**
     * Gets the value of the linkedinRecipientAuthentication property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLinkedinRecipientAuthentication() {
        return linkedinRecipientAuthentication;
    }

    /**
     * Sets the value of the linkedinRecipientAuthentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLinkedinRecipientAuthentication(Boolean value) {
        this.linkedinRecipientAuthentication = value;
    }

    /**
     * Gets the value of the googleRecipientAuthentication property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGoogleRecipientAuthentication() {
        return googleRecipientAuthentication;
    }

    /**
     * Sets the value of the googleRecipientAuthentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGoogleRecipientAuthentication(Boolean value) {
        this.googleRecipientAuthentication = value;
    }

    /**
     * Gets the value of the salesforceRecipientAuthentication property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSalesforceRecipientAuthentication() {
        return salesforceRecipientAuthentication;
    }

    /**
     * Sets the value of the salesforceRecipientAuthentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSalesforceRecipientAuthentication(Boolean value) {
        this.salesforceRecipientAuthentication = value;
    }

    /**
     * Gets the value of the twitterRecipientAuthentication property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTwitterRecipientAuthentication() {
        return twitterRecipientAuthentication;
    }

    /**
     * Sets the value of the twitterRecipientAuthentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTwitterRecipientAuthentication(Boolean value) {
        this.twitterRecipientAuthentication = value;
    }

    /**
     * Gets the value of the yahooRecipientAuthentication property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isYahooRecipientAuthentication() {
        return yahooRecipientAuthentication;
    }

    /**
     * Sets the value of the yahooRecipientAuthentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setYahooRecipientAuthentication(Boolean value) {
        this.yahooRecipientAuthentication = value;
    }

    /**
     * Gets the value of the openIDRecipientAuthentication property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOpenIDRecipientAuthentication() {
        return openIDRecipientAuthentication;
    }

    /**
     * Sets the value of the openIDRecipientAuthentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOpenIDRecipientAuthentication(Boolean value) {
        this.openIDRecipientAuthentication = value;
    }

    /**
     * Gets the value of the anySocialIDRecipientAuthentication property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAnySocialIDRecipientAuthentication() {
        return anySocialIDRecipientAuthentication;
    }

    /**
     * Sets the value of the anySocialIDRecipientAuthentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAnySocialIDRecipientAuthentication(Boolean value) {
        this.anySocialIDRecipientAuthentication = value;
    }

    /**
     * Gets the value of the phoneAuthentication property.
     * 
     * @return
     *     possible object is
     *     {@link RecipientPhoneAuthentication }
     *     
     */
    public RecipientPhoneAuthentication getPhoneAuthentication() {
        return phoneAuthentication;
    }

    /**
     * Sets the value of the phoneAuthentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipientPhoneAuthentication }
     *     
     */
    public void setPhoneAuthentication(RecipientPhoneAuthentication value) {
        this.phoneAuthentication = value;
    }

    /**
     * Gets the value of the signatureInfo property.
     * 
     * @return
     *     possible object is
     *     {@link RecipientSignatureInfo }
     *     
     */
    public RecipientSignatureInfo getSignatureInfo() {
        return signatureInfo;
    }

    /**
     * Sets the value of the signatureInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipientSignatureInfo }
     *     
     */
    public void setSignatureInfo(RecipientSignatureInfo value) {
        this.signatureInfo = value;
    }

    /**
     * Gets the value of the captiveInfo property.
     * 
     * @return
     *     possible object is
     *     {@link RecipientCaptiveInfo }
     *     
     */
    public RecipientCaptiveInfo getCaptiveInfo() {
        return captiveInfo;
    }

    /**
     * Sets the value of the captiveInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipientCaptiveInfo }
     *     
     */
    public void setCaptiveInfo(RecipientCaptiveInfo value) {
        this.captiveInfo = value;
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
     * Gets the value of the routingOrder property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRoutingOrder() {
        return routingOrder;
    }

    /**
     * Sets the value of the routingOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRoutingOrder(Integer value) {
        this.routingOrder = value;
    }

    /**
     * Gets the value of the idCheckInformationInput property.
     * 
     * @return
     *     possible object is
     *     {@link IDCheckInformationInput }
     *     
     */
    public IDCheckInformationInput getIDCheckInformationInput() {
        return idCheckInformationInput;
    }

    /**
     * Sets the value of the idCheckInformationInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link IDCheckInformationInput }
     *     
     */
    public void setIDCheckInformationInput(IDCheckInformationInput value) {
        this.idCheckInformationInput = value;
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
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Gets the value of the roleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the value of the roleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleName(String value) {
        this.roleName = value;
    }

    /**
     * Gets the value of the templateLocked property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTemplateLocked() {
        return templateLocked;
    }

    /**
     * Sets the value of the templateLocked property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTemplateLocked(Boolean value) {
        this.templateLocked = value;
    }

    /**
     * Gets the value of the templateRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTemplateRequired() {
        return templateRequired;
    }

    /**
     * Sets the value of the templateRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTemplateRequired(Boolean value) {
        this.templateRequired = value;
    }

    /**
     * Gets the value of the templateAccessCodeRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTemplateAccessCodeRequired() {
        return templateAccessCodeRequired;
    }

    /**
     * Sets the value of the templateAccessCodeRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTemplateAccessCodeRequired(Boolean value) {
        this.templateAccessCodeRequired = value;
    }

    /**
     * Gets the value of the defaultRecipient property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDefaultRecipient() {
        return defaultRecipient;
    }

    /**
     * Sets the value of the defaultRecipient property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDefaultRecipient(Boolean value) {
        this.defaultRecipient = value;
    }

    /**
     * Gets the value of the emailNotification property.
     * 
     * @return
     *     possible object is
     *     {@link RecipientEmailNotification }
     *     
     */
    public RecipientEmailNotification getEmailNotification() {
        return emailNotification;
    }

    /**
     * Sets the value of the emailNotification property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipientEmailNotification }
     *     
     */
    public void setEmailNotification(RecipientEmailNotification value) {
        this.emailNotification = value;
    }

    /**
     * Gets the value of the agentCanEditEmail property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAgentCanEditEmail() {
        return agentCanEditEmail;
    }

    /**
     * Sets the value of the agentCanEditEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAgentCanEditEmail(Boolean value) {
        this.agentCanEditEmail = value;
    }

    /**
     * Gets the value of the agentCanEditName property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAgentCanEditName() {
        return agentCanEditName;
    }

    /**
     * Sets the value of the agentCanEditName property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAgentCanEditName(Boolean value) {
        this.agentCanEditName = value;
    }

    /**
     * Gets the value of the signInEachLocation property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSignInEachLocation() {
        return signInEachLocation;
    }

    /**
     * Sets the value of the signInEachLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSignInEachLocation(Boolean value) {
        this.signInEachLocation = value;
    }

    /**
     * Gets the value of the excludedDocuments property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPositiveInteger }
     *     
     */
    public ArrayOfPositiveInteger getExcludedDocuments() {
        return excludedDocuments;
    }

    /**
     * Sets the value of the excludedDocuments property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPositiveInteger }
     *     
     */
    public void setExcludedDocuments(ArrayOfPositiveInteger value) {
        this.excludedDocuments = value;
    }

    /**
     * Gets the value of the requireSignerCertificate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequireSignerCertificate() {
        return requireSignerCertificate;
    }

    /**
     * Sets the value of the requireSignerCertificate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequireSignerCertificate(String value) {
        this.requireSignerCertificate = value;
    }

    /**
     * Gets the value of the inheritEmailNotificationConfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInheritEmailNotificationConfiguration() {
        return inheritEmailNotificationConfiguration;
    }

    /**
     * Sets the value of the inheritEmailNotificationConfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInheritEmailNotificationConfiguration(Boolean value) {
        this.inheritEmailNotificationConfiguration = value;
    }

}
