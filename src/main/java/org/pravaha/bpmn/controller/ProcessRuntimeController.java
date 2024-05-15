package org.pravaha.bpmn.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.pravaha.bpmn.domain.ProcessDetailsDomain;
import org.pravaha.bpmn.domain.ProcessTaskDomain;
import org.pravaha.bpmn.model.ProcessContextVO;
import org.pravaha.bpmn.model.ProcessDetailsVO;
import org.pravaha.bpmn.model.ProcessEventWatchVO;
import org.pravaha.bpmn.model.ProcessRuntimeSearchVO;
import org.pravaha.bpmn.model.ProcessRuntimeVO;
import org.pravaha.bpmn.model.ProcessTaskVO;
import org.pravaha.bpmn.service.ProcessContextService;
import org.pravaha.bpmn.service.ProcessDetailsService;
import org.pravaha.bpmn.service.ProcessEventWatchService;
import org.pravaha.bpmn.service.ProcessRunTimeService;
import org.pravaha.bpmn.service.ProcessTaskService;
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

	@Autowired
	ProcessTaskService processTaskService;

	@Autowired
	ProcessContextService contextService;

	@Autowired
	public ProcessDetailsService detailsService;

	@Autowired
	public ProcessEventWatchService eventWatchService;

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
		List<ProcessRuntimeVO> prRunTimeVOList = processRunTimeService.getProcessRuntimeByDateBetween(
				processRuntimeSearchVO.getStartDate(), processRuntimeSearchVO.getEndDate());

		return prRunTimeVOList;
	}

	@GetMapping(value = "/getTodaysStatus")
	public Map<String, Integer> getTodaysStatus() {
		try {
			Map<String, Integer> map = processRunTimeService.todaysRecord();
			return map;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping(value = "/getTodaysRecordByStatus")
	public List<ProcessRuntimeVO> getTodaysRecordByStatus(@RequestBody ProcessRuntimeSearchVO processRuntimeSearchVO) {
		return processRunTimeService.todaysRecordListByStatus(processRuntimeSearchVO.getStartDate(),
				processRuntimeSearchVO.getEndDate(), processRuntimeSearchVO.getStatus());
	}

	@GetMapping(value = "/getTask/{processId}/{taskId}")
	public ProcessTaskVO getTask(@PathVariable("processId") String processId, @PathVariable("taskId") Long taskId) {
		return processTaskService.getTask(processId, taskId);
	}

	@GetMapping(value = "updateTaskstatus/{processId}/{taskId}/{taskStatus}")
	public ProcessTaskVO updateProcessTask(@PathVariable("processId") String processId,
			@PathVariable("taskId") Long taskId, @PathVariable("taskStatus") int taskStatus) {
		try {
			ProcessTaskVO obj = null;
			obj = processTaskService.updateTaskStatus(processId, taskId, taskStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@GetMapping(value = { "/getProcessDetails/{processId}" })
	public ProcessDetailsVO getProcessDetails(@PathVariable("processId") String processId) {
		try {
			return detailsService.getProcessDetails(processId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping(value = { "/getProcessContext/{processId}" })
	public ProcessContextVO getProcessCOntext(@PathVariable("processId") String processId) {
		try {
			return contextService.getProcessContext(processId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping(value = { "/getProcessTask/{processId}" })
	public List<ProcessTaskVO> getProcessTask(@PathVariable("processId") String processId) {
		try {
			return processTaskService.getTaskByProcessId(processId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	@PostMapping(value = "/saveProcessTask")
//	public Long saveProcessTask(@RequestBody ProcessTaskVO obj) {
//
//		return processTaskService.saveProcessTask(obj);
//	}

}
