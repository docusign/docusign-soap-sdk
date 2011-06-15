
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CompositeTemplate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CompositeTemplate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServerTemplates" type="{http://www.docusign.net/API/3.0}ArrayOfServerTemplate" minOccurs="0"/>
 *         &lt;element name="InlineTemplates" type="{http://www.docusign.net/API/3.0}ArrayOfInlineTemplate" minOccurs="0"/>
 *         &lt;element name="PDFMetaDataTemplate" type="{http://www.docusign.net/API/3.0}PDFMetaDataTemplate" minOccurs="0"/>
 *         &lt;element name="Document" type="{http://www.docusign.net/API/3.0}Document" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompositeTemplate", propOrder = {
    "serverTemplates",
    "inlineTemplates",
    "pdfMetaDataTemplate",
    "document"
})
public class CompositeTemplate {

    @XmlElement(name = "ServerTemplates")
    protected ArrayOfServerTemplate serverTemplates;
    @XmlElement(name = "InlineTemplates")
    protected ArrayOfInlineTemplate inlineTemplates;
    @XmlElement(name = "PDFMetaDataTemplate")
    protected PDFMetaDataTemplate pdfMetaDataTemplate;
    @XmlElement(name = "Document")
    protected Document document;

    /**
     * Gets the value of the serverTemplates property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfServerTemplate }
     *     
     */
    public ArrayOfServerTemplate getServerTemplates() {
        return serverTemplates;
    }

    /**
     * Sets the value of the serverTemplates property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfServerTemplate }
     *     
     */
    public void setServerTemplates(ArrayOfServerTemplate value) {
        this.serverTemplates = value;
    }

    /**
     * Gets the value of the inlineTemplates property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInlineTemplate }
     *     
     */
    public ArrayOfInlineTemplate getInlineTemplates() {
        return inlineTemplates;
    }

    /**
     * Sets the value of the inlineTemplates property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInlineTemplate }
     *     
     */
    public void setInlineTemplates(ArrayOfInlineTemplate value) {
        this.inlineTemplates = value;
    }

    /**
     * Gets the value of the pdfMetaDataTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link PDFMetaDataTemplate }
     *     
     */
    public PDFMetaDataTemplate getPDFMetaDataTemplate() {
        return pdfMetaDataTemplate;
    }

    /**
     * Sets the value of the pdfMetaDataTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link PDFMetaDataTemplate }
     *     
     */
    public void setPDFMetaDataTemplate(PDFMetaDataTemplate value) {
        this.pdfMetaDataTemplate = value;
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

}
