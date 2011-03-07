
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VaultingModeCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="VaultingModeCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="EODeStore"/>
 *     &lt;enumeration value="EODAuthoritativeCopy"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "VaultingModeCode")
@XmlEnum
public enum VaultingModeCode {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("EODeStore")
    EO_DE_STORE("EODeStore"),
    @XmlEnumValue("EODAuthoritativeCopy")
    EOD_AUTHORITATIVE_COPY("EODAuthoritativeCopy");
    private final String value;

    VaultingModeCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static VaultingModeCode fromValue(String v) {
        for (VaultingModeCode c: VaultingModeCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
