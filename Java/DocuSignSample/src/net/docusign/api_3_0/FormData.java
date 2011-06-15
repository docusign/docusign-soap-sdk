
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FormData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FormData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xfdf" type="{http://www.docusign.net/API/3.0}FormDataXfdf" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormData", propOrder = {
    "xfdf"
})
public class FormData {

    protected FormDataXfdf xfdf;

    /**
     * Gets the value of the xfdf property.
     * 
     * @return
     *     possible object is
     *     {@link FormDataXfdf }
     *     
     */
    public FormDataXfdf getXfdf() {
        return xfdf;
    }

    /**
     * Sets the value of the xfdf property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormDataXfdf }
     *     
     */
    public void setXfdf(FormDataXfdf value) {
        this.xfdf = value;
    }

}
