
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SupportedLanguage.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SupportedLanguage">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="zh_TW"/>
 *     &lt;enumeration value="nl"/>
 *     &lt;enumeration value="en"/>
 *     &lt;enumeration value="fr"/>
 *     &lt;enumeration value="de"/>
 *     &lt;enumeration value="it"/>
 *     &lt;enumeration value="ja"/>
 *     &lt;enumeration value="ko"/>
 *     &lt;enumeration value="pt"/>
 *     &lt;enumeration value="ru"/>
 *     &lt;enumeration value="es"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SupportedLanguage")
@XmlEnum
public enum SupportedLanguage {

    @XmlEnumValue("zh_TW")
    ZH_TW("zh_TW"),
    @XmlEnumValue("nl")
    NL("nl"),
    @XmlEnumValue("en")
    EN("en"),
    @XmlEnumValue("fr")
    FR("fr"),
    @XmlEnumValue("de")
    DE("de"),
    @XmlEnumValue("it")
    IT("it"),
    @XmlEnumValue("ja")
    JA("ja"),
    @XmlEnumValue("ko")
    KO("ko"),
    @XmlEnumValue("pt")
    PT("pt"),
    @XmlEnumValue("ru")
    RU("ru"),
    @XmlEnumValue("es")
    ES("es");
    private final String value;

    SupportedLanguage(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SupportedLanguage fromValue(String v) {
        for (SupportedLanguage c: SupportedLanguage.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
