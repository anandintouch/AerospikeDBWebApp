package com.aerospike.db.model;

/**
 * User credentils bean file.
 * 
 * @author anand prakash
 *
 */
public class Credential {
	String authCredential ;
	String hostName;
	String accountNumber;
	String username;
	String password;
	String secretValue;
	int port;
	

	public String getSecretValue() {
		return secretValue;
	}

	public void setSecretValue(String secretValue) {
		this.secretValue = secretValue;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getAuthCredential() {
		return authCredential;
	}

	public void setAuthCredential(String authCredential) {
		this.authCredential = authCredential;
	}

}
