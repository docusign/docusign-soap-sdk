
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthoritativeCopyExportStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthoritativeCopyExportStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AuthoritativeCopyExportSuccess" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="EnvelopeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExportKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthoritativeCopyExportStatus", propOrder = {
    "authoritativeCopyExportSuccess",
    "envelopeId",
    "exportKey"
})
public class AuthoritativeCopyExportStatus {

    @XmlElement(name = "AuthoritativeCopyExportSuccess")
    protected Boolean authoritativeCopyExportSuccess;
    @XmlElement(name = "EnvelopeId")
    protected String envelopeId;
    @XmlElement(name = "ExportKey")
    protected String exportKey;

    /**
     * Gets the value of the authoritativeCopyExportSuccess property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAuthoritativeCopyExportSuccess() {
        return authoritativeCopyExportSuccess;
    }

    /**
     * Sets the value of the authoritativeCopyExportSuccess property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAuthoritativeCopyExportSuccess(Boolean value) {
        this.authoritativeCopyExportSuccess = value;
    }

    /**
     * Gets the value of the envelopeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnvelopeId() {
        return envelopeId;
    }

    /**
     * Sets the value of the envelopeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnvelopeId(String value) {
        this.envelopeId = value;
    }

    /**
     * Gets the value of the exportKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExportKey() {
        return exportKey;
    }

    /**
     * Sets the value of the exportKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExportKey(String value) {
        this.exportKey = value;
    }

}
