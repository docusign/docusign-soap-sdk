
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnvelopeSettings complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnvelopeSettings">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AutoNavigation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="EnvelopeIdStamping" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnvelopeSettings", propOrder = {
    "autoNavigation",
    "envelopeIdStamping"
})
public class EnvelopeSettings {

    @XmlElement(name = "AutoNavigation")
    protected Boolean autoNavigation;
    @XmlElement(name = "EnvelopeIdStamping")
    protected Boolean envelopeIdStamping;

    /**
     * Gets the value of the autoNavigation property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutoNavigation() {
        return autoNavigation;
    }

    /**
     * Sets the value of the autoNavigation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoNavigation(Boolean value) {
        this.autoNavigation = value;
    }

    /**
     * Gets the value of the envelopeIdStamping property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEnvelopeIdStamping() {
        return envelopeIdStamping;
    }

    /**
     * Sets the value of the envelopeIdStamping property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEnvelopeIdStamping(Boolean value) {
        this.envelopeIdStamping = value;
    }

}
