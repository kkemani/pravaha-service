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
	@Column(name = "task_id")
	private long taskId;

	@Column(name = "process_id")
	private String processId;

	@Column(name = "Task_name")
	private String taskName;

	@Column(name = "Start_date")
	private Date startDate;

	@Column(name = "End_Date")
	private Date endDate;

	@Column(name = "Task_status")
	private int taskStatus;

	@Column(name = "Parent_process_id")
	private String parentPid;

	@Column(name = "Task_type")
	private int task_type;

	@Column(name = "Description")
	private String description;

	public ProcessTaskDomain() {
		
	}

	public String toString() {
		StringBuffer strbuffer = new StringBuffer();
		strbuffer.append("task_id -" + this.taskId).append("Parent Pid = " + this.parentPid)
				.append("Task Type = " + this.task_type).append("Description -" + this.description)
				.append("Task Status" + this.taskStatus).append("Task Name" + this.taskName);
		return strbuffer.toString();
	}

}
