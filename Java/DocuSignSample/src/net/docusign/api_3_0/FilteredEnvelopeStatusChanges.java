
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FilteredEnvelopeStatusChanges complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FilteredEnvelopeStatusChanges">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResultSetSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="EnvelopeStatusChanges" type="{http://www.docusign.net/API/3.0}ArrayOfEnvelopeStatusChange"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FilteredEnvelopeStatusChanges", propOrder = {
    "resultSetSize",
    "envelopeStatusChanges"
})
public class FilteredEnvelopeStatusChanges {

    @XmlElement(name = "ResultSetSize")
    protected int resultSetSize;
    @XmlElement(name = "EnvelopeStatusChanges", required = true, nillable = true)
    protected ArrayOfEnvelopeStatusChange envelopeStatusChanges;

    /**
     * Gets the value of the resultSetSize property.
     * 
     */
    public int getResultSetSize() {
        return resultSetSize;
    }

    /**
     * Sets the value of the resultSetSize property.
     * 
     */
    public void setResultSetSize(int value) {
        this.resultSetSize = value;
    }

    /**
     * Gets the value of the envelopeStatusChanges property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEnvelopeStatusChange }
     *     
     */
    public ArrayOfEnvelopeStatusChange getEnvelopeStatusChanges() {
        return envelopeStatusChanges;
    }

    /**
     * Sets the value of the envelopeStatusChanges property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEnvelopeStatusChange }
     *     
     */
    public void setEnvelopeStatusChanges(ArrayOfEnvelopeStatusChange value) {
        this.envelopeStatusChanges = value;
    }

}
