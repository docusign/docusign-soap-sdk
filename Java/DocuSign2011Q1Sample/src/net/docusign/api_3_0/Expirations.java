
package net.docusign.api_3_0;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Expirations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Expirations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExpireEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="ExpireAfter" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="ExpireWarn" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Expirations", propOrder = {
    "expireEnabled",
    "expireAfter",
    "expireWarn"
})
public class Expirations {

    @XmlElement(name = "ExpireEnabled")
    protected boolean expireEnabled;
    @XmlElement(name = "ExpireAfter")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger expireAfter;
    @XmlElement(name = "ExpireWarn")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger expireWarn;

    /**
     * Gets the value of the expireEnabled property.
     * 
     */
    public boolean isExpireEnabled() {
        return expireEnabled;
    }

    /**
     * Sets the value of the expireEnabled property.
     * 
     */
    public void setExpireEnabled(boolean value) {
        this.expireEnabled = value;
    }

    /**
     * Gets the value of the expireAfter property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExpireAfter() {
        return expireAfter;
    }

    /**
     * Sets the value of the expireAfter property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExpireAfter(BigInteger value) {
        this.expireAfter = value;
    }

    /**
     * Gets the value of the expireWarn property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExpireWarn() {
        return expireWarn;
    }

    /**
     * Sets the value of the expireWarn property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExpireWarn(BigInteger value) {
        this.expireWarn = value;
    }

}
