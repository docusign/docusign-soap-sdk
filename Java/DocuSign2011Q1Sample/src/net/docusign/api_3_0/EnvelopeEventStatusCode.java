
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnvelopeEventStatusCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EnvelopeEventStatusCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Sent"/>
 *     &lt;enumeration value="Delivered"/>
 *     &lt;enumeration value="Completed"/>
 *     &lt;enumeration value="Declined"/>
 *     &lt;enumeration value="Voided"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EnvelopeEventStatusCode")
@XmlEnum
public enum EnvelopeEventStatusCode {

    @XmlEnumValue("Sent")
    SENT("Sent"),
    @XmlEnumValue("Delivered")
    DELIVERED("Delivered"),
    @XmlEnumValue("Completed")
    COMPLETED("Completed"),
    @XmlEnumValue("Declined")
    DECLINED("Declined"),
    @XmlEnumValue("Voided")
    VOIDED("Voided");
    private final String value;

    EnvelopeEventStatusCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EnvelopeEventStatusCode fromValue(String v) {
        for (EnvelopeEventStatusCode c: EnvelopeEventStatusCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
