
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
 *         &lt;element name="VoidEnvelopeResult" type="{http://www.docusign.net/API/3.0}VoidEnvelopeStatus" minOccurs="0"/>
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
    "voidEnvelopeResult"
})
@XmlRootElement(name = "VoidEnvelopeResponse")
public class VoidEnvelopeResponse {

    @XmlElement(name = "VoidEnvelopeResult")
    protected VoidEnvelopeStatus voidEnvelopeResult;

    /**
     * Gets the value of the voidEnvelopeResult property.
     * 
     * @return
     *     possible object is
     *     {@link VoidEnvelopeStatus }
     *     
     */
    public VoidEnvelopeStatus getVoidEnvelopeResult() {
        return voidEnvelopeResult;
    }

    /**
     * Sets the value of the voidEnvelopeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link VoidEnvelopeStatus }
     *     
     */
    public void setVoidEnvelopeResult(VoidEnvelopeStatus value) {
        this.voidEnvelopeResult = value;
    }

}
