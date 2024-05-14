package org.pravaha.bpmn.service;


import org.modelmapper.ModelMapper;
import org.pravaha.bpmn.domain.ProcessContextDomain;
import org.pravaha.bpmn.model.ProcessContextVO;
import org.pravaha.bpmn.repository.ProcessContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessContextService {
	
	@Autowired
	ProcessContextRepository contextRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	public ProcessContextVO getProcessContext(String id) {
		
		return convertDomaintoVO(contextRepository.findByProcessId(id));
	}
	
	public ProcessContextVO convertDomaintoVO(ProcessContextDomain domain) {
		ProcessContextVO pdvo = modelMapper.map(domain, ProcessContextVO.class);
		return pdvo;
	}

}
