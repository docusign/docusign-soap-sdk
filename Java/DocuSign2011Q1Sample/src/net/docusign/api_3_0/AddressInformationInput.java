
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AddressInformationInput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressInformationInput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AddressInformation" type="{http://www.docusign.net/API/3.0}AddressInformation" minOccurs="0"/>
 *         &lt;element name="DisplayLevel" type="{http://www.docusign.net/API/3.0}DisplayLevelCode" minOccurs="0"/>
 *         &lt;element name="ReceiveInResponse" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressInformationInput", propOrder = {
    "addressInformation",
    "displayLevel",
    "receiveInResponse"
})
public class AddressInformationInput {

    @XmlElement(name = "AddressInformation")
    protected AddressInformation addressInformation;
    @XmlElement(name = "DisplayLevel")
    protected DisplayLevelCode displayLevel;
    @XmlElement(name = "ReceiveInResponse")
    protected Boolean receiveInResponse;

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
     * Gets the value of the displayLevel property.
     * 
     * @return
     *     possible object is
     *     {@link DisplayLevelCode }
     *     
     */
    public DisplayLevelCode getDisplayLevel() {
        return displayLevel;
    }

    /**
     * Sets the value of the displayLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link DisplayLevelCode }
     *     
     */
    public void setDisplayLevel(DisplayLevelCode value) {
        this.displayLevel = value;
    }

    /**
     * Gets the value of the receiveInResponse property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReceiveInResponse() {
        return receiveInResponse;
    }

    /**
     * Sets the value of the receiveInResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReceiveInResponse(Boolean value) {
        this.receiveInResponse = value;
    }

}
