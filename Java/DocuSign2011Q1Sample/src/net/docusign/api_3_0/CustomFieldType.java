
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomFieldType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CustomFieldType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Text"/>
 *     &lt;enumeration value="List"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CustomFieldType")
@XmlEnum
public enum CustomFieldType {

    @XmlEnumValue("Text")
    TEXT("Text"),
    @XmlEnumValue("List")
    LIST("List");
    private final String value;

    CustomFieldType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CustomFieldType fromValue(String v) {
        for (CustomFieldType c: CustomFieldType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
