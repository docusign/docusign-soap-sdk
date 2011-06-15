
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VoidEnvelopeStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VoidEnvelopeStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VoidSuccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VoidEnvelopeStatus", propOrder = {
    "voidSuccess"
})
public class VoidEnvelopeStatus {

    @XmlElement(name = "VoidSuccess")
    protected boolean voidSuccess;

    /**
     * Gets the value of the voidSuccess property.
     * 
     */
    public boolean isVoidSuccess() {
        return voidSuccess;
    }

    /**
     * Sets the value of the voidSuccess property.
     * 
     */
    public void setVoidSuccess(boolean value) {
        this.voidSuccess = value;
    }

}
