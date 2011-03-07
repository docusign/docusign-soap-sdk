
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AuthenticationStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AuthenticationStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AccessCodeResult" type="{http://www.docusign.net/API/3.0}EventResult" minOccurs="0"/>
 *         &lt;element name="IDQuestionsResult" type="{http://www.docusign.net/API/3.0}EventResult" minOccurs="0"/>
 *         &lt;element name="IDLookupResult" type="{http://www.docusign.net/API/3.0}EventResult" minOccurs="0"/>
 *         &lt;element name="AgeVerifyResult" type="{http://www.docusign.net/API/3.0}EventResult" minOccurs="0"/>
 *         &lt;element name="STANPinResult" type="{http://www.docusign.net/API/3.0}EventResult" minOccurs="0"/>
 *         &lt;element name="OFACResult" type="{http://www.docusign.net/API/3.0}EventResult" minOccurs="0"/>
 *         &lt;element name="PhoneAuthResult" type="{http://www.docusign.net/API/3.0}EventResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AuthenticationStatus", propOrder = {
    "accessCodeResult",
    "idQuestionsResult",
    "idLookupResult",
    "ageVerifyResult",
    "stanPinResult",
    "ofacResult",
    "phoneAuthResult"
})
public class AuthenticationStatus {

    @XmlElement(name = "AccessCodeResult")
    protected EventResult accessCodeResult;
    @XmlElement(name = "IDQuestionsResult")
    protected EventResult idQuestionsResult;
    @XmlElement(name = "IDLookupResult")
    protected EventResult idLookupResult;
    @XmlElement(name = "AgeVerifyResult")
    protected EventResult ageVerifyResult;
    @XmlElement(name = "STANPinResult")
    protected EventResult stanPinResult;
    @XmlElement(name = "OFACResult")
    protected EventResult ofacResult;
    @XmlElement(name = "PhoneAuthResult")
    protected EventResult phoneAuthResult;

    /**
     * Gets the value of the accessCodeResult property.
     * 
     * @return
     *     possible object is
     *     {@link EventResult }
     *     
     */
    public EventResult getAccessCodeResult() {
        return accessCodeResult;
    }

    /**
     * Sets the value of the accessCodeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventResult }
     *     
     */
    public void setAccessCodeResult(EventResult value) {
        this.accessCodeResult = value;
    }

    /**
     * Gets the value of the idQuestionsResult property.
     * 
     * @return
     *     possible object is
     *     {@link EventResult }
     *     
     */
    public EventResult getIDQuestionsResult() {
        return idQuestionsResult;
    }

    /**
     * Sets the value of the idQuestionsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventResult }
     *     
     */
    public void setIDQuestionsResult(EventResult value) {
        this.idQuestionsResult = value;
    }

    /**
     * Gets the value of the idLookupResult property.
     * 
     * @return
     *     possible object is
     *     {@link EventResult }
     *     
     */
    public EventResult getIDLookupResult() {
        return idLookupResult;
    }

    /**
     * Sets the value of the idLookupResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventResult }
     *     
     */
    public void setIDLookupResult(EventResult value) {
        this.idLookupResult = value;
    }

    /**
     * Gets the value of the ageVerifyResult property.
     * 
     * @return
     *     possible object is
     *     {@link EventResult }
     *     
     */
    public EventResult getAgeVerifyResult() {
        return ageVerifyResult;
    }

    /**
     * Sets the value of the ageVerifyResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventResult }
     *     
     */
    public void setAgeVerifyResult(EventResult value) {
        this.ageVerifyResult = value;
    }

    /**
     * Gets the value of the stanPinResult property.
     * 
     * @return
     *     possible object is
     *     {@link EventResult }
     *     
     */
    public EventResult getSTANPinResult() {
        return stanPinResult;
    }

    /**
     * Sets the value of the stanPinResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventResult }
     *     
     */
    public void setSTANPinResult(EventResult value) {
        this.stanPinResult = value;
    }

    /**
     * Gets the value of the ofacResult property.
     * 
     * @return
     *     possible object is
     *     {@link EventResult }
     *     
     */
    public EventResult getOFACResult() {
        return ofacResult;
    }

    /**
     * Sets the value of the ofacResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventResult }
     *     
     */
    public void setOFACResult(EventResult value) {
        this.ofacResult = value;
    }

    /**
     * Gets the value of the phoneAuthResult property.
     * 
     * @return
     *     possible object is
     *     {@link EventResult }
     *     
     */
    public EventResult getPhoneAuthResult() {
        return phoneAuthResult;
    }

    /**
     * Sets the value of the phoneAuthResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventResult }
     *     
     */
    public void setPhoneAuthResult(EventResult value) {
        this.phoneAuthResult = value;
    }

}
