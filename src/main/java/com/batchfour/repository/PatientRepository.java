package com.batchfour.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.batchfour.model.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer>{   
    String findByPatientNric(String patientNric);
    
    public List<Patient> findAllByWardStatus(String status);
}
