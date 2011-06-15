
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
 *         &lt;element name="GetAddressBookItemsResult" type="{http://www.docusign.net/API/3.0}ArrayOfAddressBookItem" minOccurs="0"/>
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
    "getAddressBookItemsResult"
})
@XmlRootElement(name = "GetAddressBookItemsResponse")
public class GetAddressBookItemsResponse {

    @XmlElement(name = "GetAddressBookItemsResult")
    protected ArrayOfAddressBookItem getAddressBookItemsResult;

    /**
     * Gets the value of the getAddressBookItemsResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAddressBookItem }
     *     
     */
    public ArrayOfAddressBookItem getGetAddressBookItemsResult() {
        return getAddressBookItemsResult;
    }

    /**
     * Sets the value of the getAddressBookItemsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAddressBookItem }
     *     
     */
    public void setGetAddressBookItemsResult(ArrayOfAddressBookItem value) {
        this.getAddressBookItemsResult = value;
    }

}
