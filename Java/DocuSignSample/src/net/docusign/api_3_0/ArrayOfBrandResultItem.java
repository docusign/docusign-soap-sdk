
package net.docusign.api_3_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfBrandResultItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfBrandResultItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BrandResultItem" type="{http://www.docusign.net/API/3.0}BrandResultItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfBrandResultItem", propOrder = {
    "brandResultItem"
})
public class ArrayOfBrandResultItem {

    @XmlElement(name = "BrandResultItem")
    protected List<BrandResultItem> brandResultItem;

    /**
     * Gets the value of the brandResultItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the brandResultItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBrandResultItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BrandResultItem }
     * 
     * 
     */
    public List<BrandResultItem> getBrandResultItem() {
        if (brandResultItem == null) {
            brandResultItem = new ArrayList<BrandResultItem>();
        }
        return this.brandResultItem;
    }

}
