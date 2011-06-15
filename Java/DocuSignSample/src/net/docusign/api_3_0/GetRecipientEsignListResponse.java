
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
 *         &lt;element name="GetRecipientEsignListResult" type="{http://www.docusign.net/API/3.0}RecipientEsignList" minOccurs="0"/>
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
    "getRecipientEsignListResult"
})
@XmlRootElement(name = "GetRecipientEsignListResponse")
public class GetRecipientEsignListResponse {

    @XmlElement(name = "GetRecipientEsignListResult")
    protected RecipientEsignList getRecipientEsignListResult;

    /**
     * Gets the value of the getRecipientEsignListResult property.
     * 
     * @return
     *     possible object is
     *     {@link RecipientEsignList }
     *     
     */
    public RecipientEsignList getGetRecipientEsignListResult() {
        return getRecipientEsignListResult;
    }

    /**
     * Sets the value of the getRecipientEsignListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecipientEsignList }
     *     
     */
    public void setGetRecipientEsignListResult(RecipientEsignList value) {
        this.getRecipientEsignListResult = value;
    }

}
