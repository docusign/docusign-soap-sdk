/**
 * @copyright Copyright (C) DocuSign, Inc.  All rights reserved.
 *
 * This source code is intended only as a supplement to DocuSign SDK
 * and/or on-line documentation.
 * 
 * This sample is designed to demonstrate DocuSign features and is not intended
 * for production use. Code and policy for a production application must be
 * developed to meet the specific data and security requirements of the
 * application.
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 */
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
 *         &lt;element name="AddressBookItems" type="{http://www.docusign.net/API/3.0}ArrayOfAddressBookItem" minOccurs="0"/>
 *         &lt;element name="ReturnAddressBook" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "addressBookItems",
    "returnAddressBook"
})
@XmlRootElement(name = "UpdateAddressBookItems")
public class UpdateAddressBookItems {

    @XmlElement(name = "AddressBookItems")
    protected ArrayOfAddressBookItem addressBookItems;
    @XmlElement(name = "ReturnAddressBook")
    protected boolean returnAddressBook;

    /**
     * Gets the value of the addressBookItems property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAddressBookItem }
     *     
     */
    public ArrayOfAddressBookItem getAddressBookItems() {
        return addressBookItems;
    }

    /**
     * Sets the value of the addressBookItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAddressBookItem }
     *     
     */
    public void setAddressBookItems(ArrayOfAddressBookItem value) {
        this.addressBookItems = value;
    }

    /**
     * Gets the value of the returnAddressBook property.
     * 
     */
    public boolean isReturnAddressBook() {
        return returnAddressBook;
    }

    /**
     * Sets the value of the returnAddressBook property.
     * 
     */
    public void setReturnAddressBook(boolean value) {
        this.returnAddressBook = value;
    }

}
