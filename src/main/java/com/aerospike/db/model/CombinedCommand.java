package com.aerospike.db.model;

public class CombinedCommand {
	Record  record;
	PerformanceTest    test;

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public PerformanceTest getTest() {
		return test;
	}

	public void setTest(PerformanceTest test) {
		this.test = test;
	}



}
