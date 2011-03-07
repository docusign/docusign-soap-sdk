
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RecipientCaptiveInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RecipientCaptiveInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClientUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecipientCaptiveInfo", propOrder = {
    "clientUserId"
})
public class RecipientCaptiveInfo {

    @XmlElement(name = "ClientUserId")
    protected String clientUserId;

    /**
     * Gets the value of the clientUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientUserId() {
        return clientUserId;
    }

    /**
     * Sets the value of the clientUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientUserId(String value) {
        this.clientUserId = value;
    }

}
