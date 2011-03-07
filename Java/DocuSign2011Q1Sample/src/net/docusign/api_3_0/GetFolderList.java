
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
 *         &lt;element name="FoldersFilter" type="{http://www.docusign.net/API/3.0}FoldersFilter" minOccurs="0"/>
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
    "foldersFilter"
})
@XmlRootElement(name = "GetFolderList")
public class GetFolderList {

    @XmlElement(name = "FoldersFilter")
    protected FoldersFilter foldersFilter;

    /**
     * Gets the value of the foldersFilter property.
     * 
     * @return
     *     possible object is
     *     {@link FoldersFilter }
     *     
     */
    public FoldersFilter getFoldersFilter() {
        return foldersFilter;
    }

    /**
     * Sets the value of the foldersFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link FoldersFilter }
     *     
     */
    public void setFoldersFilter(FoldersFilter value) {
        this.foldersFilter = value;
    }

}
