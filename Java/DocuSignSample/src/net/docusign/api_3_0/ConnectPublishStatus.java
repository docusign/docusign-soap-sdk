
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConnectPublishStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ConnectPublishStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Queued"/>
 *     &lt;enumeration value="Success"/>
 *     &lt;enumeration value="Fail"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ConnectPublishStatus")
@XmlEnum
public enum ConnectPublishStatus {

    @XmlEnumValue("Queued")
    QUEUED("Queued"),
    @XmlEnumValue("Success")
    SUCCESS("Success"),
    @XmlEnumValue("Fail")
    FAIL("Fail");
    private final String value;

    ConnectPublishStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ConnectPublishStatus fromValue(String v) {
        for (ConnectPublishStatus c: ConnectPublishStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
