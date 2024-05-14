package org.pravaha.bpmn.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="Tbl_process_event_watch")
@Data
public class ProcessEventWatchDomain implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column (name="Id")
	private long id;
	
	@Column (name="Event_type")
	private String eventType;
	
	@Column(name="Correlation_id")
	private String correlationId;
	
	@Column(name="RELATEDID")
	private String relatedId;
	
	@Column(name="Process_id")
	private String processId;
	
	@Column(name="Create_date")
	private Date createDate;
	
	@Column(name="status")
	private int status;
	
	public ProcessEventWatchDomain() {
		
	}
	
	public String toString()
	{
		StringBuffer strBuffer= new StringBuffer();
		strBuffer.append(this.id +" || " +this.processId +" => "+" || "+this.correlationId+"||"+this.eventType+" || "+this.relatedId+"||"+this.status);
		return strBuffer.toString();
	}
}
