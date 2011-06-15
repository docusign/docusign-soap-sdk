
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequestRecipientTokenClientURLs complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestRecipientTokenClientURLs">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OnSigningComplete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnViewingComplete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnCancel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnDecline" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnSessionTimeout" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnTTLExpired" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnException" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnAccessCodeFailed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnIdCheckFailed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnFaxPending" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GenerateSignedDocumentAsynch" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestRecipientTokenClientURLs", propOrder = {
    "onSigningComplete",
    "onViewingComplete",
    "onCancel",
    "onDecline",
    "onSessionTimeout",
    "onTTLExpired",
    "onException",
    "onAccessCodeFailed",
    "onIdCheckFailed",
    "onFaxPending",
    "generateSignedDocumentAsynch"
})
public class RequestRecipientTokenClientURLs {

    @XmlElement(name = "OnSigningComplete")
    protected String onSigningComplete;
    @XmlElement(name = "OnViewingComplete")
    protected String onViewingComplete;
    @XmlElement(name = "OnCancel")
    protected String onCancel;
    @XmlElement(name = "OnDecline")
    protected String onDecline;
    @XmlElement(name = "OnSessionTimeout")
    protected String onSessionTimeout;
    @XmlElement(name = "OnTTLExpired")
    protected String onTTLExpired;
    @XmlElement(name = "OnException")
    protected String onException;
    @XmlElement(name = "OnAccessCodeFailed")
    protected String onAccessCodeFailed;
    @XmlElement(name = "OnIdCheckFailed")
    protected String onIdCheckFailed;
    @XmlElement(name = "OnFaxPending")
    protected String onFaxPending;
    @XmlElement(name = "GenerateSignedDocumentAsynch")
    protected Boolean generateSignedDocumentAsynch;

    /**
     * Gets the value of the onSigningComplete property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnSigningComplete() {
        return onSigningComplete;
    }

    /**
     * Sets the value of the onSigningComplete property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnSigningComplete(String value) {
        this.onSigningComplete = value;
    }

    /**
     * Gets the value of the onViewingComplete property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnViewingComplete() {
        return onViewingComplete;
    }

    /**
     * Sets the value of the onViewingComplete property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnViewingComplete(String value) {
        this.onViewingComplete = value;
    }

    /**
     * Gets the value of the onCancel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnCancel() {
        return onCancel;
    }

    /**
     * Sets the value of the onCancel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnCancel(String value) {
        this.onCancel = value;
    }

    /**
     * Gets the value of the onDecline property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnDecline() {
        return onDecline;
    }

    /**
     * Sets the value of the onDecline property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnDecline(String value) {
        this.onDecline = value;
    }

    /**
     * Gets the value of the onSessionTimeout property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnSessionTimeout() {
        return onSessionTimeout;
    }

    /**
     * Sets the value of the onSessionTimeout property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnSessionTimeout(String value) {
        this.onSessionTimeout = value;
    }

    /**
     * Gets the value of the onTTLExpired property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnTTLExpired() {
        return onTTLExpired;
    }

    /**
     * Sets the value of the onTTLExpired property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnTTLExpired(String value) {
        this.onTTLExpired = value;
    }

    /**
     * Gets the value of the onException property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnException() {
        return onException;
    }

    /**
     * Sets the value of the onException property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnException(String value) {
        this.onException = value;
    }

    /**
     * Gets the value of the onAccessCodeFailed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnAccessCodeFailed() {
        return onAccessCodeFailed;
    }

    /**
     * Sets the value of the onAccessCodeFailed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnAccessCodeFailed(String value) {
        this.onAccessCodeFailed = value;
    }

    /**
     * Gets the value of the onIdCheckFailed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnIdCheckFailed() {
        return onIdCheckFailed;
    }

    /**
     * Sets the value of the onIdCheckFailed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnIdCheckFailed(String value) {
        this.onIdCheckFailed = value;
    }

    /**
     * Gets the value of the onFaxPending property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnFaxPending() {
        return onFaxPending;
    }

    /**
     * Sets the value of the onFaxPending property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnFaxPending(String value) {
        this.onFaxPending = value;
    }

    /**
     * Gets the value of the generateSignedDocumentAsynch property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGenerateSignedDocumentAsynch() {
        return generateSignedDocumentAsynch;
    }

    /**
     * Sets the value of the generateSignedDocumentAsynch property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGenerateSignedDocumentAsynch(Boolean value) {
        this.generateSignedDocumentAsynch = value;
    }

}
