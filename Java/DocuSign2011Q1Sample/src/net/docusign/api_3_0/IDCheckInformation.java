
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IDCheckInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IDCheckInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AddressInformation" type="{http://www.docusign.net/API/3.0}AddressInformation" minOccurs="0"/>
 *         &lt;element name="DOBInformation" type="{http://www.docusign.net/API/3.0}DOBInformation" minOccurs="0"/>
 *         &lt;element name="SSN4Information" type="{http://www.docusign.net/API/3.0}SSN4Information" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IDCheckInformation", propOrder = {
    "addressInformation",
    "dobInformation",
    "ssn4Information"
})
public class IDCheckInformation {

    @XmlElement(name = "AddressInformation")
    protected AddressInformation addressInformation;
    @XmlElement(name = "DOBInformation")
    protected DOBInformation dobInformation;
    @XmlElement(name = "SSN4Information")
    protected SSN4Information ssn4Information;

    /**
     * Gets the value of the addressInformation property.
     * 
     * @return
     *     possible object is
     *     {@link AddressInformation }
     *     
     */
    public AddressInformation getAddressInformation() {
        return addressInformation;
    }

    /**
     * Sets the value of the addressInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressInformation }
     *     
     */
    public void setAddressInformation(AddressInformation value) {
        this.addressInformation = value;
    }

    /**
     * Gets the value of the dobInformation property.
     * 
     * @return
     *     possible object is
     *     {@link DOBInformation }
     *     
     */
    public DOBInformation getDOBInformation() {
        return dobInformation;
    }

    /**
     * Sets the value of the dobInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link DOBInformation }
     *     
     */
    public void setDOBInformation(DOBInformation value) {
        this.dobInformation = value;
    }

    /**
     * Gets the value of the ssn4Information property.
     * 
     * @return
     *     possible object is
     *     {@link SSN4Information }
     *     
     */
    public SSN4Information getSSN4Information() {
        return ssn4Information;
    }

    /**
     * Sets the value of the ssn4Information property.
     * 
     * @param value
     *     allowed object is
     *     {@link SSN4Information }
     *     
     */
    public void setSSN4Information(SSN4Information value) {
        this.ssn4Information = value;
    }

}
