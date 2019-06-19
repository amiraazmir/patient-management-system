package com.batchfour.repository;


import org.springframework.data.repository.CrudRepository;

import com.batchfour.model.Prescription;

public interface PrescriptionRepository extends CrudRepository<Prescription, Integer>{


}
