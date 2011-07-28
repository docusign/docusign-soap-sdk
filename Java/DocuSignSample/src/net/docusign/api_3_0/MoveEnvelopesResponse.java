
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MoveEnvelopesResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "moveEnvelopesResult"
})
@XmlRootElement(name = "MoveEnvelopesResponse")
public class MoveEnvelopesResponse {

    @XmlElement(name = "MoveEnvelopesResult")
    protected boolean moveEnvelopesResult;

    /**
     * Gets the value of the moveEnvelopesResult property.
     * 
     */
    public boolean isMoveEnvelopesResult() {
        return moveEnvelopesResult;
    }

    /**
     * Sets the value of the moveEnvelopesResult property.
     * 
     */
    public void setMoveEnvelopesResult(boolean value) {
        this.moveEnvelopesResult = value;
    }

}
