
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TemplateReferences" type="{http://www.docusign.net/API/3.0}ArrayOfTemplateReference" minOccurs="0"/>
 *         &lt;element name="Recipients" type="{http://www.docusign.net/API/3.0}ArrayOfRecipient1" minOccurs="0"/>
 *         &lt;element name="EnvelopeInformation" type="{http://www.docusign.net/API/3.0}EnvelopeInformation" minOccurs="0"/>
 *         &lt;element name="ActivateEnvelope" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "templateReferences",
    "recipients",
    "envelopeInformation",
    "activateEnvelope"
})
@XmlRootElement(name = "CreateEnvelopeFromTemplates")
public class CreateEnvelopeFromTemplates {

    @XmlElement(name = "TemplateReferences")
    protected ArrayOfTemplateReference templateReferences;
    @XmlElement(name = "Recipients")
    protected ArrayOfRecipient1 recipients;
    @XmlElement(name = "EnvelopeInformation")
    protected EnvelopeInformation envelopeInformation;
    @XmlElement(name = "ActivateEnvelope")
    protected boolean activateEnvelope;

    /**
     * Gets the value of the templateReferences property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTemplateReference }
     *     
     */
    public ArrayOfTemplateReference getTemplateReferences() {
        return templateReferences;
    }

    /**
     * Sets the value of the templateReferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTemplateReference }
     *     
     */
    public void setTemplateReferences(ArrayOfTemplateReference value) {
        this.templateReferences = value;
    }

    /**
     * Gets the value of the recipients property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRecipient1 }
     *     
     */
    public ArrayOfRecipient1 getRecipients() {
        return recipients;
    }

    /**
     * Sets the value of the recipients property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRecipient1 }
     *     
     */
    public void setRecipients(ArrayOfRecipient1 value) {
        this.recipients = value;
    }

    /**
     * Gets the value of the envelopeInformation property.
     * 
     * @return
     *     possible object is
     *     {@link EnvelopeInformation }
     *     
     */
    public EnvelopeInformation getEnvelopeInformation() {
        return envelopeInformation;
    }

    /**
     * Sets the value of the envelopeInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnvelopeInformation }
     *     
     */
    public void setEnvelopeInformation(EnvelopeInformation value) {
        this.envelopeInformation = value;
    }

    /**
     * Gets the value of the activateEnvelope property.
     * 
     */
    public boolean isActivateEnvelope() {
        return activateEnvelope;
    }

    /**
     * Sets the value of the activateEnvelope property.
     * 
     */
    public void setActivateEnvelope(boolean value) {
        this.activateEnvelope = value;
    }

}
