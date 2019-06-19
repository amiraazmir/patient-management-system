package com.batchfour.repository;

import org.springframework.data.repository.CrudRepository;

import com.batchfour.model.Medicine;

public interface MedicineRepository extends CrudRepository<Medicine, Integer> {
    public Medicine findByMedicineName(String medicineName);
}
