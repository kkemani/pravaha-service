package org.pravaha.bpmn.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.pravaha.bpmn.domain.ProcessRuntimeDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessRuntimeRepository extends JpaRepository<ProcessRuntimeDomain, String>{

	public ProcessRuntimeDomain findByProcessId(String id);
	public List<ProcessRuntimeDomain> findByBusinessKey(String businessKey);
	public List<ProcessRuntimeDomain> findByStartDateBetween(Date startDate, Date endDate);

	
	@Query(value = "SELECT status, COUNT(*) as COUNT FROM TBL_PROCESS_RUNTIME WHERE TRUNC(START_DATE) = TRUNC(:today) GROUP BY status", nativeQuery = true)
	public List<Map<String,Object>> getStatusCountForToday(Date today);
	
}
