package org.pravaha.bpmn.service;

import java.util.Calendar;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.pravaha.bpmn.dataaccess.ProcessRuntimeDao;
import org.pravaha.bpmn.domain.ProcessRuntimeDomain;
import org.pravaha.bpmn.model.ProcessRuntimeVO;
import org.pravaha.bpmn.repository.ProcessRuntimeRepository;
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
//		if(obj.getStartDate()==null)
//			obj.setStartDate(Calendar.getInstance());
		if(obj.getLastUpdateDate()==null)
			obj.setLastUpdateDate(Calendar.getInstance());
		System.out.println("In ProcessRunTimeService:Obj-----> "+obj);
		obj = processRuntimeRepository.save(obj);
		return convertDomaintoVO(obj);
	}
	

	public ProcessRuntimeVO getProcessRuntime(String processId) {
		ProcessRuntimeDomain obj = processRuntimeRepository.findByProcessId(processId);
		System.out.println("============>>>> start date is "+obj.getStartDate());
		if(obj!=null)
			return convertDomaintoVO(obj);
		
		return null;
	}


    public ProcessRuntimeDomain convertVOtoDomain(Object vo) {
    	System.out.println("In convertVOtoDomain....."+vo.toString());
        ProcessRuntimeDomain pd =  modelMapper.map(vo, ProcessRuntimeDomain.class);
       	pd.setProcessId(UUID.randomUUID().toString());
        System.out.println("In convertVOtoDomain....."+pd);
       	return pd;
        
    }
    

    public ProcessRuntimeVO convertDomaintoVO(Object domain) {
        ProcessRuntimeVO pdvo =  modelMapper.map(domain, ProcessRuntimeVO.class);
        System.out.println("In convertVOtoDomain....."+pdvo);
       	return pdvo;
    }
}
