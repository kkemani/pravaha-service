package org.pravaha.bpmn.controller;

import org.pravaha.bpmn.model.BpmnProcessStartVO;
import org.pravaha.bpmn.service.BpmnProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bpmnprocess")
public class BpmnProcessController {
	
	@Autowired
	BpmnProcessService bpmnProcessService;

	@PostMapping("/startbpmnprocess")
	public void startBpmnProcess(@RequestBody BpmnProcessStartVO processStartVO) {
		bpmnProcessService.startProcess(processStartVO);
	}
	
	@GetMapping("/processevent/{eventType}/{correlationId}")
	public void processEvent(@PathVariable("eventType") String eventType,@PathVariable("correlationId") String correlationId) {
		bpmnProcessService.resumeProcess(eventType,correlationId);
	}
}
