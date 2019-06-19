package com.batchfour.repository;

import org.springframework.data.repository.CrudRepository;
import com.batchfour.model.PatientCondition;

public interface PatientConditionRepository extends CrudRepository<PatientCondition, Integer>{
    
}
