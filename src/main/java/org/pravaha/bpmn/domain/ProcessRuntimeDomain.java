package org.pravaha.bpmn.domain;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="TBL_PROCESS_RUNTIME")
@Data
public class ProcessRuntimeDomain implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PROCESS_ID")
	private String processId;
	
	@Column(name="BUSINESS_KEY")
	private String businessKey;
	
	@Column(name="PROCESS_NAME")
	private String processName;
	
	
	@Column(name="START_DATE")
	private Date startDate;


	@Column(name="LAST_UPDATE_DATE")
	private Date lastUpdateDate;

	@Column(name="END_DATE")
	private Date endDate;

	@Column(name="STATUS")
	private int status;

	@Column(name="PARENT_PROCESS_ID")
	private String parentProcessId;
	
	@Column(name="VERSION")
	private String processVer;

	public ProcessRuntimeDomain() {
		
	}

	
	public String toString()
	{
		StringBuffer strbuffer= new StringBuffer();
		strbuffer.append(this.processId+ " => "+" || "+this.businessKey+" || "+this.processName+" || "+this.status+" || "+this.processVer);
		return strbuffer.toString();
	}
}