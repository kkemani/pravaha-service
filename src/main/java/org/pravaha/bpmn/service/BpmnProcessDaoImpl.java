package org.pravaha.bpmn.service;

import java.text.ParseException;
import org.pravaha.bpmn.dataaccess.BpmnProcessDao;
import org.pravaha.bpmn.model.ProcessContextVO;
import org.pravaha.bpmn.model.ProcessDefinitionVO;
import org.pravaha.bpmn.model.ProcessEventWatchVO;
import org.pravaha.bpmn.model.ProcessRuntimeVO;
import org.pravaha.bpmn.model.ProcessTaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BpmnProcessDaoImpl implements BpmnProcessDao {

	@Autowired
	ProcessRunTimeService processRunTimeService;
	
	@Autowired
	ProcessEventWatchService eventWatchService;
	
	@Autowired
	ProcessTaskService processTaskService;
	
	@Autowired
	ProcessContextService contextservice;
	
	@Autowired
	ProcessDefinitionService definitionService;

	@Override
	public ProcessRuntimeVO saveProcessRuntime(ProcessRuntimeVO processRuntimeVO) {
		// TODO Auto-generated method stub
		return processRunTimeService.saveProcessRuntime(processRuntimeVO);

	}

	@Override
	public ProcessEventWatchVO saveProcessEventWatch(ProcessEventWatchVO processEventWatchVO) {
		// TODO Auto-generated method stub
		return eventWatchService.saveProcessEventWatch(processEventWatchVO);

	}
	
	@Override
	public void deleteEventWatch(Long id) {
		// TODO Auto-generated method stub
		eventWatchService.deleteEventWatch(id);

	}

	@Override
	public ProcessTaskVO updateTaskStatus(String processId, Long taskId, int taskStatus) {
		// TODO Auto-generated method stub
		ProcessTaskVO processTaskVo = new ProcessTaskVO();
		try {
			processTaskVo = processTaskService.updateTaskStatus(processId, taskId, taskStatus);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return processTaskVo;
	}

	@Override
	public void saveProcessTask(ProcessTaskVO processTaskVO) {
		// TODO Auto-generated method stub
		processTaskService.saveProcessTask(processTaskVO);
		
	}

	@Override
	public ProcessRuntimeVO getProcessRunTime(String processId) {
		// TODO Auto-generated method stub
		return processRunTimeService.getProcessRuntime(processId);
	}

	@Override
	public ProcessContextVO saveProcessContext(ProcessContextVO vo) {
		// TODO Auto-generated method stub
		contextservice.saveProcessContext(vo);
		return null;
	}

	@Override
	public ProcessDefinitionVO saveProcessDefintion(ProcessDefinitionVO definitionVO) {
		// TODO Auto-generated method stub
		return definitionService.saveProcessDef(definitionVO);
	}

	@Override
	public ProcessDefinitionVO getProcessDefinition(String processName) {
		// TODO Auto-generated method stub
		return definitionService.getProcessDef(processName);
	}

}
