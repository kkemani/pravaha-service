package org.pravaha.bpmn.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.text.*;

import org.modelmapper.ModelMapper;
import org.pravaha.bpmn.dataaccess.ProcessRuntimeDao;
import org.pravaha.bpmn.domain.ProcessRuntimeDomain;
import org.pravaha.bpmn.model.ProcessRuntimeVO;
import org.pravaha.bpmn.repository.ProcessRuntimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
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
		System.out.println("Start date : "+startDate);
		System.out.println("End date : "+endDate);
		
		List<ProcessRuntimeDomain> procRTList = processRuntimeRepository.findByStartDateBetween(startDate, endDate);
		for(ProcessRuntimeDomain poc: procRTList)
			System.out.println("Domain Object : -----> "+poc);
		if (procRTList != null) {
			return convertListDomaintoListVO(procRTList);
		}
		return null;
	}

	public List<Map<String,Object>> todaysRecord() throws java.text.ParseException {
		Date date = getTodaysDate();
		List<Map<String,Object>> mapList = processRuntimeRepository.getStatusCountForToday(date);
		
		return mapList;
	}


	public ProcessRuntimeDomain convertVOtoDomain(Object vo) {
		ProcessRuntimeDomain pd = modelMapper.map(vo, ProcessRuntimeDomain.class);
		pd.setProcessId(UUID.randomUUID().toString());
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
	
	public List<ProcessRuntimeVO> convertListDomaintoListVO1(List<Object> domainList) {
		List<ProcessRuntimeVO> voList = new ArrayList<ProcessRuntimeVO>();
		for (Object oneDomain : domainList) {
			voList.add(convertDomaintoVO(oneDomain));

		}
		if (!voList.isEmpty())
			return voList;

		return null;
	}
	
	public Date getTodaysDate() throws java.text.ParseException {
	    Date todaysDate = Calendar.getInstance().getTime();
	    DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	    String outputDateStr = outputFormat.format(todaysDate); // Format the date to string
	    Date date = null;
	    try {
	        date = outputFormat.parse(outputDateStr); // Parse the formatted date string
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return date;
	}

}
