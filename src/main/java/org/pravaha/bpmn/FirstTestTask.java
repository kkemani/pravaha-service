package org.pravaha.bpmn;

import org.pravaha.bpmn.engine.BpmnException;
import org.pravaha.bpmn.engine.BpmnServiceTask;
import org.pravaha.bpmn.engine.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.stipl.bpmn.engine.BpmnException;
//import org.stipl.bpmn.engine.BpmnServiceTask;
//import org.stipl.bpmn.engine.DelegateExecution;

public class FirstTestTask extends BpmnServiceTask{
	public FirstTestTask(int type, String taskId, String name) {
		super(type, taskId, name);
		// TODO Auto-generated constructor stub
	}


	final static Logger logger = LoggerFactory.getLogger("FirstTestTask");



	@Override
	public void execute(DelegateExecution delExec)throws BpmnException {
		logger.debug(" In service Execution task - DE="+ delExec.toString());
		logger.debug(" Out of service Execution task ");

	}
	
	
}
