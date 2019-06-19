package com.batchfour.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.batchfour.model.Appointment;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer>{

    public List<Appointment> findAllByPatientPatientID (int patientID);
    public List<Appointment> findAllByDoctorDoctorID(int doctorID);
    
}
