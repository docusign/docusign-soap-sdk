
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Font.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="Font">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Arial"/>
 *     &lt;enumeration value="ArialNarrow"/>
 *     &lt;enumeration value="Calibri"/>
 *     &lt;enumeration value="CourierNew"/>
 *     &lt;enumeration value="Garamond"/>
 *     &lt;enumeration value="Georgia"/>
 *     &lt;enumeration value="Helvetica"/>
 *     &lt;enumeration value="LucidaConsole"/>
 *     &lt;enumeration value="Tahoma"/>
 *     &lt;enumeration value="TimesNewRoman"/>
 *     &lt;enumeration value="Trebuchet"/>
 *     &lt;enumeration value="Verdana"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Font")
@XmlEnum
public enum Font {

    @XmlEnumValue("Arial")
    ARIAL("Arial"),
    @XmlEnumValue("ArialNarrow")
    ARIAL_NARROW("ArialNarrow"),
    @XmlEnumValue("Calibri")
    CALIBRI("Calibri"),
    @XmlEnumValue("CourierNew")
    COURIER_NEW("CourierNew"),
    @XmlEnumValue("Garamond")
    GARAMOND("Garamond"),
    @XmlEnumValue("Georgia")
    GEORGIA("Georgia"),
    @XmlEnumValue("Helvetica")
    HELVETICA("Helvetica"),
    @XmlEnumValue("LucidaConsole")
    LUCIDA_CONSOLE("LucidaConsole"),
    @XmlEnumValue("Tahoma")
    TAHOMA("Tahoma"),
    @XmlEnumValue("TimesNewRoman")
    TIMES_NEW_ROMAN("TimesNewRoman"),
    @XmlEnumValue("Trebuchet")
    TREBUCHET("Trebuchet"),
    @XmlEnumValue("Verdana")
    VERDANA("Verdana");
    private final String value;

    Font(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Font fromValue(String v) {
        for (Font c: Font.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
