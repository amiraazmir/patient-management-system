package com.batchfour.repository;

import org.springframework.data.repository.CrudRepository;

import com.batchfour.model.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Integer> {
    
//public Boolean existsbymobileNo(int mobileNo);
    public Doctor findByMobileNo(int mobileNo);
}
