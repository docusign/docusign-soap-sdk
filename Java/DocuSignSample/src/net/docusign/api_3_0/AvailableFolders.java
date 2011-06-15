
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AvailableFolders complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AvailableFolders">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Folders" type="{http://www.docusign.net/API/3.0}ArrayOfFolder"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AvailableFolders", propOrder = {
    "folders"
})
public class AvailableFolders {

    @XmlElement(name = "Folders", required = true, nillable = true)
    protected ArrayOfFolder folders;

    /**
     * Gets the value of the folders property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFolder }
     *     
     */
    public ArrayOfFolder getFolders() {
        return folders;
    }

    /**
     * Sets the value of the folders property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFolder }
     *     
     */
    public void setFolders(ArrayOfFolder value) {
        this.folders = value;
    }

}
