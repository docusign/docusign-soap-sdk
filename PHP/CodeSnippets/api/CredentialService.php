<?php
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
 

/*  commented out to avoid collision with Ping function in the other API (APIService.wsdl)
class Ping {
}

class PingResponse {
  public $PingResult; // boolean
}
*/

class Login {
  public $Email; // string
  public $Password; // string
  public $ReturnBaseUrl; // string
}

class LoginResponse {
  public $LoginResult; // LoginResult
}

class LoginResult {
  public $Success; // boolean
  public $ErrorCode; // ErrorCode
  public $AuthenticationMessage; // string
  public $Accounts; // ArrayOfAccount
}

class ErrorCode {
  const User_Does_Not_Exist_In_System = 'User_Does_Not_Exist_In_System';
  const Account_Lacks_Permissions = 'Account_Lacks_Permissions';
  const User_Lacks_Permissions = 'User_Lacks_Permissions';
  const User_Authentication_Failed = 'User_Authentication_Failed';
  const Unspecified_Error = 'Unspecified_Error';
  const Success = 'Success';
}

class Account {
  public $AccountID; // string
  public $AccountName; // string
  public $UserID; // string
  public $UserName; // string
  public $Email; // string
  public $BaseUrl; // string
}

/*  commented out to avoid collision with Ping function in the other API (APIService.wsdl)
class GetAuthenticationToken {
  public $Email; // string
  public $Password; // string
  public $AccountID; // string
  public $GoToEnvelopeID; // string
}

class GetAuthenticationTokenResponse {
  public $GetAuthenticationTokenResult; // string
}

class RequestSenderToken {
  public $Email; // string
  public $Password; // string
  public $AccountID; // string
  public $EnvelopeID; // string
  public $ReturnURL; // string
}

class RequestSenderTokenResponse {
  public $RequestSenderTokenResult; // string
}

class RequestCorrectToken {
  public $Email; // string
  public $Password; // string
  public $EnvelopeID; // string
  public $SuppressNavigation; // boolean
  public $ReturnURL; // string
}

class RequestCorrectTokenResponse {
  public $RequestCorrectTokenResult; // string
}
*/

/**
 * Credential class
 * 
 *  
 * 
 * @author    {author}
 * @copyright {copyright}
 * @package   {package}
 */
class CredentialService extends SoapClient {

  private static $classmap = array(
 //                                   'Ping' => 'Ping',
 //                                   'PingResponse' => 'PingResponse',
                                    'Login' => 'Login',
                                    'LoginResponse' => 'LoginResponse',
                                    'LoginResult' => 'LoginResult',
                                    'ErrorCode' => 'ErrorCode',
                                    'Account' => 'Account',
//                                    'GetAuthenticationToken' => 'GetAuthenticationToken',
//                                   'GetAuthenticationTokenResponse' => 'GetAuthenticationTokenResponse',
//                                    'RequestSenderToken' => 'RequestSenderToken',
//                                    'RequestSenderTokenResponse' => 'RequestSenderTokenResponse',
//                                    'RequestCorrectToken' => 'RequestCorrectToken',
//                                    'RequestCorrectTokenResponse' => 'RequestCorrectTokenResponse',
                                   );

  public function Credential($wsdl = "CredentialService.wsdl", $options = array()) {
    foreach(self::$classmap as $key => $value) {
      if(!isset($options['classmap'][$key])) {
        $options['classmap'][$key] = $value;
      }
    }
    parent::__construct($wsdl, $options);
  }

  /**
   *  
   *
   * @param Login $parameters
   * @return LoginResponse
   */
  public function Login(Login $parameters) {
    return $this->__soapCall('Login', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/Credential',
            'soapaction' => ''
           )
      );
  }

  /**
   *  
   *
   * @param GetAuthenticationToken $parameters
   * @return GetAuthenticationTokenResponse
   */
	/* commented out to avoid collision with Ping function in the other API (APIService.wsdl)
  public function GetAuthenticationToken(GetAuthenticationToken $parameters) {
    return $this->__soapCall('GetAuthenticationToken', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/Credential',
            'soapaction' => ''
           )
      );
  }
	*/

	  /**
	   *  
	   *
	   * @param Ping $parameters
	   * @return PingResponse
	   */
	/* commented out to avoid collision with Ping function in the other API (APIService.wsdl)
	  public function Ping(Ping $parameters) {
	    return $this->__soapCall('Ping', array($parameters),       array(
	            'uri' => 'http://www.docusign.net/API/Credential',
	            'soapaction' => ''
	           )
	      );
	  }
		*/

  /**
   *  
   *
   * @param RequestSenderToken $parameters
   * @return RequestSenderTokenResponse
   */
	/* commented out to avoid collision with Ping function in the other API (APIService.wsdl)
  public function RequestSenderToken(RequestSenderToken $parameters) {
    return $this->__soapCall('RequestSenderToken', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/Credential',
            'soapaction' => ''
           )
      );
  }
	*/

  /**
   *  
   *
   * @param RequestCorrectToken $parameters
   * @return RequestCorrectTokenResponse
   */
	/* commented out to avoid collision with Ping function in the other API (APIService.wsdl)
  public function RequestCorrectToken(RequestCorrectToken $parameters) {
    return $this->__soapCall('RequestCorrectToken', array($parameters),       array(
            'uri' => 'http://www.docusign.net/API/Credential',
            'soapaction' => ''
           )
      );
  }
	*/
}

?>
