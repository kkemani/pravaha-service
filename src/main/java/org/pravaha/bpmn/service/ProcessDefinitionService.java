package org.pravaha.bpmn.service;

import org.modelmapper.ModelMapper;
import org.pravaha.bpmn.domain.ProcessDefinitionDomain;
import org.pravaha.bpmn.model.ProcessDefinitionVO;
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
		if (domain != null) {
			return convertDomaintoVO(domain);
		} else
			return null;
	}

	public ProcessDefinitionVO saveProcessDef(ProcessDefinitionVO processDef) {
		System.out.println("ProcessDefinition **************"+processDef.toString());
		String processName = processDef.getProcessName();
		System.out.println("ProcessDefinition Name = ::: " + processName);
		ProcessDefinitionDomain domain = definitionRepository.findByProcessName(processName);
		domain = convertVOToDomain(processDef);
		if (domain != null) {
			domain = definitionRepository.save(domain);
			return convertDomaintoVO(domain);
		} else
			System.out.println("Domain is null for process def after converting to domain");
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
