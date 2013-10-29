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
 * <p>Java class for DeleteEnvelopesArg complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeleteEnvelopesArg">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EnvelopeIds" type="{http://www.docusign.net/API/3.0}ArrayOfString2" minOccurs="0"/>
 *         &lt;element name="AccountId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FolderFrom" type="{http://www.docusign.net/API/3.0}FolderTypeInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeleteEnvelopesArg", propOrder = {
    "envelopeIds",
    "accountId",
    "folderFrom"
})
public class DeleteEnvelopesArg {

    @XmlElement(name = "EnvelopeIds")
    protected ArrayOfString2 envelopeIds;
    @XmlElement(name = "AccountId")
    protected String accountId;
    @XmlElement(name = "FolderFrom")
    protected FolderTypeInfo folderFrom;

    /**
     * Gets the value of the envelopeIds property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString2 }
     *     
     */
    public ArrayOfString2 getEnvelopeIds() {
        return envelopeIds;
    }

    /**
     * Sets the value of the envelopeIds property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString2 }
     *     
     */
    public void setEnvelopeIds(ArrayOfString2 value) {
        this.envelopeIds = value;
    }

    /**
     * Gets the value of the accountId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Sets the value of the accountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountId(String value) {
        this.accountId = value;
    }

    /**
     * Gets the value of the folderFrom property.
     * 
     * @return
     *     possible object is
     *     {@link FolderTypeInfo }
     *     
     */
    public FolderTypeInfo getFolderFrom() {
        return folderFrom;
    }

    /**
     * Sets the value of the folderFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link FolderTypeInfo }
     *     
     */
    public void setFolderFrom(FolderTypeInfo value) {
        this.folderFrom = value;
    }

}
