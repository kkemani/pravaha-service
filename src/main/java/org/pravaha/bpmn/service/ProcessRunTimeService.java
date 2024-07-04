package org.pravaha.bpmn.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.pravaha.bpmn.dataaccess.ProcessRuntimeDao;
import org.pravaha.bpmn.defines.ProcessRunTimeEnum;
import org.pravaha.bpmn.domain.ProcessRuntimeDomain;
import org.pravaha.bpmn.model.ProcessRuntimeVO;
import org.pravaha.bpmn.repository.ProcessRuntimeRepository;
import org.pravaha.bpmn.util.LocalDateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessRunTimeService extends ProcessRuntimeDao {

	@Autowired
	ProcessRuntimeRepository processRuntimeRepository;

	@Autowired
	private ModelMapper modelMapper;

	public ProcessRuntimeVO saveProcessRuntime(ProcessRuntimeVO processRuntimeVO) {
		ProcessRuntimeDomain obj = null;
		// convert from PRVO to PRRuntimeDomain
		obj = convertVOtoDomain(processRuntimeVO);
		if (obj.getStartDate() == null)
			obj.setStartDate(Calendar.getInstance().getTime());
		if (obj.getLastUpdateDate() == null)
			obj.setLastUpdateDate(Calendar.getInstance().getTime());
		obj = processRuntimeRepository.save(obj);
		return convertDomaintoVO(obj);
	}

	public ProcessRuntimeVO getProcessRuntime(String processId) {
		ProcessRuntimeDomain obj = processRuntimeRepository.findByProcessId(processId);
		if (obj != null)
			return convertDomaintoVO(obj);

		return null;
	}

	public List<ProcessRuntimeVO> getPrRuntimeByBusinessKey(String businessKey) {
		List<ProcessRuntimeDomain> obj = processRuntimeRepository.findByBusinessKey(businessKey);
		if (obj != null)
			return convertListDomaintoListVO(obj);

		return null;
	}

	public List<ProcessRuntimeVO> getProcessRuntimeByDateBetween(Date startDate, Date endDate) {
		List<ProcessRuntimeDomain> procRTList = processRuntimeRepository.findByStartDateBetween(startDate, endDate);
		if (procRTList != null) {
			return convertListDomaintoListVO(procRTList);
		}
		return null;
	}

	public Map<String, Integer> todaysRecord() throws java.text.ParseException {
		Date date = LocalDateTimeUtil.getTodaysDate();
		List<Map<String, Object>> mapList = processRuntimeRepository.getStatusCountForToday(date);
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		int inProgressCount = 0;
		int completedCount = 0;
		int failedCount = 0;
		for (Map<String, Object> oneMap : mapList) {
			String status = oneMap.get(ProcessRunTimeEnum.STATUS.getValue()).toString();
			if (status.equals(ProcessRunTimeEnum.IN_PROGRESS_INT.getValue())) {
				inProgressCount = Integer.parseInt(oneMap.get(ProcessRunTimeEnum.COUNT.getValue()).toString());
				resultMap.put(ProcessRunTimeEnum.IN_PROGRESS_STRING.getValue(), inProgressCount);
			} else if (status.equals(ProcessRunTimeEnum.COMPLETED_INT.getValue())) {
				completedCount = Integer.parseInt(oneMap.get(ProcessRunTimeEnum.COUNT.getValue()).toString());
				resultMap.put(ProcessRunTimeEnum.COMPLETED_STRING.getValue(), completedCount);
			} else if (status.equals(ProcessRunTimeEnum.FAILED_INT.getValue())) {
				failedCount = Integer.parseInt(oneMap.get(ProcessRunTimeEnum.COUNT.getValue()).toString());
				resultMap.put(ProcessRunTimeEnum.FAILED_STRING.getValue(), failedCount);
			}
			int total = inProgressCount + completedCount + failedCount;
			resultMap.put(ProcessRunTimeEnum.TOTAL_EXECUTED_STRING.getValue(), total);
		}
		if (resultMap != null)
			return resultMap;

		return null;
	}

	public List<ProcessRuntimeVO> todaysRecordListByStatus(Date startDate, Date endDate, String status) {
		status = getStringStatusValue(status);
		int statusInt = Integer.parseInt(status);
		List<ProcessRuntimeDomain> domainList = processRuntimeRepository.findByStartDateBetween(startDate, endDate);
		List<ProcessRuntimeDomain> resultList = new ArrayList<ProcessRuntimeDomain>();
		for (ProcessRuntimeDomain oneDomain : domainList) {
			if (statusInt == oneDomain.getStatus()) {
				resultList.add(oneDomain);
			}
		}
		if (resultList.isEmpty() && status.equals(ProcessRunTimeEnum.TOTAL_EXECUTED_INT.getValue()))
			return convertListDomaintoListVO(domainList);

		return convertListDomaintoListVO(resultList);

	}

	private String getStringStatusValue(String status) {
		switch (status.toLowerCase()) {
		case "inprogress":
			return ProcessRunTimeEnum.IN_PROGRESS_INT.getValue();
		case "completed":
			return ProcessRunTimeEnum.COMPLETED_INT.getValue();
		case "failed":
			return ProcessRunTimeEnum.FAILED_INT.getValue();
		case "executed":
			return ProcessRunTimeEnum.TOTAL_EXECUTED_INT.getValue();

		}
		return null;
	}

	public ProcessRuntimeDomain convertVOtoDomain(Object vo) {
		ProcessRuntimeDomain pd = modelMapper.map(vo, ProcessRuntimeDomain.class);
		String processId = UUID.randomUUID().toString();
		if(pd.getProcessId() == null)
			pd.setProcessId(processId);
		String businessKey = UUID.randomUUID().toString();
		pd.setBusinessKey(businessKey);
		return pd;

	}

	public ProcessRuntimeVO convertDomaintoVO(Object domain) {
		ProcessRuntimeVO pdvo = modelMapper.map(domain, ProcessRuntimeVO.class);
		return pdvo;
	}

	public List<ProcessRuntimeVO> convertListDomaintoListVO(List<ProcessRuntimeDomain> domainList) {
		List<ProcessRuntimeVO> voList = new ArrayList<ProcessRuntimeVO>();
		for (ProcessRuntimeDomain oneDomain : domainList) {
			voList.add(convertDomaintoVO(oneDomain));

		}
		if (!voList.isEmpty())
			return voList;

		return null;
	}

}
