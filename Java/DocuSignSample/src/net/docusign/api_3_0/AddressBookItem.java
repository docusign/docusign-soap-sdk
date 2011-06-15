
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for AddressBookItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressBookItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AddressBookID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Shared" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Created" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Owner" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Phone1" type="{http://www.docusign.net/API/3.0}AddressBookPhoneNumber" minOccurs="0"/>
 *         &lt;element name="Phone2" type="{http://www.docusign.net/API/3.0}AddressBookPhoneNumber" minOccurs="0"/>
 *         &lt;element name="Phone3" type="{http://www.docusign.net/API/3.0}AddressBookPhoneNumber" minOccurs="0"/>
 *         &lt;element name="Phone4" type="{http://www.docusign.net/API/3.0}AddressBookPhoneNumber" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressBookItem", propOrder = {
    "addressBookID",
    "email",
    "userName",
    "accountName",
    "shared",
    "created",
    "owner",
    "phone1",
    "phone2",
    "phone3",
    "phone4"
})
public class AddressBookItem {

    @XmlElement(name = "AddressBookID")
    protected String addressBookID;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "AccountName")
    protected String accountName;
    @XmlElement(name = "Shared")
    protected boolean shared;
    @XmlElement(name = "Created")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar created;
    @XmlElement(name = "Owner")
    protected Boolean owner;
    @XmlElement(name = "Phone1")
    protected AddressBookPhoneNumber phone1;
    @XmlElement(name = "Phone2")
    protected AddressBookPhoneNumber phone2;
    @XmlElement(name = "Phone3")
    protected AddressBookPhoneNumber phone3;
    @XmlElement(name = "Phone4")
    protected AddressBookPhoneNumber phone4;

    /**
     * Gets the value of the addressBookID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressBookID() {
        return addressBookID;
    }

    /**
     * Sets the value of the addressBookID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressBookID(String value) {
        this.addressBookID = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the accountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the value of the accountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountName(String value) {
        this.accountName = value;
    }

    /**
     * Gets the value of the shared property.
     * 
     */
    public boolean isShared() {
        return shared;
    }

    /**
     * Sets the value of the shared property.
     * 
     */
    public void setShared(boolean value) {
        this.shared = value;
    }

    /**
     * Gets the value of the created property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreated() {
        return created;
    }

    /**
     * Sets the value of the created property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreated(XMLGregorianCalendar value) {
        this.created = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOwner(Boolean value) {
        this.owner = value;
    }

    /**
     * Gets the value of the phone1 property.
     * 
     * @return
     *     possible object is
     *     {@link AddressBookPhoneNumber }
     *     
     */
    public AddressBookPhoneNumber getPhone1() {
        return phone1;
    }

    /**
     * Sets the value of the phone1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressBookPhoneNumber }
     *     
     */
    public void setPhone1(AddressBookPhoneNumber value) {
        this.phone1 = value;
    }

    /**
     * Gets the value of the phone2 property.
     * 
     * @return
     *     possible object is
     *     {@link AddressBookPhoneNumber }
     *     
     */
    public AddressBookPhoneNumber getPhone2() {
        return phone2;
    }

    /**
     * Sets the value of the phone2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressBookPhoneNumber }
     *     
     */
    public void setPhone2(AddressBookPhoneNumber value) {
        this.phone2 = value;
    }

    /**
     * Gets the value of the phone3 property.
     * 
     * @return
     *     possible object is
     *     {@link AddressBookPhoneNumber }
     *     
     */
    public AddressBookPhoneNumber getPhone3() {
        return phone3;
    }

    /**
     * Sets the value of the phone3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressBookPhoneNumber }
     *     
     */
    public void setPhone3(AddressBookPhoneNumber value) {
        this.phone3 = value;
    }

    /**
     * Gets the value of the phone4 property.
     * 
     * @return
     *     possible object is
     *     {@link AddressBookPhoneNumber }
     *     
     */
    public AddressBookPhoneNumber getPhone4() {
        return phone4;
    }

    /**
     * Sets the value of the phone4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressBookPhoneNumber }
     *     
     */
    public void setPhone4(AddressBookPhoneNumber value) {
        this.phone4 = value;
    }

}
