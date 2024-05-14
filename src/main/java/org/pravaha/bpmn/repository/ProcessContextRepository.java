package org.pravaha.bpmn.repository;



import org.pravaha.bpmn.domain.ProcessContextDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessContextRepository extends JpaRepository<ProcessContextDomain, String>{
	
	public ProcessContextDomain findByProcessId(String processId);

}
