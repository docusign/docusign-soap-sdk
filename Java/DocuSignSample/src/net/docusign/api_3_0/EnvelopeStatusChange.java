
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for EnvelopeStatusChange complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnvelopeStatusChange">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EnvelopeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.docusign.net/API/3.0}EnvelopeStatusCode"/>
 *         &lt;element name="StatusChanged" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnvelopeStatusChange", propOrder = {
    "envelopeID",
    "status",
    "statusChanged"
})
public class EnvelopeStatusChange {

    @XmlElement(name = "EnvelopeID")
    protected String envelopeID;
    @XmlElement(name = "Status", required = true)
    protected EnvelopeStatusCode status;
    @XmlElement(name = "StatusChanged", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar statusChanged;

    /**
     * Gets the value of the envelopeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnvelopeID() {
        return envelopeID;
    }

    /**
     * Sets the value of the envelopeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnvelopeID(String value) {
        this.envelopeID = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link EnvelopeStatusCode }
     *     
     */
    public EnvelopeStatusCode getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnvelopeStatusCode }
     *     
     */
    public void setStatus(EnvelopeStatusCode value) {
        this.status = value;
    }

    /**
     * Gets the value of the statusChanged property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStatusChanged() {
        return statusChanged;
    }

    /**
     * Sets the value of the statusChanged property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStatusChanged(XMLGregorianCalendar value) {
        this.statusChanged = value;
    }

}
