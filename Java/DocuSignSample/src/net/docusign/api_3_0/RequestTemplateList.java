
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
 *         &lt;element name="TemplateIDs" type="{http://www.docusign.net/API/3.0}ArrayOfString5" minOccurs="0"/>
 *         &lt;element name="IncludeDocumentBytes" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "templateIDs",
    "includeDocumentBytes"
})
@XmlRootElement(name = "RequestTemplateList")
public class RequestTemplateList {

    @XmlElement(name = "TemplateIDs")
    protected ArrayOfString5 templateIDs;
    @XmlElement(name = "IncludeDocumentBytes")
    protected boolean includeDocumentBytes;

    /**
     * Gets the value of the templateIDs property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString5 }
     *     
     */
    public ArrayOfString5 getTemplateIDs() {
        return templateIDs;
    }

    /**
     * Sets the value of the templateIDs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString5 }
     *     
     */
    public void setTemplateIDs(ArrayOfString5 value) {
        this.templateIDs = value;
    }

    /**
     * Gets the value of the includeDocumentBytes property.
     * 
     */
    public boolean isIncludeDocumentBytes() {
        return includeDocumentBytes;
    }

    /**
     * Sets the value of the includeDocumentBytes property.
     * 
     */
    public void setIncludeDocumentBytes(boolean value) {
        this.includeDocumentBytes = value;
    }

}
