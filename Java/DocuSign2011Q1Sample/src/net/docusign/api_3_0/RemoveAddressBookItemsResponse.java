
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
 *         &lt;element name="RemoveAddressBookItemsResult" type="{http://www.docusign.net/API/3.0}UpdateAddressBookResult" minOccurs="0"/>
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
    "removeAddressBookItemsResult"
})
@XmlRootElement(name = "RemoveAddressBookItemsResponse")
public class RemoveAddressBookItemsResponse {

    @XmlElement(name = "RemoveAddressBookItemsResult")
    protected UpdateAddressBookResult removeAddressBookItemsResult;

    /**
     * Gets the value of the removeAddressBookItemsResult property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateAddressBookResult }
     *     
     */
    public UpdateAddressBookResult getRemoveAddressBookItemsResult() {
        return removeAddressBookItemsResult;
    }

    /**
     * Sets the value of the removeAddressBookItemsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateAddressBookResult }
     *     
     */
    public void setRemoveAddressBookItemsResult(UpdateAddressBookResult value) {
        this.removeAddressBookItemsResult = value;
    }

}
