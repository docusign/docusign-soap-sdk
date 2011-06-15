
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FormDataXfdf complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FormDataXfdf">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fields" type="{http://www.docusign.net/API/3.0}ArrayOfFormDataXfdfField" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormDataXfdf", propOrder = {
    "fields"
})
public class FormDataXfdf {

    protected ArrayOfFormDataXfdfField fields;

    /**
     * Gets the value of the fields property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFormDataXfdfField }
     *     
     */
    public ArrayOfFormDataXfdfField getFields() {
        return fields;
    }

    /**
     * Sets the value of the fields property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFormDataXfdfField }
     *     
     */
    public void setFields(ArrayOfFormDataXfdfField value) {
        this.fields = value;
    }

}
