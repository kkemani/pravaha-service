package org.pravaha.bpmn.controller;

import org.pravaha.bpmn.model.ProcessDefinitionVO;
import org.pravaha.bpmn.service.ProcessDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/processDefinition")
public class ProcessDefinitionController {
	
	@Autowired
	ProcessDefinitionService definitionService;
	
	@GetMapping(value = "/getProcessDef/{processName}/{processversion}")
	public ProcessDefinitionVO getProcessDef(@PathVariable("processName") String processName, @PathVariable("processversion") String processversion) {
		ProcessDefinitionVO vo = definitionService.getProcessDef(processName,processversion);
		return vo!=null ? vo : null;
	}
	
	@PostMapping(value = "/save")
	public ProcessDefinitionVO saveProcessDef(@RequestBody ProcessDefinitionVO vo) {
		return definitionService.saveProcessDef(vo);
	}

}
