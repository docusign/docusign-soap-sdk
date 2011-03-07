
package net.docusign.api_3_0;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Document complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Document">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PDFBytes" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="Password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransformPdfFields" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="FileExtension" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MatchBoxes" type="{http://www.docusign.net/API/3.0}ArrayOfMatchBox" minOccurs="0"/>
 *         &lt;element name="AttachmentDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Document", propOrder = {
    "id",
    "name",
    "pdfBytes",
    "password",
    "transformPdfFields",
    "fileExtension",
    "matchBoxes",
    "attachmentDescription"
})
public class Document {

    @XmlElement(name = "ID")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger id;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "PDFBytes")
    protected byte[] pdfBytes;
    @XmlElement(name = "Password")
    protected String password;
    @XmlElement(name = "TransformPdfFields", defaultValue = "false")
    protected Boolean transformPdfFields;
    @XmlElement(name = "FileExtension")
    protected String fileExtension;
    @XmlElement(name = "MatchBoxes")
    protected ArrayOfMatchBox matchBoxes;
    @XmlElement(name = "AttachmentDescription")
    protected String attachmentDescription;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setID(BigInteger value) {
        this.id = value;
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
     * Gets the value of the pdfBytes property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getPDFBytes() {
        return pdfBytes;
    }

    /**
     * Sets the value of the pdfBytes property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setPDFBytes(byte[] value) {
        this.pdfBytes = ((byte[]) value);
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the transformPdfFields property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTransformPdfFields() {
        return transformPdfFields;
    }

    /**
     * Sets the value of the transformPdfFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTransformPdfFields(Boolean value) {
        this.transformPdfFields = value;
    }

    /**
     * Gets the value of the fileExtension property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileExtension() {
        return fileExtension;
    }

    /**
     * Sets the value of the fileExtension property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileExtension(String value) {
        this.fileExtension = value;
    }

    /**
     * Gets the value of the matchBoxes property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfMatchBox }
     *     
     */
    public ArrayOfMatchBox getMatchBoxes() {
        return matchBoxes;
    }

    /**
     * Sets the value of the matchBoxes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfMatchBox }
     *     
     */
    public void setMatchBoxes(ArrayOfMatchBox value) {
        this.matchBoxes = value;
    }

    /**
     * Gets the value of the attachmentDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttachmentDescription() {
        return attachmentDescription;
    }

    /**
     * Sets the value of the attachmentDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttachmentDescription(String value) {
        this.attachmentDescription = value;
    }

}
