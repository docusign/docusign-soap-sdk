
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountMembershipFeaturesList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountMembershipFeaturesList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnabledFeaturesSet" type="{http://www.docusign.net/API/3.0}ArrayOfString4" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountMembershipFeaturesList", propOrder = {
    "email",
    "userName",
    "enabledFeaturesSet"
})
public class AccountMembershipFeaturesList {

    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "EnabledFeaturesSet")
    protected ArrayOfString4 enabledFeaturesSet;

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the enabledFeaturesSet property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString4 }
     *     
     */
    public ArrayOfString4 getEnabledFeaturesSet() {
        return enabledFeaturesSet;
    }

    /**
     * Sets the value of the enabledFeaturesSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString4 }
     *     
     */
    public void setEnabledFeaturesSet(ArrayOfString4 value) {
        this.enabledFeaturesSet = value;
    }

}
