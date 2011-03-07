
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AnchorTab complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AnchorTab">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AnchorTabString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="XOffset" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="YOffset" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="Unit" type="{http://www.docusign.net/API/3.0}UnitTypeCode" minOccurs="0"/>
 *         &lt;element name="IgnoreIfNotPresent" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnchorTab", propOrder = {
    "anchorTabString",
    "xOffset",
    "yOffset",
    "unit",
    "ignoreIfNotPresent"
})
public class AnchorTab {

    @XmlElement(name = "AnchorTabString")
    protected String anchorTabString;
    @XmlElement(name = "XOffset", defaultValue = "0")
    protected Double xOffset;
    @XmlElement(name = "YOffset", defaultValue = "0")
    protected Double yOffset;
    @XmlElement(name = "Unit")
    protected UnitTypeCode unit;
    @XmlElement(name = "IgnoreIfNotPresent")
    protected Boolean ignoreIfNotPresent;

    /**
     * Gets the value of the anchorTabString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnchorTabString() {
        return anchorTabString;
    }

    /**
     * Sets the value of the anchorTabString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnchorTabString(String value) {
        this.anchorTabString = value;
    }

    /**
     * Gets the value of the xOffset property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getXOffset() {
        return xOffset;
    }

    /**
     * Sets the value of the xOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setXOffset(Double value) {
        this.xOffset = value;
    }

    /**
     * Gets the value of the yOffset property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getYOffset() {
        return yOffset;
    }

    /**
     * Sets the value of the yOffset property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setYOffset(Double value) {
        this.yOffset = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link UnitTypeCode }
     *     
     */
    public UnitTypeCode getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitTypeCode }
     *     
     */
    public void setUnit(UnitTypeCode value) {
        this.unit = value;
    }

    /**
     * Gets the value of the ignoreIfNotPresent property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIgnoreIfNotPresent() {
        return ignoreIfNotPresent;
    }

    /**
     * Sets the value of the ignoreIfNotPresent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIgnoreIfNotPresent(Boolean value) {
        this.ignoreIfNotPresent = value;
    }

}
