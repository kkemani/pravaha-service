package org.pravaha.bpmn.service;

import org.pravaha.bpmn.domain.ProcessTaskDomain;
import org.pravaha.bpmn.repository.ProcessTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessTaskService {
	
	@Autowired
	ProcessTaskRepository processTaskRepository;
	
	public Long saveProcessTask(ProcessTaskDomain processTaskDomain) {
		ProcessTaskDomain obj = null;
		obj = processTaskRepository.save(processTaskDomain);
		return new Long(1);
	}

}
