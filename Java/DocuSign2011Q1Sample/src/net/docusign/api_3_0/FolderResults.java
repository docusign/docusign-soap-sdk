
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FolderResults complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FolderResults">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResultSetSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="StartPosition" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EndPosition" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="FolderTypeInfo" type="{http://www.docusign.net/API/3.0}FolderTypeInfo" minOccurs="0"/>
 *         &lt;element name="FolderItems" type="{http://www.docusign.net/API/3.0}ArrayOfFolderItem"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FolderResults", propOrder = {
    "resultSetSize",
    "startPosition",
    "endPosition",
    "folderTypeInfo",
    "folderItems"
})
public class FolderResults {

    @XmlElement(name = "ResultSetSize")
    protected int resultSetSize;
    @XmlElement(name = "StartPosition")
    protected int startPosition;
    @XmlElement(name = "EndPosition")
    protected int endPosition;
    @XmlElement(name = "FolderTypeInfo")
    protected FolderTypeInfo folderTypeInfo;
    @XmlElement(name = "FolderItems", required = true, nillable = true)
    protected ArrayOfFolderItem folderItems;

    /**
     * Gets the value of the resultSetSize property.
     * 
     */
    public int getResultSetSize() {
        return resultSetSize;
    }

    /**
     * Sets the value of the resultSetSize property.
     * 
     */
    public void setResultSetSize(int value) {
        this.resultSetSize = value;
    }

    /**
     * Gets the value of the startPosition property.
     * 
     */
    public int getStartPosition() {
        return startPosition;
    }

    /**
     * Sets the value of the startPosition property.
     * 
     */
    public void setStartPosition(int value) {
        this.startPosition = value;
    }

    /**
     * Gets the value of the endPosition property.
     * 
     */
    public int getEndPosition() {
        return endPosition;
    }

    /**
     * Sets the value of the endPosition property.
     * 
     */
    public void setEndPosition(int value) {
        this.endPosition = value;
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

    /**
     * Gets the value of the folderItems property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFolderItem }
     *     
     */
    public ArrayOfFolderItem getFolderItems() {
        return folderItems;
    }

    /**
     * Sets the value of the folderItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFolderItem }
     *     
     */
    public void setFolderItems(ArrayOfFolderItem value) {
        this.folderItems = value;
    }

}
