
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
 *         &lt;element name="RequestTemplateResult" type="{http://www.docusign.net/API/3.0}EnvelopeTemplate" minOccurs="0"/>
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
    "requestTemplateResult"
})
@XmlRootElement(name = "RequestTemplateResponse")
public class RequestTemplateResponse {

    @XmlElement(name = "RequestTemplateResult")
    protected EnvelopeTemplate requestTemplateResult;

    /**
     * Gets the value of the requestTemplateResult property.
     * 
     * @return
     *     possible object is
     *     {@link EnvelopeTemplate }
     *     
     */
    public EnvelopeTemplate getRequestTemplateResult() {
        return requestTemplateResult;
    }

    /**
     * Sets the value of the requestTemplateResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnvelopeTemplate }
     *     
     */
    public void setRequestTemplateResult(EnvelopeTemplate value) {
        this.requestTemplateResult = value;
    }

}
