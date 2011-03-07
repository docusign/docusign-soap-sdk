
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnitTypeCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="UnitTypeCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Pixels"/>
 *     &lt;enumeration value="Mms"/>
 *     &lt;enumeration value="Cms"/>
 *     &lt;enumeration value="Inches"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "UnitTypeCode")
@XmlEnum
public enum UnitTypeCode {

    @XmlEnumValue("Pixels")
    PIXELS("Pixels"),
    @XmlEnumValue("Mms")
    MMS("Mms"),
    @XmlEnumValue("Cms")
    CMS("Cms"),
    @XmlEnumValue("Inches")
    INCHES("Inches");
    private final String value;

    UnitTypeCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UnitTypeCode fromValue(String v) {
        for (UnitTypeCode c: UnitTypeCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
