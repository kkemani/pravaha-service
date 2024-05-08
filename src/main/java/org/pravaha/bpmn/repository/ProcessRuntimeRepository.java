package org.pravaha.bpmn.repository;

import org.pravaha.bpmn.domain.ProcessRuntimeDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRuntimeRepository extends JpaRepository<ProcessRuntimeDomain, String>{

	public ProcessRuntimeDomain findByProcessId(String id);
}
