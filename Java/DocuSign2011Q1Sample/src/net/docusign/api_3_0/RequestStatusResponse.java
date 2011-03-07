
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
 *         &lt;element name="RequestStatusResult" type="{http://www.docusign.net/API/3.0}EnvelopeStatus" minOccurs="0"/>
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
    "requestStatusResult"
})
@XmlRootElement(name = "RequestStatusResponse")
public class RequestStatusResponse {

    @XmlElement(name = "RequestStatusResult")
    protected EnvelopeStatus requestStatusResult;

    /**
     * Gets the value of the requestStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link EnvelopeStatus }
     *     
     */
    public EnvelopeStatus getRequestStatusResult() {
        return requestStatusResult;
    }

    /**
     * Sets the value of the requestStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnvelopeStatus }
     *     
     */
    public void setRequestStatusResult(EnvelopeStatus value) {
        this.requestStatusResult = value;
    }

}
