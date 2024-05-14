package org.pravaha.bpmn.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.pravaha.bpmn.domain.ProcessEventWatchDomain;
import org.pravaha.bpmn.domain.ProcessTaskDomain;
import org.pravaha.bpmn.model.ProcessEventWatchVO;
import org.pravaha.bpmn.model.ProcessTaskVO;
import org.pravaha.bpmn.repository.ProcessEventWatchRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessEventWatchService {
	
	@Autowired
	public ProcessEventWatchRespository eventWatchRespository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<ProcessEventWatchVO> getEventWatch(String processId) {
		
		return convertListDomaintoListVO(eventWatchRespository.findByProcessId(processId));
	}
	
	public List<ProcessEventWatchVO> convertListDomaintoListVO(List<ProcessEventWatchDomain> domainList) {
		List<ProcessEventWatchVO> voList = new ArrayList<ProcessEventWatchVO>();
		for (ProcessEventWatchDomain oneDomain : domainList) {
			voList.add(convertDomaintoVO(oneDomain));

		}
		if (!voList.isEmpty())
			return voList;

		return null;
	}
	
	public ProcessEventWatchVO convertDomaintoVO(ProcessEventWatchDomain domain) {
		ProcessEventWatchVO pdvo = modelMapper.map(domain, ProcessEventWatchVO.class);
		return pdvo;
	}


}
