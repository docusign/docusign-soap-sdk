package net.docusign.sample;


public class DocusignAPICredentials {
	private String integratorsKey;
	private String password;
	private String accountId;
	private String userId;
    private String userEmail;
	private String docusignWebserviceEndpoint;
	
    public String getIntegratorsKey() {
		return integratorsKey;
	}
	
	public void setIntegratorsKey(String integratorsKey) {
		this.integratorsKey = integratorsKey;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAccountId() {
		return accountId;
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
	
	public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    public String getDocusignWebserviceEndpoint() {
        return docusignWebserviceEndpoint;
    }

    public void setDocusignWebserviceEndpoint(String docusignWebserviceEndpoint) {
        this.docusignWebserviceEndpoint = docusignWebserviceEndpoint;
    }
}
