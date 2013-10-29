/**
 * @copyright Copyright (C) DocuSign, Inc.  All rights reserved.
 *
 * This source code is intended only as a supplement to DocuSign SDK
 * and/or on-line documentation.
 * 
 * This sample is designed to demonstrate DocuSign features and is not intended
 * for production use. Code and policy for a production application must be
 * developed to meet the specific data and security requirements of the
 * application.
 *
 * THIS CODE AND INFORMATION ARE PROVIDED "AS IS" WITHOUT WARRANTY OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND/OR FITNESS FOR A
 * PARTICULAR PURPOSE.
 */
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
 *     &lt;enumeration value="ar"/>
 *     &lt;enumeration value="bg"/>
 *     &lt;enumeration value="cs"/>
 *     &lt;enumeration value="da"/>
 *     &lt;enumeration value="de"/>
 *     &lt;enumeration value="el"/>
 *     &lt;enumeration value="en"/>
 *     &lt;enumeration value="en_GB"/>
 *     &lt;enumeration value="es"/>
 *     &lt;enumeration value="es_MX"/>
 *     &lt;enumeration value="et"/>
 *     &lt;enumeration value="fa"/>
 *     &lt;enumeration value="fi"/>
 *     &lt;enumeration value="fr"/>
 *     &lt;enumeration value="fr_CA"/>
 *     &lt;enumeration value="he"/>
 *     &lt;enumeration value="hi"/>
 *     &lt;enumeration value="hr"/>
 *     &lt;enumeration value="hu"/>
 *     &lt;enumeration value="id"/>
 *     &lt;enumeration value="it"/>
 *     &lt;enumeration value="ja"/>
 *     &lt;enumeration value="ko"/>
 *     &lt;enumeration value="lt"/>
 *     &lt;enumeration value="lv"/>
 *     &lt;enumeration value="ms"/>
 *     &lt;enumeration value="nl"/>
 *     &lt;enumeration value="no"/>
 *     &lt;enumeration value="pl"/>
 *     &lt;enumeration value="pt"/>
 *     &lt;enumeration value="pt_BR"/>
 *     &lt;enumeration value="ro"/>
 *     &lt;enumeration value="ru"/>
 *     &lt;enumeration value="sk"/>
 *     &lt;enumeration value="sl"/>
 *     &lt;enumeration value="sr"/>
 *     &lt;enumeration value="sv"/>
 *     &lt;enumeration value="th"/>
 *     &lt;enumeration value="tr"/>
 *     &lt;enumeration value="uk"/>
 *     &lt;enumeration value="vi"/>
 *     &lt;enumeration value="zh_CN"/>
 *     &lt;enumeration value="zh_TW"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SupportedLanguage")
@XmlEnum
public enum SupportedLanguage {

    @XmlEnumValue("ar")
    AR("ar"),
    @XmlEnumValue("bg")
    BG("bg"),
    @XmlEnumValue("cs")
    CS("cs"),
    @XmlEnumValue("da")
    DA("da"),
    @XmlEnumValue("de")
    DE("de"),
    @XmlEnumValue("el")
    EL("el"),
    @XmlEnumValue("en")
    EN("en"),
    @XmlEnumValue("en_GB")
    EN_GB("en_GB"),
    @XmlEnumValue("es")
    ES("es"),
    @XmlEnumValue("es_MX")
    ES_MX("es_MX"),
    @XmlEnumValue("et")
    ET("et"),
    @XmlEnumValue("fa")
    FA("fa"),
    @XmlEnumValue("fi")
    FI("fi"),
    @XmlEnumValue("fr")
    FR("fr"),
    @XmlEnumValue("fr_CA")
    FR_CA("fr_CA"),
    @XmlEnumValue("he")
    HE("he"),
    @XmlEnumValue("hi")
    HI("hi"),
    @XmlEnumValue("hr")
    HR("hr"),
    @XmlEnumValue("hu")
    HU("hu"),
    @XmlEnumValue("id")
    ID("id"),
    @XmlEnumValue("it")
    IT("it"),
    @XmlEnumValue("ja")
    JA("ja"),
    @XmlEnumValue("ko")
    KO("ko"),
    @XmlEnumValue("lt")
    LT("lt"),
    @XmlEnumValue("lv")
    LV("lv"),
    @XmlEnumValue("ms")
    MS("ms"),
    @XmlEnumValue("nl")
    NL("nl"),
    @XmlEnumValue("no")
    NO("no"),
    @XmlEnumValue("pl")
    PL("pl"),
    @XmlEnumValue("pt")
    PT("pt"),
    @XmlEnumValue("pt_BR")
    PT_BR("pt_BR"),
    @XmlEnumValue("ro")
    RO("ro"),
    @XmlEnumValue("ru")
    RU("ru"),
    @XmlEnumValue("sk")
    SK("sk"),
    @XmlEnumValue("sl")
    SL("sl"),
    @XmlEnumValue("sr")
    SR("sr"),
    @XmlEnumValue("sv")
    SV("sv"),
    @XmlEnumValue("th")
    TH("th"),
    @XmlEnumValue("tr")
    TR("tr"),
    @XmlEnumValue("uk")
    UK("uk"),
    @XmlEnumValue("vi")
    VI("vi"),
    @XmlEnumValue("zh_CN")
    ZH_CN("zh_CN"),
    @XmlEnumValue("zh_TW")
    ZH_TW("zh_TW");
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
