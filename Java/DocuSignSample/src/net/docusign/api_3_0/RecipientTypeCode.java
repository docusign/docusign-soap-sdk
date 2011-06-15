
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RecipientTypeCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RecipientTypeCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Signer"/>
 *     &lt;enumeration value="Agent"/>
 *     &lt;enumeration value="Editor"/>
 *     &lt;enumeration value="CarbonCopy"/>
 *     &lt;enumeration value="CertifiedDelivery"/>
 *     &lt;enumeration value="InPersonSigner"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RecipientTypeCode")
@XmlEnum
public enum RecipientTypeCode {

    @XmlEnumValue("Signer")
    SIGNER("Signer"),
    @XmlEnumValue("Agent")
    AGENT("Agent"),
    @XmlEnumValue("Editor")
    EDITOR("Editor"),
    @XmlEnumValue("CarbonCopy")
    CARBON_COPY("CarbonCopy"),
    @XmlEnumValue("CertifiedDelivery")
    CERTIFIED_DELIVERY("CertifiedDelivery"),
    @XmlEnumValue("InPersonSigner")
    IN_PERSON_SIGNER("InPersonSigner");
    private final String value;

    RecipientTypeCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RecipientTypeCode fromValue(String v) {
        for (RecipientTypeCode c: RecipientTypeCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
