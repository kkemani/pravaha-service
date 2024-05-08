package org.pravaha.bpmn.controller;

import org.pravaha.bpmn.domain.ProcessContextDomain;
import org.pravaha.bpmn.service.ProcessContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/processcontext")
public class ProcessContextController {
	
	@Autowired
	ProcessContextService contextService;
	
	@GetMapping(value = "/getById/{id}") 
	public ProcessContextDomain getProcessContextDomain (@PathVariable("id") String id) {
		ProcessContextDomain contextDomain= new ProcessContextDomain();
		System.out.println("In ProcessContextController ..........");
		contextDomain = contextService.findById(id);
		return contextDomain;
	}

}
