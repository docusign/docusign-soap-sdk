
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RecipientEventStatusCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RecipientEventStatusCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Sent"/>
 *     &lt;enumeration value="Delivered"/>
 *     &lt;enumeration value="Completed"/>
 *     &lt;enumeration value="Declined"/>
 *     &lt;enumeration value="AutoResponded"/>
 *     &lt;enumeration value="AuthenticationFailed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RecipientEventStatusCode")
@XmlEnum
public enum RecipientEventStatusCode {

    @XmlEnumValue("Sent")
    SENT("Sent"),
    @XmlEnumValue("Delivered")
    DELIVERED("Delivered"),
    @XmlEnumValue("Completed")
    COMPLETED("Completed"),
    @XmlEnumValue("Declined")
    DECLINED("Declined"),
    @XmlEnumValue("AutoResponded")
    AUTO_RESPONDED("AutoResponded"),
    @XmlEnumValue("AuthenticationFailed")
    AUTHENTICATION_FAILED("AuthenticationFailed");
    private final String value;

    RecipientEventStatusCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RecipientEventStatusCode fromValue(String v) {
        for (RecipientEventStatusCode c: RecipientEventStatusCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
