
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EventNotification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventNotification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LoggingEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="RequireAcknowledgment" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="UseSoapInterface" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SoapNameSpace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IncludeCertificateWithSoap" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SignMessageWithX509Cert" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IncludeDocuments" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IncludeTimeZone" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IncludeEnvelopeVoidReason" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IncludeSenderAccountAsCustomField" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="EnvelopeEvents" type="{http://www.docusign.net/API/3.0}ArrayOfEnvelopeEvent" minOccurs="0"/>
 *         &lt;element name="RecipientEvents" type="{http://www.docusign.net/API/3.0}ArrayOfRecipientEvent" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventNotification", propOrder = {
    "url",
    "loggingEnabled",
    "requireAcknowledgment",
    "useSoapInterface",
    "soapNameSpace",
    "includeCertificateWithSoap",
    "signMessageWithX509Cert",
    "includeDocuments",
    "includeTimeZone",
    "includeEnvelopeVoidReason",
    "includeSenderAccountAsCustomField",
    "envelopeEvents",
    "recipientEvents"
})
public class EventNotification {

    @XmlElement(name = "URL")
    protected String url;
    @XmlElement(name = "LoggingEnabled")
    protected Boolean loggingEnabled;
    @XmlElement(name = "RequireAcknowledgment")
    protected Boolean requireAcknowledgment;
    @XmlElement(name = "UseSoapInterface")
    protected Boolean useSoapInterface;
    @XmlElement(name = "SoapNameSpace")
    protected String soapNameSpace;
    @XmlElement(name = "IncludeCertificateWithSoap")
    protected Boolean includeCertificateWithSoap;
    @XmlElement(name = "SignMessageWithX509Cert")
    protected Boolean signMessageWithX509Cert;
    @XmlElement(name = "IncludeDocuments")
    protected Boolean includeDocuments;
    @XmlElement(name = "IncludeTimeZone")
    protected Boolean includeTimeZone;
    @XmlElement(name = "IncludeEnvelopeVoidReason")
    protected Boolean includeEnvelopeVoidReason;
    @XmlElement(name = "IncludeSenderAccountAsCustomField")
    protected Boolean includeSenderAccountAsCustomField;
    @XmlElement(name = "EnvelopeEvents")
    protected ArrayOfEnvelopeEvent envelopeEvents;
    @XmlElement(name = "RecipientEvents")
    protected ArrayOfRecipientEvent recipientEvents;

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURL() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURL(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the loggingEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLoggingEnabled() {
        return loggingEnabled;
    }

    /**
     * Sets the value of the loggingEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLoggingEnabled(Boolean value) {
        this.loggingEnabled = value;
    }

    /**
     * Gets the value of the requireAcknowledgment property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRequireAcknowledgment() {
        return requireAcknowledgment;
    }

    /**
     * Sets the value of the requireAcknowledgment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRequireAcknowledgment(Boolean value) {
        this.requireAcknowledgment = value;
    }

    /**
     * Gets the value of the useSoapInterface property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isUseSoapInterface() {
        return useSoapInterface;
    }

    /**
     * Sets the value of the useSoapInterface property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setUseSoapInterface(Boolean value) {
        this.useSoapInterface = value;
    }

    /**
     * Gets the value of the soapNameSpace property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoapNameSpace() {
        return soapNameSpace;
    }

    /**
     * Sets the value of the soapNameSpace property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoapNameSpace(String value) {
        this.soapNameSpace = value;
    }

    /**
     * Gets the value of the includeCertificateWithSoap property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeCertificateWithSoap() {
        return includeCertificateWithSoap;
    }

    /**
     * Sets the value of the includeCertificateWithSoap property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeCertificateWithSoap(Boolean value) {
        this.includeCertificateWithSoap = value;
    }

    /**
     * Gets the value of the signMessageWithX509Cert property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSignMessageWithX509Cert() {
        return signMessageWithX509Cert;
    }

    /**
     * Sets the value of the signMessageWithX509Cert property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSignMessageWithX509Cert(Boolean value) {
        this.signMessageWithX509Cert = value;
    }

    /**
     * Gets the value of the includeDocuments property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeDocuments() {
        return includeDocuments;
    }

    /**
     * Sets the value of the includeDocuments property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeDocuments(Boolean value) {
        this.includeDocuments = value;
    }

    /**
     * Gets the value of the includeTimeZone property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeTimeZone() {
        return includeTimeZone;
    }

    /**
     * Sets the value of the includeTimeZone property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeTimeZone(Boolean value) {
        this.includeTimeZone = value;
    }

    /**
     * Gets the value of the includeEnvelopeVoidReason property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeEnvelopeVoidReason() {
        return includeEnvelopeVoidReason;
    }

    /**
     * Sets the value of the includeEnvelopeVoidReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeEnvelopeVoidReason(Boolean value) {
        this.includeEnvelopeVoidReason = value;
    }

    /**
     * Gets the value of the includeSenderAccountAsCustomField property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludeSenderAccountAsCustomField() {
        return includeSenderAccountAsCustomField;
    }

    /**
     * Sets the value of the includeSenderAccountAsCustomField property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeSenderAccountAsCustomField(Boolean value) {
        this.includeSenderAccountAsCustomField = value;
    }

    /**
     * Gets the value of the envelopeEvents property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEnvelopeEvent }
     *     
     */
    public ArrayOfEnvelopeEvent getEnvelopeEvents() {
        return envelopeEvents;
    }

    /**
     * Sets the value of the envelopeEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEnvelopeEvent }
     *     
     */
    public void setEnvelopeEvents(ArrayOfEnvelopeEvent value) {
        this.envelopeEvents = value;
    }

    /**
     * Gets the value of the recipientEvents property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRecipientEvent }
     *     
     */
    public ArrayOfRecipientEvent getRecipientEvents() {
        return recipientEvents;
    }

    /**
     * Sets the value of the recipientEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRecipientEvent }
     *     
     */
    public void setRecipientEvents(ArrayOfRecipientEvent value) {
        this.recipientEvents = value;
    }

}
