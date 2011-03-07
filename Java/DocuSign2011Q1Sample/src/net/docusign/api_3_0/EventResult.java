
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for EventResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EventResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Status" type="{http://www.docusign.net/API/3.0}EventStatusCode"/>
 *         &lt;element name="EventTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EventResult", propOrder = {
    "status",
    "eventTimestamp"
})
public class EventResult {

    @XmlElement(name = "Status", required = true)
    protected EventStatusCode status;
    @XmlElement(name = "EventTimestamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar eventTimestamp;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link EventStatusCode }
     *     
     */
    public EventStatusCode getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventStatusCode }
     *     
     */
    public void setStatus(EventStatusCode value) {
        this.status = value;
    }

    /**
     * Gets the value of the eventTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEventTimestamp() {
        return eventTimestamp;
    }

    /**
     * Sets the value of the eventTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEventTimestamp(XMLGregorianCalendar value) {
        this.eventTimestamp = value;
    }

}
