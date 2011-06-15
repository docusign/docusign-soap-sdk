
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RecipientCorrection complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RecipientCorrection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PreviousUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PreviousEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PreviousRoutingOrder" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *         &lt;element name="PreviousSignerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CorrectedUserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CorrectedSignerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CorrectedEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CorrectedCaptiveInfo" type="{http://www.docusign.net/API/3.0}RecipientCorrectionCorrectedCaptiveInfo" minOccurs="0"/>
 *         &lt;element name="CorrectedAccessCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CorrectedAccessCodeRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CorrectedRequireIDLookup" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CorrectedIDCheckConfigurationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CorrectedRoutingOrder" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
 *         &lt;element name="CorrectedAutoNavigation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CorrectedIDCheckInformationInput" type="{http://www.docusign.net/API/3.0}IDCheckInformationInput" minOccurs="0"/>
 *         &lt;element name="Resend" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecipientCorrection", propOrder = {
    "previousUserName",
    "previousEmail",
    "previousRoutingOrder",
    "previousSignerName",
    "correctedUserName",
    "correctedSignerName",
    "correctedEmail",
    "correctedCaptiveInfo",
    "correctedAccessCode",
    "correctedAccessCodeRequired",
    "correctedRequireIDLookup",
    "correctedIDCheckConfigurationName",
    "correctedRoutingOrder",
    "correctedAutoNavigation",
    "correctedIDCheckInformationInput",
    "resend"
})
public class RecipientCorrection {

    @XmlElement(name = "PreviousUserName")
    protected String previousUserName;
    @XmlElement(name = "PreviousEmail")
    protected String previousEmail;
    @XmlElement(name = "PreviousRoutingOrder")
    @XmlSchemaType(name = "unsignedShort")
    protected int previousRoutingOrder;
    @XmlElement(name = "PreviousSignerName")
    protected String previousSignerName;
    @XmlElement(name = "CorrectedUserName")
    protected String correctedUserName;
    @XmlElement(name = "CorrectedSignerName")
    protected String correctedSignerName;
    @XmlElement(name = "CorrectedEmail")
    protected String correctedEmail;
    @XmlElement(name = "CorrectedCaptiveInfo")
    protected RecipientCorrectionCorrectedCaptiveInfo correctedCaptiveInfo;
    @XmlElement(name = "CorrectedAccessCode")
    protected String correctedAccessCode;
    @XmlElement(name = "CorrectedAccessCodeRequired")
    protected Boolean correctedAccessCodeRequired;
    @XmlElement(name = "CorrectedRequireIDLookup")
    protected Boolean correctedRequireIDLookup;
    @XmlElement(name = "CorrectedIDCheckConfigurationName")
    protected String correctedIDCheckConfigurationName;
    @XmlElement(name = "CorrectedRoutingOrder")
    @XmlSchemaType(name = "unsignedShort")
    protected Integer correctedRoutingOrder;
    @XmlElement(name = "CorrectedAutoNavigation")
    protected Boolean correctedAutoNavigation;
    @XmlElement(name = "CorrectedIDCheckInformationInput")
    protected IDCheckInformationInput correctedIDCheckInformationInput;
    @XmlElement(name = "Resend")
    protected Boolean resend;

    /**
     * Gets the value of the previousUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreviousUserName() {
        return previousUserName;
    }

    /**
     * Sets the value of the previousUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreviousUserName(String value) {
        this.previousUserName = value;
    }

    /**
     * Gets the value of the previousEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreviousEmail() {
        return previousEmail;
    }

    /**
     * Sets the value of the previousEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreviousEmail(String value) {
        this.previousEmail = value;
    }

    /**
     * Gets the value of the previousRoutingOrder property.
     * 
     */
    public int getPreviousRoutingOrder() {
        return previousRoutingOrder;
    }

    /**
     * Sets the value of the previousRoutingOrder property.
     * 
     */
    public void setPreviousRoutingOrder(int value) {
        this.previousRoutingOrder = value;
    }

    /**
     * Gets the value of the previousSignerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreviousSignerName() {
        return previousSignerName;
    }

    /**
     * Sets the value of the previousSignerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreviousSignerName(String value) {
        this.previousSignerName = value;
    }

    /**
     * Gets the value of the correctedUserName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrectedUserName() {
        return correctedUserName;
    }

    /**
     * Sets the value of the correctedUserName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrectedUserName(String value) {
        this.correctedUserName = value;
    }

    /**
     * Gets the value of the correctedSignerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrectedSignerName() {
        return correctedSignerName;
    }

    /**
     * Sets the value of the correctedSignerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrectedSignerName(String value) {
        this.correctedSignerName = value;
    }

    /**
     * Gets the value of the correctedEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrectedEmail() {
        return correctedEmail;
    }

    /**
     * Sets the value of the correctedEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrectedEmail(String value) {
        this.correctedEmail = value;
    }

    /**
     * Gets the value of the correctedCaptiveInfo property.
     * 
     * @return
     *     possible object is
     *     {@link RecipientCorrectionCorrectedCaptiveInfo }
     *     
     */
    public RecipientCorrectionCorrectedCaptiveInfo getCorrectedCaptiveInfo() {
        return correctedCaptiveInfo;
    }

    /**
     * Sets the value of the correctedCaptiveInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipientCorrectionCorrectedCaptiveInfo }
     *     
     */
    public void setCorrectedCaptiveInfo(RecipientCorrectionCorrectedCaptiveInfo value) {
        this.correctedCaptiveInfo = value;
    }

    /**
     * Gets the value of the correctedAccessCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrectedAccessCode() {
        return correctedAccessCode;
    }

    /**
     * Sets the value of the correctedAccessCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrectedAccessCode(String value) {
        this.correctedAccessCode = value;
    }

    /**
     * Gets the value of the correctedAccessCodeRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCorrectedAccessCodeRequired() {
        return correctedAccessCodeRequired;
    }

    /**
     * Sets the value of the correctedAccessCodeRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCorrectedAccessCodeRequired(Boolean value) {
        this.correctedAccessCodeRequired = value;
    }

    /**
     * Gets the value of the correctedRequireIDLookup property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCorrectedRequireIDLookup() {
        return correctedRequireIDLookup;
    }

    /**
     * Sets the value of the correctedRequireIDLookup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCorrectedRequireIDLookup(Boolean value) {
        this.correctedRequireIDLookup = value;
    }

    /**
     * Gets the value of the correctedIDCheckConfigurationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrectedIDCheckConfigurationName() {
        return correctedIDCheckConfigurationName;
    }

    /**
     * Sets the value of the correctedIDCheckConfigurationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrectedIDCheckConfigurationName(String value) {
        this.correctedIDCheckConfigurationName = value;
    }

    /**
     * Gets the value of the correctedRoutingOrder property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCorrectedRoutingOrder() {
        return correctedRoutingOrder;
    }

    /**
     * Sets the value of the correctedRoutingOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCorrectedRoutingOrder(Integer value) {
        this.correctedRoutingOrder = value;
    }

    /**
     * Gets the value of the correctedAutoNavigation property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCorrectedAutoNavigation() {
        return correctedAutoNavigation;
    }

    /**
     * Sets the value of the correctedAutoNavigation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCorrectedAutoNavigation(Boolean value) {
        this.correctedAutoNavigation = value;
    }

    /**
     * Gets the value of the correctedIDCheckInformationInput property.
     * 
     * @return
     *     possible object is
     *     {@link IDCheckInformationInput }
     *     
     */
    public IDCheckInformationInput getCorrectedIDCheckInformationInput() {
        return correctedIDCheckInformationInput;
    }

    /**
     * Sets the value of the correctedIDCheckInformationInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link IDCheckInformationInput }
     *     
     */
    public void setCorrectedIDCheckInformationInput(IDCheckInformationInput value) {
        this.correctedIDCheckInformationInput = value;
    }

    /**
     * Gets the value of the resend property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isResend() {
        return resend;
    }

    /**
     * Sets the value of the resend property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setResend(Boolean value) {
        this.resend = value;
    }

}
