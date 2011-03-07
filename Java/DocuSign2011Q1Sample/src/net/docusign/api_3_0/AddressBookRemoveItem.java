
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddressBookRemoveItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressBookRemoveItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AddressBookID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressBookRemoveItem", propOrder = {
    "addressBookID"
})
public class AddressBookRemoveItem {

    @XmlElement(name = "AddressBookID")
    protected String addressBookID;

    /**
     * Gets the value of the addressBookID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressBookID() {
        return addressBookID;
    }

    /**
     * Sets the value of the addressBookID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressBookID(String value) {
        this.addressBookID = value;
    }

}
