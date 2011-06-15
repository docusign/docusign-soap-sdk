
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Correction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Correction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EnvelopeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnvelopeSettingsCorrection" type="{http://www.docusign.net/API/3.0}EnvelopeSettings" minOccurs="0"/>
 *         &lt;element name="RecipientCorrections" type="{http://www.docusign.net/API/3.0}ArrayOfRecipientCorrection" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Correction", propOrder = {
    "envelopeID",
    "envelopeSettingsCorrection",
    "recipientCorrections"
})
public class Correction {

    @XmlElement(name = "EnvelopeID")
    protected String envelopeID;
    @XmlElement(name = "EnvelopeSettingsCorrection")
    protected EnvelopeSettings envelopeSettingsCorrection;
    @XmlElement(name = "RecipientCorrections")
    protected ArrayOfRecipientCorrection recipientCorrections;

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
     * Gets the value of the envelopeSettingsCorrection property.
     * 
     * @return
     *     possible object is
     *     {@link EnvelopeSettings }
     *     
     */
    public EnvelopeSettings getEnvelopeSettingsCorrection() {
        return envelopeSettingsCorrection;
    }

    /**
     * Sets the value of the envelopeSettingsCorrection property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnvelopeSettings }
     *     
     */
    public void setEnvelopeSettingsCorrection(EnvelopeSettings value) {
        this.envelopeSettingsCorrection = value;
    }

    /**
     * Gets the value of the recipientCorrections property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRecipientCorrection }
     *     
     */
    public ArrayOfRecipientCorrection getRecipientCorrections() {
        return recipientCorrections;
    }

    /**
     * Sets the value of the recipientCorrections property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRecipientCorrection }
     *     
     */
    public void setRecipientCorrections(ArrayOfRecipientCorrection value) {
        this.recipientCorrections = value;
    }

}
