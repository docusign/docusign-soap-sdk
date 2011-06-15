
package net.docusign.api_3_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfAddressBookItem1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfAddressBookItem1">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AddressBookItem" type="{http://www.docusign.net/API/3.0}AddressBookItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfAddressBookItem1", propOrder = {
    "addressBookItem"
})
public class ArrayOfAddressBookItem1 {

    @XmlElement(name = "AddressBookItem")
    protected List<AddressBookItem> addressBookItem;

    /**
     * Gets the value of the addressBookItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addressBookItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddressBookItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddressBookItem }
     * 
     * 
     */
    public List<AddressBookItem> getAddressBookItem() {
        if (addressBookItem == null) {
            addressBookItem = new ArrayList<AddressBookItem>();
        }
        return this.addressBookItem;
    }

}
