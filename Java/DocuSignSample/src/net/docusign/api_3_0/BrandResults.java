
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BrandResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BrandResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecipientBrandIdDefault" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SenderBrandIdDefault" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BrandResultItems" type="{http://www.docusign.net/API/3.0}ArrayOfBrandResultItem" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BrandResults", propOrder = {
    "recipientBrandIdDefault",
    "senderBrandIdDefault",
    "brandResultItems"
})
public class BrandResults {

    @XmlElement(name = "RecipientBrandIdDefault")
    protected String recipientBrandIdDefault;
    @XmlElement(name = "SenderBrandIdDefault")
    protected String senderBrandIdDefault;
    @XmlElement(name = "BrandResultItems")
    protected ArrayOfBrandResultItem brandResultItems;

    /**
     * Gets the value of the recipientBrandIdDefault property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecipientBrandIdDefault() {
        return recipientBrandIdDefault;
    }

    /**
     * Sets the value of the recipientBrandIdDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecipientBrandIdDefault(String value) {
        this.recipientBrandIdDefault = value;
    }

    /**
     * Gets the value of the senderBrandIdDefault property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSenderBrandIdDefault() {
        return senderBrandIdDefault;
    }

    /**
     * Sets the value of the senderBrandIdDefault property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSenderBrandIdDefault(String value) {
        this.senderBrandIdDefault = value;
    }

    /**
     * Gets the value of the brandResultItems property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfBrandResultItem }
     *     
     */
    public ArrayOfBrandResultItem getBrandResultItems() {
        return brandResultItems;
    }

    /**
     * Sets the value of the brandResultItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfBrandResultItem }
     *     
     */
    public void setBrandResultItems(ArrayOfBrandResultItem value) {
        this.brandResultItems = value;
    }

}
