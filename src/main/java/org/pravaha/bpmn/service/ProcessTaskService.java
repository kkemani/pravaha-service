package org.pravaha.bpmn.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.pravaha.bpmn.domain.ProcessContextDomain;
import org.pravaha.bpmn.domain.ProcessRuntimeDomain;
import org.pravaha.bpmn.domain.ProcessTaskDomain;
import org.pravaha.bpmn.model.ProcessContextVO;
import org.pravaha.bpmn.model.ProcessRuntimeVO;
import org.pravaha.bpmn.model.ProcessTaskVO;
import org.pravaha.bpmn.repository.ProcessTaskRepository;
import org.pravaha.bpmn.util.LocalDateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessTaskService {

	@Autowired
	ProcessTaskRepository processTaskRepository;
	
	@Autowired
	private ModelMapper modelMapper;


	public ProcessTaskVO getTask(String processId, Long taskId) {
		ProcessTaskDomain obj = processTaskRepository.findByProcessIdAndTaskId(processId, taskId);
		if ( obj!= null)
			return convertDomaintoVO(obj);
		return null;
	}

	public ProcessTaskVO updateTaskStatus(String processId, Long taskId, int taskStatus) throws java.text.ParseException{
		ProcessTaskDomain obj = processTaskRepository.findByProcessIdAndTaskId(processId, taskId);
		if (obj != null) {
			if ((taskStatus == 2 || taskStatus == 3)) {
				Date date = LocalDateTimeUtil.getTodaysDate();
				obj.setEndDate(date);
			}
			obj.setTaskStatus(taskStatus);
			obj = processTaskRepository.save(obj);
			return convertDomaintoVO(obj);
		}

		return null;
	}
	
	public List<ProcessTaskVO> getTaskByProcessId(String processId) {
		System.out.println("getTaskByProcessId service ------");
		return convertListDomaintoListVO(processTaskRepository.findByProcessId(processId));
		
	}
	public ProcessTaskVO convertDomaintoVO(Object domain) {
		ProcessTaskVO pdvo = modelMapper.map(domain, ProcessTaskVO.class);
		return pdvo;
	}
	
	public List<ProcessTaskVO> convertListDomaintoListVO(List<ProcessTaskDomain> domainList) {
		List<ProcessTaskVO> voList = new ArrayList<ProcessTaskVO>();
		for (ProcessTaskDomain oneDomain : domainList) {
			voList.add(convertDomaintoVO(oneDomain));

		}
		if (!voList.isEmpty())
			return voList;

		return null;
	}
	
	public ProcessTaskVO convertDomaintoVO(ProcessTaskDomain domain) {
		ProcessTaskVO pdvo = modelMapper.map(domain, ProcessTaskVO.class);
		return pdvo;
	}
	
//	public Long saveProcessTask(ProcessTaskVO processTaskVo) {
//		ProcessTaskDomain obj = convertVOtoDomain(processTaskVo);
//		if (obj.getStartDate() == null)
//			obj.setStartDate(Calendar.getInstance().getTime());
//		obj = processTaskRepository.save(obj);
//		return new Long(1);
//	}
}
