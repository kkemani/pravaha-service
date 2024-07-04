package org.pravaha.bpmn.repository;


import org.pravaha.bpmn.domain.ProcessDefinitionDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessDefinitionRepository extends JpaRepository<ProcessDefinitionDomain, String>{
	
	public ProcessDefinitionDomain findByProcessNameAndProcessVersion(String processName, String processVersion);

}
