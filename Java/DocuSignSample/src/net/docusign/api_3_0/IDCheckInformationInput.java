
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IDCheckInformationInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IDCheckInformationInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AddressInformationInput" type="{http://www.docusign.net/API/3.0}AddressInformationInput" minOccurs="0"/>
 *         &lt;element name="DOBInformationInput" type="{http://www.docusign.net/API/3.0}DOBInformationInput" minOccurs="0"/>
 *         &lt;element name="SSN4InformationInput" type="{http://www.docusign.net/API/3.0}SSN4InformationInput" minOccurs="0"/>
 *         &lt;element name="SSN9InformationInput" type="{http://www.docusign.net/API/3.0}SSN9InformationInput" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IDCheckInformationInput", propOrder = {
    "addressInformationInput",
    "dobInformationInput",
    "ssn4InformationInput",
    "ssn9InformationInput"
})
public class IDCheckInformationInput {

    @XmlElement(name = "AddressInformationInput")
    protected AddressInformationInput addressInformationInput;
    @XmlElement(name = "DOBInformationInput")
    protected DOBInformationInput dobInformationInput;
    @XmlElement(name = "SSN4InformationInput")
    protected SSN4InformationInput ssn4InformationInput;
    @XmlElement(name = "SSN9InformationInput")
    protected SSN9InformationInput ssn9InformationInput;

    /**
     * Gets the value of the addressInformationInput property.
     * 
     * @return
     *     possible object is
     *     {@link AddressInformationInput }
     *     
     */
    public AddressInformationInput getAddressInformationInput() {
        return addressInformationInput;
    }

    /**
     * Sets the value of the addressInformationInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressInformationInput }
     *     
     */
    public void setAddressInformationInput(AddressInformationInput value) {
        this.addressInformationInput = value;
    }

    /**
     * Gets the value of the dobInformationInput property.
     * 
     * @return
     *     possible object is
     *     {@link DOBInformationInput }
     *     
     */
    public DOBInformationInput getDOBInformationInput() {
        return dobInformationInput;
    }

    /**
     * Sets the value of the dobInformationInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link DOBInformationInput }
     *     
     */
    public void setDOBInformationInput(DOBInformationInput value) {
        this.dobInformationInput = value;
    }

    /**
     * Gets the value of the ssn4InformationInput property.
     * 
     * @return
     *     possible object is
     *     {@link SSN4InformationInput }
     *     
     */
    public SSN4InformationInput getSSN4InformationInput() {
        return ssn4InformationInput;
    }

    /**
     * Sets the value of the ssn4InformationInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link SSN4InformationInput }
     *     
     */
    public void setSSN4InformationInput(SSN4InformationInput value) {
        this.ssn4InformationInput = value;
    }

    /**
     * Gets the value of the ssn9InformationInput property.
     * 
     * @return
     *     possible object is
     *     {@link SSN9InformationInput }
     *     
     */
    public SSN9InformationInput getSSN9InformationInput() {
        return ssn9InformationInput;
    }

    /**
     * Sets the value of the ssn9InformationInput property.
     * 
     * @param value
     *     allowed object is
     *     {@link SSN9InformationInput }
     *     
     */
    public void setSSN9InformationInput(SSN9InformationInput value) {
        this.ssn9InformationInput = value;
    }

}
