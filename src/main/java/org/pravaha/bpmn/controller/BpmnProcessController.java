package org.pravaha.bpmn.controller;

import org.pravaha.bpmn.model.BpmnProcessStartVO;
import org.pravaha.bpmn.service.BpmnProcessService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
