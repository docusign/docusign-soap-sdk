
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EventStatusCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="EventStatusCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Passed"/>
 *     &lt;enumeration value="Failed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EventStatusCode")
@XmlEnum
public enum EventStatusCode {

    @XmlEnumValue("Passed")
    PASSED("Passed"),
    @XmlEnumValue("Failed")
    FAILED("Failed");
    private final String value;

    EventStatusCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EventStatusCode fromValue(String v) {
        for (EventStatusCode c: EventStatusCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
