package org.pravaha.bpmn.repository;

import org.pravaha.bpmn.domain.ProcessTaskDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessTaskRepository extends JpaRepository<ProcessTaskDomain, Long>{
	
	public ProcessTaskDomain findByProcessIdAndTaskId(String processId, long taskId);

}
