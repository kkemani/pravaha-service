package org.pravaha.bpmn.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "Tbl_process_definition")
@Data
public class ProcessDefinitionDomain {
	
	@Id
	@Column(name="PROCESS_NAME")
	private String processName;
	
	@Column(name="PROCESS_FILE")
	private String processFileName;
	
	@Column(name="PROCESS_VERSION")
	private String processVersion;

	@Override
	public String toString() {
		return "ProcessDefinitionDomain [processName=" + processName + ", processFileName=" + processFileName
				+ ", processVersion=" + processVersion + "]";
	}
	
	

}
