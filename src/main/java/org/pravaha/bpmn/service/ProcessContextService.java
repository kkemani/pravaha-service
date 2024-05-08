package org.pravaha.bpmn.service;

import java.util.Optional;

import org.pravaha.bpmn.domain.ProcessContextDomain;
import org.pravaha.bpmn.repository.ProcessContextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessContextService {
	
	@Autowired
	ProcessContextRepository contextRepository;

	public ProcessContextDomain findById(String id) {
		Optional<ProcessContextDomain> contextDomain = null;
		contextDomain = contextRepository.findById(id);
		System.out.println("In ProcessContextService  "+contextDomain);
		return contextDomain.get();
	}

}
