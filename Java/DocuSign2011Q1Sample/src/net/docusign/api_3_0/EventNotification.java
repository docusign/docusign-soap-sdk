
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EventNotification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventNotification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="URL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LoggingEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="EnvelopeEvents" type="{http://www.docusign.net/API/3.0}ArrayOfEnvelopeEvent" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventNotification", propOrder = {
    "url",
    "loggingEnabled",
    "envelopeEvents"
})
public class EventNotification {

    @XmlElement(name = "URL")
    protected String url;
    @XmlElement(name = "LoggingEnabled")
    protected Boolean loggingEnabled;
    @XmlElement(name = "EnvelopeEvents")
    protected ArrayOfEnvelopeEvent envelopeEvents;

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getURL() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setURL(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the loggingEnabled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isLoggingEnabled() {
        return loggingEnabled;
    }

    /**
     * Sets the value of the loggingEnabled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setLoggingEnabled(Boolean value) {
        this.loggingEnabled = value;
    }

    /**
     * Gets the value of the envelopeEvents property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEnvelopeEvent }
     *     
     */
    public ArrayOfEnvelopeEvent getEnvelopeEvents() {
        return envelopeEvents;
    }

    /**
     * Sets the value of the envelopeEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEnvelopeEvent }
     *     
     */
    public void setEnvelopeEvents(ArrayOfEnvelopeEvent value) {
        this.envelopeEvents = value;
    }

}
