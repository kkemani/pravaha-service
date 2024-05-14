package org.pravaha.bpmn.domain;

import java.util.List;

import lombok.Data;

@Data
public class ProcessDetailsDomain {
	
	private String processId;
	
	private ProcessRuntimeDomain processRuntimeDomain;
	
	private ProcessContextDomain processContextDomain;
	
	private List<ProcessEventWatchDomain> processEventWatchDomain;
	
	private List<ProcessTaskDomain> processTaskDomain;
	
	public ProcessDetailsDomain() {
		
	}
	
	
	@Override
	public String toString() {
		return "ProcessDetailsVO [processId=" + processId + ", processRuntimVO=" + processRuntimeDomain
				+ ", processContextVO=" + processContextDomain + ", processEventWatchVO=" + processEventWatchDomain
				+ ", processTaskVO=" + processTaskDomain + "]";
	}

}
