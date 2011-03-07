
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SSN9Information complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SSN9Information">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SSN9" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SSN9Information", propOrder = {
    "ssn9"
})
public class SSN9Information {

    @XmlElement(name = "SSN9")
    protected String ssn9;

    /**
     * Gets the value of the ssn9 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSSN9() {
        return ssn9;
    }

    /**
     * Sets the value of the ssn9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSSN9(String value) {
        this.ssn9 = value;
    }

}
