
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
 *         &lt;element name="PublishConnectFailuresFilter" type="{http://www.docusign.net/API/3.0}PublishConnectFailuresFilter" minOccurs="0"/>
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
    "publishConnectFailuresFilter"
})
@XmlRootElement(name = "PublishConnectFailures")
public class PublishConnectFailures {

    @XmlElement(name = "PublishConnectFailuresFilter")
    protected PublishConnectFailuresFilter publishConnectFailuresFilter;

    /**
     * Gets the value of the publishConnectFailuresFilter property.
     * 
     * @return
     *     possible object is
     *     {@link PublishConnectFailuresFilter }
     *     
     */
    public PublishConnectFailuresFilter getPublishConnectFailuresFilter() {
        return publishConnectFailuresFilter;
    }

    /**
     * Sets the value of the publishConnectFailuresFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link PublishConnectFailuresFilter }
     *     
     */
    public void setPublishConnectFailuresFilter(PublishConnectFailuresFilter value) {
        this.publishConnectFailuresFilter = value;
    }

}
