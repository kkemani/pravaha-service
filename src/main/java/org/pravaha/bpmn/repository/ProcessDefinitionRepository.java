package org.pravaha.bpmn.repository;

import java.util.Optional;

import org.pravaha.bpmn.domain.ProcessDefinitionDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessDefinitionRepository extends JpaRepository<ProcessDefinitionDomain, String>{
	
	public ProcessDefinitionDomain findByProcessName(String processName);

}
