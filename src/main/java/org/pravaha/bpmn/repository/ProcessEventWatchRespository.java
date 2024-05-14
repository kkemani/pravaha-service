package org.pravaha.bpmn.repository;

import java.util.List;

import org.pravaha.bpmn.domain.ProcessEventWatchDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessEventWatchRespository extends JpaRepository<ProcessEventWatchDomain, Long>{
	
	public List<ProcessEventWatchDomain> findByProcessId(String processId);

}
