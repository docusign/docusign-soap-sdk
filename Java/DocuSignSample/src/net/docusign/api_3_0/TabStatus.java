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

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TabStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TabStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TabType" type="{http://www.docusign.net/API/3.0}TabTypeCode"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="XPosition" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="YPosition" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="Signed" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="TabLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TabName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TabValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DocumentID" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="PageNumber" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="OriginalValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ValidationPattern" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RoleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ListValues" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ListSelectedValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ScaleValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="CustomTabType" type="{http://www.docusign.net/API/3.0}CustomTabType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TabStatus", propOrder = {
    "tabType",
    "status",
    "xPosition",
    "yPosition",
    "signed",
    "tabLabel",
    "tabName",
    "tabValue",
    "documentID",
    "pageNumber",
    "originalValue",
    "validationPattern",
    "roleName",
    "listValues",
    "listSelectedValue",
    "scaleValue",
    "customTabType"
})
public class TabStatus {

    @XmlElement(name = "TabType", required = true)
    protected TabTypeCode tabType;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "XPosition")
    protected double xPosition;
    @XmlElement(name = "YPosition")
    protected double yPosition;
    @XmlElement(name = "Signed")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar signed;
    @XmlElement(name = "TabLabel")
    protected String tabLabel;
    @XmlElement(name = "TabName")
    protected String tabName;
    @XmlElement(name = "TabValue")
    protected String tabValue;
    @XmlElement(name = "DocumentID")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger documentID;
    @XmlElement(name = "PageNumber")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger pageNumber;
    @XmlElement(name = "OriginalValue")
    protected String originalValue;
    @XmlElement(name = "ValidationPattern")
    protected String validationPattern;
    @XmlElement(name = "RoleName")
    protected String roleName;
    @XmlElement(name = "ListValues")
    protected String listValues;
    @XmlElement(name = "ListSelectedValue")
    protected String listSelectedValue;
    @XmlElement(name = "ScaleValue")
    protected BigDecimal scaleValue;
    @XmlElement(name = "CustomTabType")
    protected CustomTabType customTabType;

    /**
     * Gets the value of the tabType property.
     * 
     * @return
     *     possible object is
     *     {@link TabTypeCode }
     *     
     */
    public TabTypeCode getTabType() {
        return tabType;
    }

    /**
     * Sets the value of the tabType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TabTypeCode }
     *     
     */
    public void setTabType(TabTypeCode value) {
        this.tabType = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the xPosition property.
     * 
     */
    public double getXPosition() {
        return xPosition;
    }

    /**
     * Sets the value of the xPosition property.
     * 
     */
    public void setXPosition(double value) {
        this.xPosition = value;
    }

    /**
     * Gets the value of the yPosition property.
     * 
     */
    public double getYPosition() {
        return yPosition;
    }

    /**
     * Sets the value of the yPosition property.
     * 
     */
    public void setYPosition(double value) {
        this.yPosition = value;
    }

    /**
     * Gets the value of the signed property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSigned() {
        return signed;
    }

    /**
     * Sets the value of the signed property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSigned(XMLGregorianCalendar value) {
        this.signed = value;
    }

    /**
     * Gets the value of the tabLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTabLabel() {
        return tabLabel;
    }

    /**
     * Sets the value of the tabLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTabLabel(String value) {
        this.tabLabel = value;
    }

    /**
     * Gets the value of the tabName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTabName() {
        return tabName;
    }

    /**
     * Sets the value of the tabName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTabName(String value) {
        this.tabName = value;
    }

    /**
     * Gets the value of the tabValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTabValue() {
        return tabValue;
    }

    /**
     * Sets the value of the tabValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTabValue(String value) {
        this.tabValue = value;
    }

    /**
     * Gets the value of the documentID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDocumentID() {
        return documentID;
    }

    /**
     * Sets the value of the documentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDocumentID(BigInteger value) {
        this.documentID = value;
    }

    /**
     * Gets the value of the pageNumber property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPageNumber() {
        return pageNumber;
    }

    /**
     * Sets the value of the pageNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPageNumber(BigInteger value) {
        this.pageNumber = value;
    }

    /**
     * Gets the value of the originalValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalValue() {
        return originalValue;
    }

    /**
     * Sets the value of the originalValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalValue(String value) {
        this.originalValue = value;
    }

    /**
     * Gets the value of the validationPattern property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidationPattern() {
        return validationPattern;
    }

    /**
     * Sets the value of the validationPattern property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidationPattern(String value) {
        this.validationPattern = value;
    }

    /**
     * Gets the value of the roleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Sets the value of the roleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleName(String value) {
        this.roleName = value;
    }

    /**
     * Gets the value of the listValues property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListValues() {
        return listValues;
    }

    /**
     * Sets the value of the listValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListValues(String value) {
        this.listValues = value;
    }

    /**
     * Gets the value of the listSelectedValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListSelectedValue() {
        return listSelectedValue;
    }

    /**
     * Sets the value of the listSelectedValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListSelectedValue(String value) {
        this.listSelectedValue = value;
    }

    /**
     * Gets the value of the scaleValue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getScaleValue() {
        return scaleValue;
    }

    /**
     * Sets the value of the scaleValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setScaleValue(BigDecimal value) {
        this.scaleValue = value;
    }

    /**
     * Gets the value of the customTabType property.
     * 
     * @return
     *     possible object is
     *     {@link CustomTabType }
     *     
     */
    public CustomTabType getCustomTabType() {
        return customTabType;
    }

    /**
     * Sets the value of the customTabType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomTabType }
     *     
     */
    public void setCustomTabType(CustomTabType value) {
        this.customTabType = value;
    }

}
