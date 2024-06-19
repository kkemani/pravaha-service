package org.pravaha.bpmn.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.pravaha.bpmn.domain.ProcessDefinitionDomain;
import org.pravaha.bpmn.domain.ProcessEventWatchDomain;
import org.pravaha.bpmn.model.ProcessDefinitionVO;
import org.pravaha.bpmn.model.ProcessEventWatchVO;
import org.pravaha.bpmn.repository.ProcessDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessDefinitionService {
	
	@Autowired
	ProcessDefinitionRepository definitionRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public ProcessDefinitionVO getProcessDef(String processName) {
		ProcessDefinitionDomain domain = definitionRepository.findByProcessName(processName);
		if(domain!=null) {
			return convertDomaintoVO(domain);
		}
		else
			return null;
	}
	
	public ProcessDefinitionVO saveProcessDef(ProcessDefinitionVO processDef) {
		ProcessDefinitionDomain domain = convertVOToDomain(processDef);
		if(domain!=null) {
			domain = definitionRepository.save(domain);
			return convertDomaintoVO(domain);
		} 
		else
			return null;
	}
	
	public ProcessDefinitionVO convertDomaintoVO(ProcessDefinitionDomain domain) {
		ProcessDefinitionVO processvo = modelMapper.map(domain, ProcessDefinitionVO.class);
		return processvo;
	}
	
	public ProcessDefinitionDomain convertVOToDomain(ProcessDefinitionVO vo) {
		ProcessDefinitionDomain processdomain = modelMapper.map(vo, ProcessDefinitionDomain.class);
		return processdomain;
	}
	
	
}
