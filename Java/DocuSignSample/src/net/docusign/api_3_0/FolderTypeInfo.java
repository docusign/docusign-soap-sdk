
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FolderTypeInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FolderTypeInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FolderType" type="{http://www.docusign.net/API/3.0}FolderType"/>
 *         &lt;element name="FolderName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FolderTypeInfo", propOrder = {
    "folderType",
    "folderName"
})
public class FolderTypeInfo {

    @XmlElement(name = "FolderType", required = true)
    protected FolderType folderType;
    @XmlElement(name = "FolderName")
    protected String folderName;

    /**
     * Gets the value of the folderType property.
     * 
     * @return
     *     possible object is
     *     {@link FolderType }
     *     
     */
    public FolderType getFolderType() {
        return folderType;
    }

    /**
     * Sets the value of the folderType property.
     * 
     * @param value
     *     allowed object is
     *     {@link FolderType }
     *     
     */
    public void setFolderType(FolderType value) {
        this.folderType = value;
    }

    /**
     * Gets the value of the folderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFolderName() {
        return folderName;
    }

    /**
     * Sets the value of the folderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFolderName(String value) {
        this.folderName = value;
    }

}
