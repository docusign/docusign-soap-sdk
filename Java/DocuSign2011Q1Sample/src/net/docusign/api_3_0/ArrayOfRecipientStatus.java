
package net.docusign.api_3_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRecipientStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRecipientStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecipientStatus" type="{http://www.docusign.net/API/3.0}RecipientStatus" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRecipientStatus", propOrder = {
    "recipientStatus"
})
public class ArrayOfRecipientStatus {

    @XmlElement(name = "RecipientStatus")
    protected List<RecipientStatus> recipientStatus;

    /**
     * Gets the value of the recipientStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recipientStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecipientStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RecipientStatus }
     * 
     * 
     */
    public List<RecipientStatus> getRecipientStatus() {
        if (recipientStatus == null) {
            recipientStatus = new ArrayList<RecipientStatus>();
        }
        return this.recipientStatus;
    }

}
