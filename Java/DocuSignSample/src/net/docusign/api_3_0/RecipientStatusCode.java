
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RecipientStatusCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RecipientStatusCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Created"/>
 *     &lt;enumeration value="Sent"/>
 *     &lt;enumeration value="Delivered"/>
 *     &lt;enumeration value="Signed"/>
 *     &lt;enumeration value="Declined"/>
 *     &lt;enumeration value="Completed"/>
 *     &lt;enumeration value="FaxPending"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RecipientStatusCode")
@XmlEnum
public enum RecipientStatusCode {

    @XmlEnumValue("Created")
    CREATED("Created"),
    @XmlEnumValue("Sent")
    SENT("Sent"),
    @XmlEnumValue("Delivered")
    DELIVERED("Delivered"),
    @XmlEnumValue("Signed")
    SIGNED("Signed"),
    @XmlEnumValue("Declined")
    DECLINED("Declined"),
    @XmlEnumValue("Completed")
    COMPLETED("Completed"),
    @XmlEnumValue("FaxPending")
    FAX_PENDING("FaxPending");
    private final String value;

    RecipientStatusCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RecipientStatusCode fromValue(String v) {
        for (RecipientStatusCode c: RecipientStatusCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
