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
 * <p>Java class for TemplateReferenceFieldData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TemplateReferenceFieldData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataValues" type="{http://www.docusign.net/API/3.0}ArrayOfTemplateReferenceFieldDataDataValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemplateReferenceFieldData", propOrder = {
    "dataValues"
})
public class TemplateReferenceFieldData {

    @XmlElement(name = "DataValues")
    protected ArrayOfTemplateReferenceFieldDataDataValue dataValues;

    /**
     * Gets the value of the dataValues property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTemplateReferenceFieldDataDataValue }
     *     
     */
    public ArrayOfTemplateReferenceFieldDataDataValue getDataValues() {
        return dataValues;
    }

    /**
     * Sets the value of the dataValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTemplateReferenceFieldDataDataValue }
     *     
     */
    public void setDataValues(ArrayOfTemplateReferenceFieldDataDataValue value) {
        this.dataValues = value;
    }

}
