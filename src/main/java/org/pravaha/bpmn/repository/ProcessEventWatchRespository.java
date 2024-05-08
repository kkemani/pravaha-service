package org.pravaha.bpmn.repository;

import org.pravaha.bpmn.domain.ProcessEventWatchDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessEventWatchRespository extends JpaRepository<ProcessEventWatchDomain, Long>{

}
