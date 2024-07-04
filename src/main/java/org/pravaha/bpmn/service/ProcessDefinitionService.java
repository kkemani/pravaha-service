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

	public ProcessDefinitionVO getProcessDef(String processName, String processVersion) {
		ProcessDefinitionDomain domain = definitionRepository.findByProcessNameAndProcessVersion(processName,
				processVersion);
		if (domain != null) {
			return convertDomaintoVO(domain);
		} else
			return null;
	}

	public ProcessDefinitionVO saveProcessDef(ProcessDefinitionVO processDef) {
		String processName = processDef.getProcessName();
		String processVersion = processDef.getProcessVersion();
		ProcessDefinitionDomain domain = definitionRepository.findByProcessNameAndProcessVersion(processName,
				processVersion);
		if (domain == null && processDef != null) {
			domain = convertVOToDomain(processDef);
			domain = definitionRepository.save(domain);
			return convertDomaintoVO(domain);
		} else
			System.out.println("Domain is not null for process def with ProcessName: " + processName
					+ " and processVersion : " + processVersion);
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
