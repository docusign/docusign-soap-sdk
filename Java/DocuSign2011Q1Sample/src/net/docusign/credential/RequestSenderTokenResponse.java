
package net.docusign.credential;

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
 *         &lt;element name="RequestSenderTokenResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "requestSenderTokenResult"
})
@XmlRootElement(name = "RequestSenderTokenResponse")
public class RequestSenderTokenResponse {

    @XmlElement(name = "RequestSenderTokenResult")
    protected String requestSenderTokenResult;

    /**
     * Gets the value of the requestSenderTokenResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestSenderTokenResult() {
        return requestSenderTokenResult;
    }

    /**
     * Sets the value of the requestSenderTokenResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestSenderTokenResult(String value) {
        this.requestSenderTokenResult = value;
    }

}
