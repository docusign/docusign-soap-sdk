
package net.docusign.api_3_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfCompositeTemplate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCompositeTemplate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CompositeTemplate" type="{http://www.docusign.net/API/3.0}CompositeTemplate" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCompositeTemplate", propOrder = {
    "compositeTemplate"
})
public class ArrayOfCompositeTemplate {

    @XmlElement(name = "CompositeTemplate", nillable = true)
    protected List<CompositeTemplate> compositeTemplate;

    /**
     * Gets the value of the compositeTemplate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the compositeTemplate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompositeTemplate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CompositeTemplate }
     * 
     * 
     */
    public List<CompositeTemplate> getCompositeTemplate() {
        if (compositeTemplate == null) {
            compositeTemplate = new ArrayList<CompositeTemplate>();
        }
        return this.compositeTemplate;
    }

}
