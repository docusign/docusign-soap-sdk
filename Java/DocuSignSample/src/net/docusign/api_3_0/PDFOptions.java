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
 * <p>Java class for PDFOptions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PDFOptions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShowChanges" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AddWaterMark" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IncludeCert" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PDFOptions", propOrder = {
    "showChanges",
    "addWaterMark",
    "includeCert"
})
public class PDFOptions {

    @XmlElement(name = "ShowChanges")
    protected boolean showChanges;
    @XmlElement(name = "AddWaterMark")
    protected boolean addWaterMark;
    @XmlElement(name = "IncludeCert")
    protected boolean includeCert;

    /**
     * Gets the value of the showChanges property.
     * 
     */
    public boolean isShowChanges() {
        return showChanges;
    }

    /**
     * Sets the value of the showChanges property.
     * 
     */
    public void setShowChanges(boolean value) {
        this.showChanges = value;
    }

    /**
     * Gets the value of the addWaterMark property.
     * 
     */
    public boolean isAddWaterMark() {
        return addWaterMark;
    }

    /**
     * Sets the value of the addWaterMark property.
     * 
     */
    public void setAddWaterMark(boolean value) {
        this.addWaterMark = value;
    }

    /**
     * Gets the value of the includeCert property.
     * 
     */
    public boolean isIncludeCert() {
        return includeCert;
    }

    /**
     * Sets the value of the includeCert property.
     * 
     */
    public void setIncludeCert(boolean value) {
        this.includeCert = value;
    }

}
