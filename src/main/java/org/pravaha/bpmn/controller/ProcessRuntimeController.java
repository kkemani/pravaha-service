package org.pravaha.bpmn.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.pravaha.bpmn.model.ProcessRuntimeSearchVO;
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
		return prRunTimeVo;
	}

	@GetMapping(value = "/get/{processId}")
	public ProcessRuntimeVO getByProcessId(@PathVariable("processId") String processId) {
		ProcessRuntimeVO prRunTimeVo = processRunTimeService.getProcessRuntime(processId);

		return prRunTimeVo;
	}
	
	@GetMapping(value = "/getByBusinessKey/{businesskey}")
	public List<ProcessRuntimeVO> getByBusinessKey(@PathVariable("businesskey") String businesskey) {
		List<ProcessRuntimeVO> prRunTimeVo = processRunTimeService.getPrRuntimeByBusinessKey(businesskey);

		return prRunTimeVo;
	}

	@PostMapping(value = "/getByStartDateBetween")
	public List<ProcessRuntimeVO> getByStartDateBetween(@RequestBody ProcessRuntimeSearchVO processRuntimeSearchVO) {
		List<ProcessRuntimeVO> prRunTimeVOList = processRunTimeService.getProcessRuntimeByDateBetween(processRuntimeSearchVO.getStartDate(), processRuntimeSearchVO.getEndDate());

		return prRunTimeVOList;
	}
	
	@GetMapping(value = "/getTodaysStatus")
	public List<Map<String,Object>> getTodaysStatus() {
		try {
			List<Map<String,Object>> voList = processRunTimeService.todaysRecord();
			if(voList!=null)
				return voList;
			else
				return null;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


}
