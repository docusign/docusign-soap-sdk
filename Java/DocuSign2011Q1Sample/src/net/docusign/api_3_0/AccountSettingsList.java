
package net.docusign.api_3_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountSettingsList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountSettingsList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AccountSetting" type="{http://www.docusign.net/API/3.0}AccountSetting" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountSettingsList", propOrder = {
    "accountSetting"
})
public class AccountSettingsList {

    @XmlElement(name = "AccountSetting")
    protected List<AccountSetting> accountSetting;

    /**
     * Gets the value of the accountSetting property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accountSetting property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccountSetting().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccountSetting }
     * 
     * 
     */
    public List<AccountSetting> getAccountSetting() {
        if (accountSetting == null) {
            accountSetting = new ArrayList<AccountSetting>();
        }
        return this.accountSetting;
    }

}
