
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TemplateLocationCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TemplateLocationCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SOAP"/>
 *     &lt;enumeration value="PDFMetaData"/>
 *     &lt;enumeration value="Server"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TemplateLocationCode")
@XmlEnum
public enum TemplateLocationCode {

    SOAP("SOAP"),
    @XmlEnumValue("PDFMetaData")
    PDF_META_DATA("PDFMetaData"),
    @XmlEnumValue("Server")
    SERVER("Server");
    private final String value;

    TemplateLocationCode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TemplateLocationCode fromValue(String v) {
        for (TemplateLocationCode c: TemplateLocationCode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
