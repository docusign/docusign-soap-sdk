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
 * <p>Java class for CustomTabType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CustomTabType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Text"/>
 *     &lt;enumeration value="Checkbox"/>
 *     &lt;enumeration value="Radio"/>
 *     &lt;enumeration value="List"/>
 *     &lt;enumeration value="Date"/>
 *     &lt;enumeration value="Number"/>
 *     &lt;enumeration value="SSN"/>
 *     &lt;enumeration value="ZIP5"/>
 *     &lt;enumeration value="ZIP5DASH4"/>
 *     &lt;enumeration value="Email"/>
 *     &lt;enumeration value="Note"/>
 *     &lt;enumeration value="Formula"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CustomTabType")
@XmlEnum
public enum CustomTabType {

    @XmlEnumValue("Text")
    TEXT("Text"),
    @XmlEnumValue("Checkbox")
    CHECKBOX("Checkbox"),
    @XmlEnumValue("Radio")
    RADIO("Radio"),
    @XmlEnumValue("List")
    LIST("List"),
    @XmlEnumValue("Date")
    DATE("Date"),
    @XmlEnumValue("Number")
    NUMBER("Number"),
    SSN("SSN"),
    @XmlEnumValue("ZIP5")
    ZIP_5("ZIP5"),
    @XmlEnumValue("ZIP5DASH4")
    ZIP_5_DASH_4("ZIP5DASH4"),
    @XmlEnumValue("Email")
    EMAIL("Email"),
    @XmlEnumValue("Note")
    NOTE("Note"),
    @XmlEnumValue("Formula")
    FORMULA("Formula");
    private final String value;

    CustomTabType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CustomTabType fromValue(String v) {
        for (CustomTabType c: CustomTabType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
