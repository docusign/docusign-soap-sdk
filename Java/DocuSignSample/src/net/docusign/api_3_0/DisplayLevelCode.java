
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DisplayLevelCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DisplayLevelCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ReadOnly"/>
 *     &lt;enumeration value="Editable"/>
 *     &lt;enumeration value="DoNotDisplay"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DisplayLevelCode")
@XmlEnum
public enum DisplayLevelCode {

    @XmlEnumValue("ReadOnly")
    READ_ONLY("ReadOnly"),
    @XmlEnumValue("Editable")
    EDITABLE("Editable"),
    @XmlEnumValue("DoNotDisplay")
    DO_NOT_DISPLAY("DoNotDisplay");
    private final String value;

    DisplayLevelCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DisplayLevelCode fromValue(String v) {
        for (DisplayLevelCode c: DisplayLevelCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
