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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UpdateAddressBookResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdateAddressBookResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Success" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AddressBookItems" type="{http://www.docusign.net/API/3.0}ArrayOfAddressBookItem1" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdateAddressBookResult", propOrder = {
    "success",
    "addressBookItems"
})
public class UpdateAddressBookResult {

    @XmlElement(name = "Success")
    protected boolean success;
    @XmlElement(name = "AddressBookItems")
    protected ArrayOfAddressBookItem1 addressBookItems;

    /**
     * Gets the value of the success property.
     * 
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets the value of the success property.
     * 
     */
    public void setSuccess(boolean value) {
        this.success = value;
    }

    /**
     * Gets the value of the addressBookItems property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAddressBookItem1 }
     *     
     */
    public ArrayOfAddressBookItem1 getAddressBookItems() {
        return addressBookItems;
    }

    /**
     * Sets the value of the addressBookItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAddressBookItem1 }
     *     
     */
    public void setAddressBookItems(ArrayOfAddressBookItem1 value) {
        this.addressBookItems = value;
    }

}
