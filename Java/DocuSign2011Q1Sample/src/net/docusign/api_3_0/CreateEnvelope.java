
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
 *         &lt;element name="Envelope" type="{http://www.docusign.net/API/3.0}Envelope" minOccurs="0"/>
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
    "envelope"
})
@XmlRootElement(name = "CreateEnvelope")
public class CreateEnvelope {

    @XmlElement(name = "Envelope")
    protected Envelope envelope;

    /**
     * Gets the value of the envelope property.
     * 
     * @return
     *     possible object is
     *     {@link Envelope }
     *     
     */
    public Envelope getEnvelope() {
        return envelope;
    }

    /**
     * Sets the value of the envelope property.
     * 
     * @param value
     *     allowed object is
     *     {@link Envelope }
     *     
     */
    public void setEnvelope(Envelope value) {
        this.envelope = value;
    }

}
