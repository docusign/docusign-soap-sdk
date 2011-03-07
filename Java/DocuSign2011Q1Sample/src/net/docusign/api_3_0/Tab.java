
package net.docusign.api_3_0;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Tab complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Tab">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DocumentID" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="RecipientID" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="PageNumber" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="XPosition" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="YPosition" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *         &lt;element name="ScaleValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="AnchorTabItem" type="{http://www.docusign.net/API/3.0}AnchorTab" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://www.docusign.net/API/3.0}TabTypeCode"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TabLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomTabType" type="{http://www.docusign.net/API/3.0}CustomTabType" minOccurs="0"/>
 *         &lt;element name="CustomTabWidth" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CustomTabHeight" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CustomTabRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CustomTabLocked" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CustomTabDisableAutoSize" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CustomTabListItems" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomTabListValues" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomTabListSelectedValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomTabRadioGroupName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomTabValidationPattern" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomTabValidationMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TemplateLocked" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="TemplateRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ConditionalParentLabel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConditionalParentValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SharedTab" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="RequireInitialOnSharedTabChange" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ConcealValueOnDocument" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tab", propOrder = {
    "documentID",
    "recipientID",
    "pageNumber",
    "xPosition",
    "yPosition",
    "scaleValue",
    "anchorTabItem",
    "type",
    "name",
    "tabLabel",
    "value",
    "customTabType",
    "customTabWidth",
    "customTabHeight",
    "customTabRequired",
    "customTabLocked",
    "customTabDisableAutoSize",
    "customTabListItems",
    "customTabListValues",
    "customTabListSelectedValue",
    "customTabRadioGroupName",
    "customTabValidationPattern",
    "customTabValidationMessage",
    "templateLocked",
    "templateRequired",
    "conditionalParentLabel",
    "conditionalParentValue",
    "sharedTab",
    "requireInitialOnSharedTabChange",
    "concealValueOnDocument"
})
public class Tab {

    @XmlElement(name = "DocumentID")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger documentID;
    @XmlElement(name = "RecipientID")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger recipientID;
    @XmlElement(name = "PageNumber")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger pageNumber;
    @XmlElement(name = "XPosition")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger xPosition;
    @XmlElement(name = "YPosition")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger yPosition;
    @XmlElement(name = "ScaleValue")
    protected BigDecimal scaleValue;
    @XmlElement(name = "AnchorTabItem")
    protected AnchorTab anchorTabItem;
    @XmlElement(name = "Type", required = true)
    protected TabTypeCode type;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "TabLabel")
    protected String tabLabel;
    @XmlElement(name = "Value")
    protected String value;
    @XmlElement(name = "CustomTabType")
    protected CustomTabType customTabType;
    @XmlElement(name = "CustomTabWidth")
    protected Integer customTabWidth;
    @XmlElement(name = "CustomTabHeight")
    protected Integer customTabHeight;
    @XmlElement(name = "CustomTabRequired")
    protected Boolean customTabRequired;
    @XmlElement(name = "CustomTabLocked")
    protected Boolean customTabLocked;
    @XmlElement(name = "CustomTabDisableAutoSize")
    protected Boolean customTabDisableAutoSize;
    @XmlElement(name = "CustomTabListItems")
    protected String customTabListItems;
    @XmlElement(name = "CustomTabListValues")
    protected String customTabListValues;
    @XmlElement(name = "CustomTabListSelectedValue")
    protected String customTabListSelectedValue;
    @XmlElement(name = "CustomTabRadioGroupName")
    protected String customTabRadioGroupName;
    @XmlElement(name = "CustomTabValidationPattern")
    protected String customTabValidationPattern;
    @XmlElement(name = "CustomTabValidationMessage")
    protected String customTabValidationMessage;
    @XmlElement(name = "TemplateLocked")
    protected Boolean templateLocked;
    @XmlElement(name = "TemplateRequired")
    protected Boolean templateRequired;
    @XmlElement(name = "ConditionalParentLabel")
    protected String conditionalParentLabel;
    @XmlElement(name = "ConditionalParentValue")
    protected String conditionalParentValue;
    @XmlElement(name = "SharedTab")
    protected Boolean sharedTab;
    @XmlElement(name = "RequireInitialOnSharedTabChange")
    protected Boolean requireInitialOnSharedTabChange;
    @XmlElement(name = "ConcealValueOnDocument")
    protected Boolean concealValueOnDocument;

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
     * Gets the value of the recipientID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRecipientID() {
        return recipientID;
    }

    /**
     * Sets the value of the recipientID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRecipientID(BigInteger value) {
        this.recipientID = value;
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
     * Gets the value of the xPosition property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getXPosition() {
        return xPosition;
    }

    /**
     * Sets the value of the xPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setXPosition(BigInteger value) {
        this.xPosition = value;
    }

    /**
     * Gets the value of the yPosition property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getYPosition() {
        return yPosition;
    }

    /**
     * Sets the value of the yPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setYPosition(BigInteger value) {
        this.yPosition = value;
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
     * Gets the value of the anchorTabItem property.
     * 
     * @return
     *     possible object is
     *     {@link AnchorTab }
     *     
     */
    public AnchorTab getAnchorTabItem() {
        return anchorTabItem;
    }

    /**
     * Sets the value of the anchorTabItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link AnchorTab }
     *     
     */
    public void setAnchorTabItem(AnchorTab value) {
        this.anchorTabItem = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link TabTypeCode }
     *     
     */
    public TabTypeCode getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link TabTypeCode }
     *     
     */
    public void setType(TabTypeCode value) {
        this.type = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
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
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
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

    /**
     * Gets the value of the customTabWidth property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCustomTabWidth() {
        return customTabWidth;
    }

    /**
     * Sets the value of the customTabWidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCustomTabWidth(Integer value) {
        this.customTabWidth = value;
    }

    /**
     * Gets the value of the customTabHeight property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCustomTabHeight() {
        return customTabHeight;
    }

    /**
     * Sets the value of the customTabHeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCustomTabHeight(Integer value) {
        this.customTabHeight = value;
    }

    /**
     * Gets the value of the customTabRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCustomTabRequired() {
        return customTabRequired;
    }

    /**
     * Sets the value of the customTabRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCustomTabRequired(Boolean value) {
        this.customTabRequired = value;
    }

    /**
     * Gets the value of the customTabLocked property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCustomTabLocked() {
        return customTabLocked;
    }

    /**
     * Sets the value of the customTabLocked property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCustomTabLocked(Boolean value) {
        this.customTabLocked = value;
    }

    /**
     * Gets the value of the customTabDisableAutoSize property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCustomTabDisableAutoSize() {
        return customTabDisableAutoSize;
    }

    /**
     * Sets the value of the customTabDisableAutoSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCustomTabDisableAutoSize(Boolean value) {
        this.customTabDisableAutoSize = value;
    }

    /**
     * Gets the value of the customTabListItems property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomTabListItems() {
        return customTabListItems;
    }

    /**
     * Sets the value of the customTabListItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomTabListItems(String value) {
        this.customTabListItems = value;
    }

    /**
     * Gets the value of the customTabListValues property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomTabListValues() {
        return customTabListValues;
    }

    /**
     * Sets the value of the customTabListValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomTabListValues(String value) {
        this.customTabListValues = value;
    }

    /**
     * Gets the value of the customTabListSelectedValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomTabListSelectedValue() {
        return customTabListSelectedValue;
    }

    /**
     * Sets the value of the customTabListSelectedValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomTabListSelectedValue(String value) {
        this.customTabListSelectedValue = value;
    }

    /**
     * Gets the value of the customTabRadioGroupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomTabRadioGroupName() {
        return customTabRadioGroupName;
    }

    /**
     * Sets the value of the customTabRadioGroupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomTabRadioGroupName(String value) {
        this.customTabRadioGroupName = value;
    }

    /**
     * Gets the value of the customTabValidationPattern property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomTabValidationPattern() {
        return customTabValidationPattern;
    }

    /**
     * Sets the value of the customTabValidationPattern property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomTabValidationPattern(String value) {
        this.customTabValidationPattern = value;
    }

    /**
     * Gets the value of the customTabValidationMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomTabValidationMessage() {
        return customTabValidationMessage;
    }

    /**
     * Sets the value of the customTabValidationMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomTabValidationMessage(String value) {
        this.customTabValidationMessage = value;
    }

    /**
     * Gets the value of the templateLocked property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTemplateLocked() {
        return templateLocked;
    }

    /**
     * Sets the value of the templateLocked property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTemplateLocked(Boolean value) {
        this.templateLocked = value;
    }

    /**
     * Gets the value of the templateRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTemplateRequired() {
        return templateRequired;
    }

    /**
     * Sets the value of the templateRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTemplateRequired(Boolean value) {
        this.templateRequired = value;
    }

    /**
     * Gets the value of the conditionalParentLabel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConditionalParentLabel() {
        return conditionalParentLabel;
    }

    /**
     * Sets the value of the conditionalParentLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConditionalParentLabel(String value) {
        this.conditionalParentLabel = value;
    }

    /**
     * Gets the value of the conditionalParentValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConditionalParentValue() {
        return conditionalParentValue;
    }

    /**
     * Sets the value of the conditionalParentValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConditionalParentValue(String value) {
        this.conditionalParentValue = value;
    }

    /**
     * Gets the value of the sharedTab property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSharedTab() {
        return sharedTab;
    }

    /**
     * Sets the value of the sharedTab property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSharedTab(Boolean value) {
        this.sharedTab = value;
    }

    /**
     * Gets the value of the requireInitialOnSharedTabChange property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRequireInitialOnSharedTabChange() {
        return requireInitialOnSharedTabChange;
    }

    /**
     * Sets the value of the requireInitialOnSharedTabChange property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRequireInitialOnSharedTabChange(Boolean value) {
        this.requireInitialOnSharedTabChange = value;
    }

    /**
     * Gets the value of the concealValueOnDocument property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isConcealValueOnDocument() {
        return concealValueOnDocument;
    }

    /**
     * Sets the value of the concealValueOnDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setConcealValueOnDocument(Boolean value) {
        this.concealValueOnDocument = value;
    }

}
