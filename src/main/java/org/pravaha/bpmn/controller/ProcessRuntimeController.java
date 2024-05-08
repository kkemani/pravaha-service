package org.pravaha.bpmn.controller;

import org.pravaha.bpmn.model.ProcessRuntimeVO;
import org.pravaha.bpmn.service.ProcessRunTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/processruntime")
public class ProcessRuntimeController {
	
	@Autowired
	ProcessRunTimeService processRunTimeService;
	
	@PostMapping("/savePRRunTime")
	public ProcessRuntimeVO saveProcessRuntime(@RequestBody ProcessRuntimeVO prRunTimeVo) {
		prRunTimeVo = processRunTimeService.saveProcessRuntime(prRunTimeVo);
		System.out.println("In ProcessRuntimeController.........."+ prRunTimeVo);
		
		return prRunTimeVo;
	}

	
	@GetMapping(value = "/get/{processId}")
	public ProcessRuntimeVO getByProcessId(@PathVariable("processId") String processId) {
		ProcessRuntimeVO prRunTimeVo = processRunTimeService.getProcessRuntime(processId);
		System.out.println("In ProcessRuntimeController.........."+ prRunTimeVo);
		
		return prRunTimeVo;
	}


}
