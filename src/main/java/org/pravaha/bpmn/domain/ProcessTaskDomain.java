package org.pravaha.bpmn.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "TBL_PROCESS_TASK")
@Data
public class ProcessTaskDomain implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "TASK_ID")
	private long taskId;

	@Column(name = "PROCESS_ID")
	private String processId;

	@Column(name = "TASK_NAME")
	private String taskName;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "TASK_STATUS")
	private int taskStatus;

	@Column(name = "PARENT_PROCESS_ID")
	private String parentPid;

	@Column(name = "TASK_TYPE")
	private int taskType;

	@Column(name = "DESCRIPTION")
	private String description;

	public ProcessTaskDomain() {
		
	}

	public String toString() {
		StringBuffer strbuffer = new StringBuffer();
		strbuffer.append("task_id -" + this.taskId).append("Parent Pid = " + this.parentPid)
				.append("Task Type = " + this.taskType).append("Description -" + this.description)
				.append("Task Status" + this.taskStatus).append("Task Name" + this.taskName);
		return strbuffer.toString();
	}

}
