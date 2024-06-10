package org.pravaha.bpmn.defines;

public enum ProcessRunTimeEnum {
	
	IN_PROGRESS_INT("1"),
	COMPLETED_INT("2"),
	FAILED_INT("3"),
	TOTAL_EXECUTED_INT("4"),
	IN_PROGRESS_STRING("InProgress"),
	COMPLETED_STRING("Completed"),
	FAILED_STRING("Failed"),
	TOTAL_EXECUTED_STRING("Executed"),
	STATUS("status"),
	COUNT("count");
	
	
	
	
	 private final String variableType;
	
	 ProcessRunTimeEnum(String varType){
		this.variableType = varType;
	}
	
	public String getValue() {
		return this.variableType;
	}
	

}
