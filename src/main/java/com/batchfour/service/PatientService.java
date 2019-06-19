package com.batchfour.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.batchfour.model.Appointment;
import com.batchfour.model.Patient;
import com.batchfour.model.PatientCondition;
import com.batchfour.repository.AppointmentRepository;
import com.batchfour.repository.PatientConditionRepository;
import com.batchfour.repository.PatientRepository;

@Service

public class PatientService {
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired 
    private AppointmentRepository aptRepo; 
    
    @Autowired 
    private PatientConditionRepository patientConditionRepo;
    

    public Patient findByPatientID(int patientID) {
        return patientRepository.findOne(patientID);
    }

    public Patient save(Patient patient){
        return patientRepository.save(patient);
    }
    
    public PatientCondition findConditionByConditonID(int conditionID) {
        return patientConditionRepo.findOne(conditionID);
    }
    public Appointment findAppointmentByPatientID(int patientID) {
        return aptRepo.findOne(patientID);
    }
    
    
    // Get dates based on patientID for dependent drop down list 
    public Map<Integer, Set<Date>> getDates(int patientID) {
          
          List<Appointment> appointment = aptRepo.findAllByPatientPatientID(patientID);
        Map<Integer, Set<Date>> datesMap = new HashMap<Integer, Set<Date>>();
        Set<Date> dates = new HashSet<Date>();
        for (Appointment appt : appointment) {
            dates.add(appt.getAppointmentDate());
        }
        datesMap.put(patientID, dates);
        return datesMap;
    }
    
    
    
/*    public Set<Date> findAppointmentDateByPatientID (int patientID) {
        Appointment apt = aptRepo.findOne(patientID);
        Set<Date> d = new HashSet<Date>();
        d.add(apt.getAppointmentDate());
        return d;
    }*/
    
/*    public PatientCondition save (PatientCondition pc, int id) {
        List<Patient> patients = new ArrayList<>();
        for (Patient p: patients) {
          p =  patientRepository.findNameByPatientID(id);
          pc.setPatientName(p.getName());
        }
     
        return patientConditionRepo.save(pc);
        
    }*/
}
