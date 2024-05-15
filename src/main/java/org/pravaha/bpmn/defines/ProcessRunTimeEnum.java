package org.pravaha.bpmn.defines;

public enum ProcessRunTimeEnum {
	
	IN_PROGRESS_INT("1"),
	COMPLETED_INT("2"),
	FAILED_INT("3"),
	TOTAL_EXECUTED_INT("4"); 
	
	
	
	
	 private final String variableType;
	
	 ProcessRunTimeEnum(String varType){
		this.variableType = varType;
	}
	
	public String getValue() {
		return this.variableType;
	}
	

}
