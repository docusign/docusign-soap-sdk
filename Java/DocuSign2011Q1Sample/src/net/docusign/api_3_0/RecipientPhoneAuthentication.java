
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RecipientPhoneAuthentication complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RecipientPhoneAuthentication">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecipMayProvideNumber" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ValidateRecipProvidedNumber" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="RecordVoicePrint" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SenderProvidedNumbers" type="{http://www.docusign.net/API/3.0}ArrayOfString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecipientPhoneAuthentication", propOrder = {
    "recipMayProvideNumber",
    "validateRecipProvidedNumber",
    "recordVoicePrint",
    "senderProvidedNumbers"
})
public class RecipientPhoneAuthentication {

    @XmlElement(name = "RecipMayProvideNumber")
    protected Boolean recipMayProvideNumber;
    @XmlElement(name = "ValidateRecipProvidedNumber")
    protected Boolean validateRecipProvidedNumber;
    @XmlElement(name = "RecordVoicePrint")
    protected Boolean recordVoicePrint;
    @XmlElement(name = "SenderProvidedNumbers")
    protected ArrayOfString senderProvidedNumbers;

    /**
     * Gets the value of the recipMayProvideNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRecipMayProvideNumber() {
        return recipMayProvideNumber;
    }

    /**
     * Sets the value of the recipMayProvideNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRecipMayProvideNumber(Boolean value) {
        this.recipMayProvideNumber = value;
    }

    /**
     * Gets the value of the validateRecipProvidedNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isValidateRecipProvidedNumber() {
        return validateRecipProvidedNumber;
    }

    /**
     * Sets the value of the validateRecipProvidedNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setValidateRecipProvidedNumber(Boolean value) {
        this.validateRecipProvidedNumber = value;
    }

    /**
     * Gets the value of the recordVoicePrint property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRecordVoicePrint() {
        return recordVoicePrint;
    }

    /**
     * Sets the value of the recordVoicePrint property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRecordVoicePrint(Boolean value) {
        this.recordVoicePrint = value;
    }

    /**
     * Gets the value of the senderProvidedNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getSenderProvidedNumbers() {
        return senderProvidedNumbers;
    }

    /**
     * Sets the value of the senderProvidedNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setSenderProvidedNumbers(ArrayOfString value) {
        this.senderProvidedNumbers = value;
    }

}
