package org.pravaha.bpmn.domain;

import java.io.Serializable;
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
	@Column (name="ID")
	private long id;
	
	@Column (name="EVENT_TYPE")
	private String eventType;
	
	@Column(name="CORRELATION_ID")
	private String correlationId;
	
	@Column(name="RELATEDID")
	private String relatedId;
	
	@Column(name="PROCESS_ID")
	private String processId;
	
	@Column(name="CREATE_DATE")
	private Date createDate;
	
	@Column(name="STATUS")
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
