package org.pravaha.bpmn.controller;

import java.util.List;

import org.pravaha.bpmn.model.ProcessEventWatchVO;
import org.pravaha.bpmn.service.ProcessEventWatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/processeventwatch")
public class ProcessEventWatchController {

	@Autowired
	public ProcessEventWatchService eventWatchService;

	@PostMapping("/saveProcEvent")
	public ProcessEventWatchVO saveProcessEventWatch(@RequestBody ProcessEventWatchVO eventWatchVO) {
		eventWatchVO = eventWatchService.saveProcessEventWatch(eventWatchVO);
		return eventWatchVO;
	}

	@GetMapping(value = "/getProcEveByCorrId/{correlationId}")
	public List<ProcessEventWatchVO> getProcessEventWatchByCorrelation(
			@PathVariable("correlationId") String correlationId) {
		return eventWatchService.getEventWatchByCorrelationId(correlationId);
	}

	@GetMapping(value = "/getProcEveByCorrAndRelatedId/{correlationId}/{relatedId}")
	public ProcessEventWatchVO getProcEveByCorrelationAndRelatedId(
			@PathVariable("correlationId") String correlationId, @PathVariable("relatedId") String relatedId) {
		return eventWatchService.getEventWatchByCorrelationIdAndRelatedId(correlationId,relatedId);
	}

	@GetMapping(value = { "/getProcEveByProcId/{processId}" })
	public List<ProcessEventWatchVO> getProcessEventWatch(@PathVariable("processId") String processId) {
		try {
			return eventWatchService.getEventWatch(processId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping(value = { "/delProcEveById/{id}" })
	public Long delProcessEventWatch(@PathVariable("id") Long id) {
		try {
			return eventWatchService.deleteEventWatch(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
