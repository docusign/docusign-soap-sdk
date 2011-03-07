
package net.docusign.api_3_0;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InlineTemplate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InlineTemplate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Sequence" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
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
@XmlType(name = "InlineTemplate", propOrder = {
    "sequence",
    "envelope"
})
public class InlineTemplate {

    @XmlElement(name = "Sequence")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger sequence;
    @XmlElement(name = "Envelope")
    protected Envelope envelope;

    /**
     * Gets the value of the sequence property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSequence() {
        return sequence;
    }

    /**
     * Sets the value of the sequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSequence(BigInteger value) {
        this.sequence = value;
    }

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
