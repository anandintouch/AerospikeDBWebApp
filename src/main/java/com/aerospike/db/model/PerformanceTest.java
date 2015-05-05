package com.aerospike.db.model;

import java.util.List;

public class PerformanceTest {

	String setName;
	String binName;
	String binValue;
	String hostName;
	String nodeName;
	String loggedInHostName;
	List<String> hostNames;
	String secretValue;
	String recordCount="0";
	

	public String getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}
	

	public String getSecretValue() {
		return secretValue;
	}

	public void setSecretValue(String secretValue) {
		this.secretValue = secretValue;
	}

	public List<String> getHostNames() {
		return hostNames;
	}

	public void setHostNames(List<String> hostNames) {
		this.hostNames = hostNames;
	}

	public String getLoggedInHostName() {
		return loggedInHostName;
	}

	public void setLoggedInHostName(String loggedInHostName) {
		this.loggedInHostName = loggedInHostName;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	public String getBinName() {
		return binName;
	}

	public void setBinName(String binName) {
		this.binName = binName;
	}

	public String getBinValue() {
		return binValue;
	}

	public void setBinValue(String binValue) {
		this.binValue = binValue;
	}

	public String getSetName() {
		return setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}


}
