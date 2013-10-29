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
 * <p>Java class for FontSize.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FontSize">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Size7"/>
 *     &lt;enumeration value="Size8"/>
 *     &lt;enumeration value="Size9"/>
 *     &lt;enumeration value="Size10"/>
 *     &lt;enumeration value="Size11"/>
 *     &lt;enumeration value="Size12"/>
 *     &lt;enumeration value="Size14"/>
 *     &lt;enumeration value="Size16"/>
 *     &lt;enumeration value="Size18"/>
 *     &lt;enumeration value="Size20"/>
 *     &lt;enumeration value="Size22"/>
 *     &lt;enumeration value="Size24"/>
 *     &lt;enumeration value="Size26"/>
 *     &lt;enumeration value="Size28"/>
 *     &lt;enumeration value="Size36"/>
 *     &lt;enumeration value="Size48"/>
 *     &lt;enumeration value="Size72"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FontSize")
@XmlEnum
public enum FontSize {

    @XmlEnumValue("Size7")
    SIZE_7("Size7"),
    @XmlEnumValue("Size8")
    SIZE_8("Size8"),
    @XmlEnumValue("Size9")
    SIZE_9("Size9"),
    @XmlEnumValue("Size10")
    SIZE_10("Size10"),
    @XmlEnumValue("Size11")
    SIZE_11("Size11"),
    @XmlEnumValue("Size12")
    SIZE_12("Size12"),
    @XmlEnumValue("Size14")
    SIZE_14("Size14"),
    @XmlEnumValue("Size16")
    SIZE_16("Size16"),
    @XmlEnumValue("Size18")
    SIZE_18("Size18"),
    @XmlEnumValue("Size20")
    SIZE_20("Size20"),
    @XmlEnumValue("Size22")
    SIZE_22("Size22"),
    @XmlEnumValue("Size24")
    SIZE_24("Size24"),
    @XmlEnumValue("Size26")
    SIZE_26("Size26"),
    @XmlEnumValue("Size28")
    SIZE_28("Size28"),
    @XmlEnumValue("Size36")
    SIZE_36("Size36"),
    @XmlEnumValue("Size48")
    SIZE_48("Size48"),
    @XmlEnumValue("Size72")
    SIZE_72("Size72");
    private final String value;

    FontSize(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FontSize fromValue(String v) {
        for (FontSize c: FontSize.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
