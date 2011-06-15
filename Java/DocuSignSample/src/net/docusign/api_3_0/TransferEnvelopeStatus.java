
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransferEnvelopeStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransferEnvelopeStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TransferEnvelopeSuccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransferEnvelopeStatus", propOrder = {
    "transferEnvelopeSuccess"
})
public class TransferEnvelopeStatus {

    @XmlElement(name = "TransferEnvelopeSuccess")
    protected boolean transferEnvelopeSuccess;

    /**
     * Gets the value of the transferEnvelopeSuccess property.
     * 
     */
    public boolean isTransferEnvelopeSuccess() {
        return transferEnvelopeSuccess;
    }

    /**
     * Sets the value of the transferEnvelopeSuccess property.
     * 
     */
    public void setTransferEnvelopeSuccess(boolean value) {
        this.transferEnvelopeSuccess = value;
    }

}
