
package net.docusign.api_3_0;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequestRecipientTokenAuthenticationAssertionAuthenticationMethod.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RequestRecipientTokenAuthenticationAssertionAuthenticationMethod">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Password"/>
 *     &lt;enumeration value="Email"/>
 *     &lt;enumeration value="PaperDocuments"/>
 *     &lt;enumeration value="HTTPBasicAuth"/>
 *     &lt;enumeration value="SSLMutualAuth"/>
 *     &lt;enumeration value="X509Certificate"/>
 *     &lt;enumeration value="Kerberos"/>
 *     &lt;enumeration value="SingleSignOn_CASiteminder"/>
 *     &lt;enumeration value="SingleSignOn_InfoCard"/>
 *     &lt;enumeration value="SingleSignOn_MicrosoftActiveDirectory"/>
 *     &lt;enumeration value="SingleSignOn_Passport"/>
 *     &lt;enumeration value="SingleSignOn_SAML"/>
 *     &lt;enumeration value="SingleSignOn_Other"/>
 *     &lt;enumeration value="Smartcard"/>
 *     &lt;enumeration value="RSASecureID"/>
 *     &lt;enumeration value="Biometric"/>
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="KnowledgeBasedAuth"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RequestRecipientTokenAuthenticationAssertionAuthenticationMethod")
@XmlEnum
public enum RequestRecipientTokenAuthenticationAssertionAuthenticationMethod {

    @XmlEnumValue("Password")
    PASSWORD("Password"),
    @XmlEnumValue("Email")
    EMAIL("Email"),
    @XmlEnumValue("PaperDocuments")
    PAPER_DOCUMENTS("PaperDocuments"),
    @XmlEnumValue("HTTPBasicAuth")
    HTTP_BASIC_AUTH("HTTPBasicAuth"),
    @XmlEnumValue("SSLMutualAuth")
    SSL_MUTUAL_AUTH("SSLMutualAuth"),
    @XmlEnumValue("X509Certificate")
    X_509_CERTIFICATE("X509Certificate"),
    @XmlEnumValue("Kerberos")
    KERBEROS("Kerberos"),
    @XmlEnumValue("SingleSignOn_CASiteminder")
    SINGLE_SIGN_ON_CA_SITEMINDER("SingleSignOn_CASiteminder"),
    @XmlEnumValue("SingleSignOn_InfoCard")
    SINGLE_SIGN_ON_INFO_CARD("SingleSignOn_InfoCard"),
    @XmlEnumValue("SingleSignOn_MicrosoftActiveDirectory")
    SINGLE_SIGN_ON_MICROSOFT_ACTIVE_DIRECTORY("SingleSignOn_MicrosoftActiveDirectory"),
    @XmlEnumValue("SingleSignOn_Passport")
    SINGLE_SIGN_ON_PASSPORT("SingleSignOn_Passport"),
    @XmlEnumValue("SingleSignOn_SAML")
    SINGLE_SIGN_ON_SAML("SingleSignOn_SAML"),
    @XmlEnumValue("SingleSignOn_Other")
    SINGLE_SIGN_ON_OTHER("SingleSignOn_Other"),
    @XmlEnumValue("Smartcard")
    SMARTCARD("Smartcard"),
    @XmlEnumValue("RSASecureID")
    RSA_SECURE_ID("RSASecureID"),
    @XmlEnumValue("Biometric")
    BIOMETRIC("Biometric"),
    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("KnowledgeBasedAuth")
    KNOWLEDGE_BASED_AUTH("KnowledgeBasedAuth");
    private final String value;

    RequestRecipientTokenAuthenticationAssertionAuthenticationMethod(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RequestRecipientTokenAuthenticationAssertionAuthenticationMethod fromValue(String v) {
        for (RequestRecipientTokenAuthenticationAssertionAuthenticationMethod c: RequestRecipientTokenAuthenticationAssertionAuthenticationMethod.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
