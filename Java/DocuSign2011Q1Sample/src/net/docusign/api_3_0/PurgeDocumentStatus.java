
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PurgeDocumentStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PurgeDocumentStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PurgeDocumentSuccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PurgeDocumentError" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PurgeDocumentStatus", propOrder = {
    "purgeDocumentSuccess",
    "purgeDocumentError"
})
public class PurgeDocumentStatus {

    @XmlElement(name = "PurgeDocumentSuccess")
    protected boolean purgeDocumentSuccess;
    @XmlElement(name = "PurgeDocumentError")
    protected String purgeDocumentError;

    /**
     * Gets the value of the purgeDocumentSuccess property.
     * 
     */
    public boolean isPurgeDocumentSuccess() {
        return purgeDocumentSuccess;
    }

    /**
     * Sets the value of the purgeDocumentSuccess property.
     * 
     */
    public void setPurgeDocumentSuccess(boolean value) {
        this.purgeDocumentSuccess = value;
    }

    /**
     * Gets the value of the purgeDocumentError property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurgeDocumentError() {
        return purgeDocumentError;
    }

    /**
     * Sets the value of the purgeDocumentError property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurgeDocumentError(String value) {
        this.purgeDocumentError = value;
    }

}
