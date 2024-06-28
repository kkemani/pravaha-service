package org.pravaha.bpmn.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.pravaha.bpmn.domain.ProcessEventWatchDomain;
import org.pravaha.bpmn.domain.ProcessRuntimeDomain;
import org.pravaha.bpmn.domain.ProcessTaskDomain;
import org.pravaha.bpmn.model.ProcessEventWatchVO;
import org.pravaha.bpmn.model.ProcessTaskVO;
import org.pravaha.bpmn.repository.ProcessEventWatchRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessEventWatchService {

	@Autowired
	public ProcessEventWatchRespository eventWatchRespository;

	@Autowired
	private ModelMapper modelMapper;

	// Save ProcessEventWatch
	public ProcessEventWatchVO saveProcessEventWatch(ProcessEventWatchVO eventWatchVO) {
		ProcessEventWatchDomain obj = null;
		obj = convertVOtoDomain(eventWatchVO);
		if (obj.getCreateDate() == null)
			obj.setCreateDate(Calendar.getInstance().getTime());

		eventWatchRespository.save(obj);
		return convertDomaintoVO(obj);
	}

	// getVOByEventTypeAndCorrelationId
	public ProcessEventWatchVO getEventByEventTypeAndCorrId(String eventType, String correlationId) {
		ProcessEventWatchDomain domain = eventWatchRespository.findByEventTypeAndCorrelationId(eventType,
				correlationId);
		if (domain != null) {
			eventWatchRespository.delete(domain);
			return convertDomaintoVO(domain);
		} else
			return null;
	}

	// Get ProcessEventWatchVO List By Process Id
	public List<ProcessEventWatchVO> getEventWatch(String processId) {

		return convertListDomaintoListVO(eventWatchRespository.findByProcessId(processId));
	}

	// Get ProcessEventWatchVO list by correlationId
	public List<ProcessEventWatchVO> getEventWatchByCorrelationId(String correlationId) {
		return convertListDomaintoListVO(eventWatchRespository.findByCorrelationId(correlationId));
	}

	// Get ProcessEventWatchVO by correlationId and relatedId
	public ProcessEventWatchVO getEventWatchByCorrelationIdAndRelatedId(String correlationId, String relatedId) {
		return convertDomaintoVO(eventWatchRespository.findByCorrelationIdAndRelatedId(correlationId, relatedId));
	}

	// delete Process Event Watch By Id
	public Long deleteEventWatch(Long id) {
		Optional<ProcessEventWatchDomain> obj = eventWatchRespository.findById(id);
		if (obj != null) {
			eventWatchRespository.delete(obj.get());
			return id;
		} else
			return null;
	}

	public ProcessEventWatchDomain convertVOtoDomain(ProcessEventWatchVO vo) {
		ProcessEventWatchDomain pd = modelMapper.map(vo, ProcessEventWatchDomain.class);
		long id = (long) UUID.randomUUID().getLeastSignificantBits();
		pd.setId(id);
		return pd;

	}

	public List<ProcessEventWatchVO> convertListDomaintoListVO(List<ProcessEventWatchDomain> domainList) {
		List<ProcessEventWatchVO> voList = new ArrayList<ProcessEventWatchVO>();
		for (ProcessEventWatchDomain oneDomain : domainList) {
			voList.add(convertDomaintoVO(oneDomain));

		}
		if (!voList.isEmpty())
			return voList;

		return null;
	}

	public ProcessEventWatchVO convertDomaintoVO(ProcessEventWatchDomain domain) {
		ProcessEventWatchVO pdvo = modelMapper.map(domain, ProcessEventWatchVO.class);
		return pdvo;
	}

}
