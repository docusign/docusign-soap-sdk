
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
 *         &lt;element name="GetFolderListResult" type="{http://www.docusign.net/API/3.0}AvailableFolders" minOccurs="0"/>
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
    "getFolderListResult"
})
@XmlRootElement(name = "GetFolderListResponse")
public class GetFolderListResponse {

    @XmlElement(name = "GetFolderListResult")
    protected AvailableFolders getFolderListResult;

    /**
     * Gets the value of the getFolderListResult property.
     * 
     * @return
     *     possible object is
     *     {@link AvailableFolders }
     *     
     */
    public AvailableFolders getGetFolderListResult() {
        return getFolderListResult;
    }

    /**
     * Sets the value of the getFolderListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AvailableFolders }
     *     
     */
    public void setGetFolderListResult(AvailableFolders value) {
        this.getFolderListResult = value;
    }

}
