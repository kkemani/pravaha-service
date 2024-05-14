package org.pravaha.bpmn.service;

import org.modelmapper.ModelMapper;
import org.pravaha.bpmn.domain.ProcessDetailsDomain;
import org.pravaha.bpmn.model.ProcessDetailsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessDetailsService {
	
	@Autowired
	public ProcessRunTimeService processRunTimeService;
	
	@Autowired
	public ProcessContextService contextService;
	
	@Autowired
	public ProcessTaskService processTaskService;
	
	@Autowired
	public ProcessEventWatchService eventWatchService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ProcessDetailsVO getProcessDetails(String processId) {
		ProcessDetailsVO obj = new ProcessDetailsVO();
		obj.setProcessId(processId);
		obj.setProcessRuntimeVO(processRunTimeService.getProcessRuntime(processId));
		obj.setProcessContextVO(contextService.getProcessContext(processId));
		obj.setProcessEventWatchVO(eventWatchService.getEventWatch(processId));
		obj.setProcessTaskVO(processTaskService.getTaskByProcessId(processId));
		if(obj!=null)
			return obj;
		else
			System.out.println("ProcessDetailsDomain Object is null...");
		return null;
		
	}
	
	

}
