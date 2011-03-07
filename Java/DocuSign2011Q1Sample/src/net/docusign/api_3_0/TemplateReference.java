
package net.docusign.api_3_0;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TemplateReference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TemplateReference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TemplateLocation" type="{http://www.docusign.net/API/3.0}TemplateLocationCode" minOccurs="0"/>
 *         &lt;element name="Template" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Document" type="{http://www.docusign.net/API/3.0}Document" minOccurs="0"/>
 *         &lt;element name="RoleAssignments" type="{http://www.docusign.net/API/3.0}ArrayOfTemplateReferenceRoleAssignment" minOccurs="0"/>
 *         &lt;element name="FieldData" type="{http://www.docusign.net/API/3.0}TemplateReferenceFieldData" minOccurs="0"/>
 *         &lt;element name="AdditionalTabs" type="{http://www.docusign.net/API/3.0}ArrayOfTab" minOccurs="0"/>
 *         &lt;element name="Sequence" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="TemplateAttachments" type="{http://www.docusign.net/API/3.0}ArrayOfAttachment" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemplateReference", propOrder = {
    "templateLocation",
    "template",
    "document",
    "roleAssignments",
    "fieldData",
    "additionalTabs",
    "sequence",
    "templateAttachments"
})
public class TemplateReference {

    @XmlElement(name = "TemplateLocation", defaultValue = "SOAP")
    protected TemplateLocationCode templateLocation;
    @XmlElement(name = "Template")
    protected String template;
    @XmlElement(name = "Document")
    protected Document document;
    @XmlElement(name = "RoleAssignments")
    protected ArrayOfTemplateReferenceRoleAssignment roleAssignments;
    @XmlElement(name = "FieldData")
    protected TemplateReferenceFieldData fieldData;
    @XmlElement(name = "AdditionalTabs")
    protected ArrayOfTab additionalTabs;
    @XmlElement(name = "Sequence")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger sequence;
    @XmlElement(name = "TemplateAttachments")
    protected ArrayOfAttachment templateAttachments;

    /**
     * Gets the value of the templateLocation property.
     * 
     * @return
     *     possible object is
     *     {@link TemplateLocationCode }
     *     
     */
    public TemplateLocationCode getTemplateLocation() {
        return templateLocation;
    }

    /**
     * Sets the value of the templateLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link TemplateLocationCode }
     *     
     */
    public void setTemplateLocation(TemplateLocationCode value) {
        this.templateLocation = value;
    }

    /**
     * Gets the value of the template property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemplate() {
        return template;
    }

    /**
     * Sets the value of the template property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemplate(String value) {
        this.template = value;
    }

    /**
     * Gets the value of the document property.
     * 
     * @return
     *     possible object is
     *     {@link Document }
     *     
     */
    public Document getDocument() {
        return document;
    }

    /**
     * Sets the value of the document property.
     * 
     * @param value
     *     allowed object is
     *     {@link Document }
     *     
     */
    public void setDocument(Document value) {
        this.document = value;
    }

    /**
     * Gets the value of the roleAssignments property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTemplateReferenceRoleAssignment }
     *     
     */
    public ArrayOfTemplateReferenceRoleAssignment getRoleAssignments() {
        return roleAssignments;
    }

    /**
     * Sets the value of the roleAssignments property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTemplateReferenceRoleAssignment }
     *     
     */
    public void setRoleAssignments(ArrayOfTemplateReferenceRoleAssignment value) {
        this.roleAssignments = value;
    }

    /**
     * Gets the value of the fieldData property.
     * 
     * @return
     *     possible object is
     *     {@link TemplateReferenceFieldData }
     *     
     */
    public TemplateReferenceFieldData getFieldData() {
        return fieldData;
    }

    /**
     * Sets the value of the fieldData property.
     * 
     * @param value
     *     allowed object is
     *     {@link TemplateReferenceFieldData }
     *     
     */
    public void setFieldData(TemplateReferenceFieldData value) {
        this.fieldData = value;
    }

    /**
     * Gets the value of the additionalTabs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTab }
     *     
     */
    public ArrayOfTab getAdditionalTabs() {
        return additionalTabs;
    }

    /**
     * Sets the value of the additionalTabs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTab }
     *     
     */
    public void setAdditionalTabs(ArrayOfTab value) {
        this.additionalTabs = value;
    }

    /**
     * Gets the value of the sequence property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSequence() {
        return sequence;
    }

    /**
     * Sets the value of the sequence property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSequence(BigInteger value) {
        this.sequence = value;
    }

    /**
     * Gets the value of the templateAttachments property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfAttachment }
     *     
     */
    public ArrayOfAttachment getTemplateAttachments() {
        return templateAttachments;
    }

    /**
     * Sets the value of the templateAttachments property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfAttachment }
     *     
     */
    public void setTemplateAttachments(ArrayOfAttachment value) {
        this.templateAttachments = value;
    }

}
