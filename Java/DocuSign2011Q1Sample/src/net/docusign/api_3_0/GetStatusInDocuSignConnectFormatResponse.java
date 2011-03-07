
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
 *         &lt;element name="GetStatusInDocuSignConnectFormatResult" type="{http://www.docusign.net/API/3.0}DocuSignEnvelopeInformation" minOccurs="0"/>
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
    "getStatusInDocuSignConnectFormatResult"
})
@XmlRootElement(name = "GetStatusInDocuSignConnectFormatResponse")
public class GetStatusInDocuSignConnectFormatResponse {

    @XmlElement(name = "GetStatusInDocuSignConnectFormatResult")
    protected DocuSignEnvelopeInformation getStatusInDocuSignConnectFormatResult;

    /**
     * Gets the value of the getStatusInDocuSignConnectFormatResult property.
     * 
     * @return
     *     possible object is
     *     {@link DocuSignEnvelopeInformation }
     *     
     */
    public DocuSignEnvelopeInformation getGetStatusInDocuSignConnectFormatResult() {
        return getStatusInDocuSignConnectFormatResult;
    }

    /**
     * Sets the value of the getStatusInDocuSignConnectFormatResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocuSignEnvelopeInformation }
     *     
     */
    public void setGetStatusInDocuSignConnectFormatResult(DocuSignEnvelopeInformation value) {
        this.getStatusInDocuSignConnectFormatResult = value;
    }

}
