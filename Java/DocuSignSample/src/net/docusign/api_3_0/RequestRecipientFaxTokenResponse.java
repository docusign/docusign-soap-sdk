
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
 *         &lt;element name="RequestRecipientFaxTokenResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "requestRecipientFaxTokenResult"
})
@XmlRootElement(name = "RequestRecipientFaxTokenResponse")
public class RequestRecipientFaxTokenResponse {

    @XmlElement(name = "RequestRecipientFaxTokenResult")
    protected String requestRecipientFaxTokenResult;

    /**
     * Gets the value of the requestRecipientFaxTokenResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestRecipientFaxTokenResult() {
        return requestRecipientFaxTokenResult;
    }

    /**
     * Sets the value of the requestRecipientFaxTokenResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestRecipientFaxTokenResult(String value) {
        this.requestRecipientFaxTokenResult = value;
    }

}
