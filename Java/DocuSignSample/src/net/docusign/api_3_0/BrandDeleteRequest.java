
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BrandDeleteRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BrandDeleteRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BrandRequestItems" type="{http://www.docusign.net/API/3.0}ArrayOfBrandRequestItem" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BrandDeleteRequest", propOrder = {
    "brandRequestItems"
})
public class BrandDeleteRequest {

    @XmlElement(name = "BrandRequestItems")
    protected ArrayOfBrandRequestItem brandRequestItems;

    /**
     * Gets the value of the brandRequestItems property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfBrandRequestItem }
     *     
     */
    public ArrayOfBrandRequestItem getBrandRequestItems() {
        return brandRequestItems;
    }

    /**
     * Sets the value of the brandRequestItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfBrandRequestItem }
     *     
     */
    public void setBrandRequestItems(ArrayOfBrandRequestItem value) {
        this.brandRequestItems = value;
    }

}
