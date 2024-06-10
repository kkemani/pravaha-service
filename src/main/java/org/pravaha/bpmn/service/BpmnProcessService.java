package org.pravaha.bpmn.service;

import java.util.Hashtable;
import java.util.List;

import org.pravaha.bpmn.engine.BpmnException;
import org.pravaha.bpmn.engine.BpmnProcessRuntime;
import org.pravaha.bpmn.model.BpmnProcessStartVO;
import org.pravaha.bpmn.model.BpmnProcessVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BpmnProcessService {

	@Autowired
	BpmnProcessDaoImpl bpmnProcessDaoImpl;
	
	public void startProcess(BpmnProcessStartVO processStartVo) {
		System.out.println("File name ::: "+processStartVo.getFileName());
		BpmnProcessRuntime bpmnRun = new BpmnProcessRuntime(processStartVo.getFileName());
		List<BpmnProcessVariable> varList = processStartVo.getProcessVariableList();
		Hashtable<String, Object> map = new Hashtable<String, Object>();
		
		if (varList != null && !varList.isEmpty()) {
		    varList.forEach(x -> map.put(x.getName(), x.getVariable())
		    	);
		}
		
		if (varList != null && !varList.isEmpty()) {
		    for(BpmnProcessVariable oneVariable : varList) {
		    	System.out.println("Name : "+oneVariable.getName());
		    	System.out.println("getVariable : "+oneVariable.getVariable());
		    	map.put(oneVariable.getName(), oneVariable.getVariable());

		    }
		}
		
		bpmnRun.setVariables(map);
		System.out.println("before starting");
		try {
			bpmnRun.setBpmnProcessDao(bpmnProcessDaoImpl);
			bpmnRun.startProcess();
			System.out.println("after starting");
		
		} catch (BpmnException e) {
			e.printStackTrace();
		}
	}
	
}