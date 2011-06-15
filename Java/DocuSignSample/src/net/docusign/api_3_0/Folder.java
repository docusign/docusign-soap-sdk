
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Folder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Folder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FolderOwner" type="{http://www.docusign.net/API/3.0}UserInfo" minOccurs="0"/>
 *         &lt;element name="FolderTypeInfo" type="{http://www.docusign.net/API/3.0}FolderTypeInfo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Folder", propOrder = {
    "folderOwner",
    "folderTypeInfo"
})
public class Folder {

    @XmlElement(name = "FolderOwner")
    protected UserInfo folderOwner;
    @XmlElement(name = "FolderTypeInfo")
    protected FolderTypeInfo folderTypeInfo;

    /**
     * Gets the value of the folderOwner property.
     * 
     * @return
     *     possible object is
     *     {@link UserInfo }
     *     
     */
    public UserInfo getFolderOwner() {
        return folderOwner;
    }

    /**
     * Sets the value of the folderOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserInfo }
     *     
     */
    public void setFolderOwner(UserInfo value) {
        this.folderOwner = value;
    }

    /**
     * Gets the value of the folderTypeInfo property.
     * 
     * @return
     *     possible object is
     *     {@link FolderTypeInfo }
     *     
     */
    public FolderTypeInfo getFolderTypeInfo() {
        return folderTypeInfo;
    }

    /**
     * Sets the value of the folderTypeInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link FolderTypeInfo }
     *     
     */
    public void setFolderTypeInfo(FolderTypeInfo value) {
        this.folderTypeInfo = value;
    }

}
