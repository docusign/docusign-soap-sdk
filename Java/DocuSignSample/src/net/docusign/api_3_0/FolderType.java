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
 * <p>Java class for FolderType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="FolderType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="RecycleBin"/>
 *     &lt;enumeration value="Draft"/>
 *     &lt;enumeration value="Inbox"/>
 *     &lt;enumeration value="SentItems"/>
 *     &lt;enumeration value="Normal"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "FolderType")
@XmlEnum
public enum FolderType {

    @XmlEnumValue("RecycleBin")
    RECYCLE_BIN("RecycleBin"),
    @XmlEnumValue("Draft")
    DRAFT("Draft"),
    @XmlEnumValue("Inbox")
    INBOX("Inbox"),
    @XmlEnumValue("SentItems")
    SENT_ITEMS("SentItems"),
    @XmlEnumValue("Normal")
    NORMAL("Normal");
    private final String value;

    FolderType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FolderType fromValue(String v) {
        for (FolderType c: FolderType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
