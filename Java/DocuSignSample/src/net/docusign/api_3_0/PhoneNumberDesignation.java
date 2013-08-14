
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PhoneNumberDesignation.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PhoneNumberDesignation">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Home"/>
 *     &lt;enumeration value="Mobile"/>
 *     &lt;enumeration value="Work"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="Fax"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PhoneNumberDesignation")
@XmlEnum
public enum PhoneNumberDesignation {

    @XmlEnumValue("Home")
    HOME("Home"),
    @XmlEnumValue("Mobile")
    MOBILE("Mobile"),
    @XmlEnumValue("Work")
    WORK("Work"),
    @XmlEnumValue("Other")
    OTHER("Other"),
    @XmlEnumValue("Fax")
    FAX("Fax");
    private final String value;

    PhoneNumberDesignation(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PhoneNumberDesignation fromValue(String v) {
        for (PhoneNumberDesignation c: PhoneNumberDesignation.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
