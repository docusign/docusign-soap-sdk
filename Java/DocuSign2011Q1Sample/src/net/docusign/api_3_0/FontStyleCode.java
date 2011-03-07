
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FontStyleCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FontStyleCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RageItalic"/>
 *     &lt;enumeration value="Mistral"/>
 *     &lt;enumeration value="BradleyHandITC"/>
 *     &lt;enumeration value="KaufmannBT"/>
 *     &lt;enumeration value="Freehand575"/>
 *     &lt;enumeration value="LuciaBT"/>
 *     &lt;enumeration value="DocuSign1"/>
 *     &lt;enumeration value="DocuSign2"/>
 *     &lt;enumeration value="DocuSign3"/>
 *     &lt;enumeration value="DocuSign4"/>
 *     &lt;enumeration value="DocuSign5"/>
 *     &lt;enumeration value="DocuSign6"/>
 *     &lt;enumeration value="DocuSign7"/>
 *     &lt;enumeration value="DocuSign8"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FontStyleCode")
@XmlEnum
public enum FontStyleCode {

    @XmlEnumValue("RageItalic")
    RAGE_ITALIC("RageItalic"),
    @XmlEnumValue("Mistral")
    MISTRAL("Mistral"),
    @XmlEnumValue("BradleyHandITC")
    BRADLEY_HAND_ITC("BradleyHandITC"),
    @XmlEnumValue("KaufmannBT")
    KAUFMANN_BT("KaufmannBT"),
    @XmlEnumValue("Freehand575")
    FREEHAND_575("Freehand575"),
    @XmlEnumValue("LuciaBT")
    LUCIA_BT("LuciaBT"),
    @XmlEnumValue("DocuSign1")
    DOCU_SIGN_1("DocuSign1"),
    @XmlEnumValue("DocuSign2")
    DOCU_SIGN_2("DocuSign2"),
    @XmlEnumValue("DocuSign3")
    DOCU_SIGN_3("DocuSign3"),
    @XmlEnumValue("DocuSign4")
    DOCU_SIGN_4("DocuSign4"),
    @XmlEnumValue("DocuSign5")
    DOCU_SIGN_5("DocuSign5"),
    @XmlEnumValue("DocuSign6")
    DOCU_SIGN_6("DocuSign6"),
    @XmlEnumValue("DocuSign7")
    DOCU_SIGN_7("DocuSign7"),
    @XmlEnumValue("DocuSign8")
    DOCU_SIGN_8("DocuSign8");
    private final String value;

    FontStyleCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FontStyleCode fromValue(String v) {
        for (FontStyleCode c: FontStyleCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
