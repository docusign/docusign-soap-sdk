
package net.docusign.api_3_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRecipientCorrectionStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRecipientCorrectionStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RecipientCorrectionStatus" type="{http://www.docusign.net/API/3.0}RecipientCorrectionStatus" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRecipientCorrectionStatus", propOrder = {
    "recipientCorrectionStatus"
})
public class ArrayOfRecipientCorrectionStatus {

    @XmlElement(name = "RecipientCorrectionStatus")
    protected List<RecipientCorrectionStatus> recipientCorrectionStatus;

    /**
     * Gets the value of the recipientCorrectionStatus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recipientCorrectionStatus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecipientCorrectionStatus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RecipientCorrectionStatus }
     * 
     * 
     */
    public List<RecipientCorrectionStatus> getRecipientCorrectionStatus() {
        if (recipientCorrectionStatus == null) {
            recipientCorrectionStatus = new ArrayList<RecipientCorrectionStatus>();
        }
        return this.recipientCorrectionStatus;
    }

}
