
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnvelopeEvent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnvelopeEvent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EnvelopeEventStatusCode" type="{http://www.docusign.net/API/3.0}EnvelopeEventStatusCode"/>
 *         &lt;element name="IncludeDocuments" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnvelopeEvent", propOrder = {
    "envelopeEventStatusCode",
    "includeDocuments"
})
public class EnvelopeEvent {

    @XmlElement(name = "EnvelopeEventStatusCode", required = true)
    protected EnvelopeEventStatusCode envelopeEventStatusCode;
    @XmlElement(name = "IncludeDocuments")
    protected boolean includeDocuments;

    /**
     * Gets the value of the envelopeEventStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link EnvelopeEventStatusCode }
     *     
     */
    public EnvelopeEventStatusCode getEnvelopeEventStatusCode() {
        return envelopeEventStatusCode;
    }

    /**
     * Sets the value of the envelopeEventStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnvelopeEventStatusCode }
     *     
     */
    public void setEnvelopeEventStatusCode(EnvelopeEventStatusCode value) {
        this.envelopeEventStatusCode = value;
    }

    /**
     * Gets the value of the includeDocuments property.
     * 
     */
    public boolean isIncludeDocuments() {
        return includeDocuments;
    }

    /**
     * Sets the value of the includeDocuments property.
     * 
     */
    public void setIncludeDocuments(boolean value) {
        this.includeDocuments = value;
    }

}
