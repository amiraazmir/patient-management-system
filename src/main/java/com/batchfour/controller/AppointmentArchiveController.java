package com.batchfour.controller;


import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.batchfour.model.AppointmentArchive;
import com.batchfour.repository.AppointmentArchiveRepository;
import com.batchfour.repository.AppointmentRepository;


@Controller
@RequestMapping(value = "/admin/patientappointment")
public class AppointmentArchiveController {
    
    @Autowired
    private AppointmentArchiveRepository aptArchiveRepo;
    
    @Autowired
    private AppointmentRepository aptRepo;


    //delete data
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deletePatientappointment(@PathVariable("id") int id, Model model) {

        archivePatientappointment(id); 
        
        aptRepo.delete(id);
        return "redirect:/admin/patientappointment";
    }


    
    // archiving data before deleting
    public void archivePatientappointment(int id) {

        AppointmentArchive appointmentArchive = new AppointmentArchive();
        
        appointmentArchive.setAppointmentDate(aptRepo.findOne(id).getAppointmentDate());
        appointmentArchive.setAppointmentTime(aptRepo.findOne(id).getAppointmentTime());
               
        appointmentArchive.setCreatedOn(aptRepo.findOne(id).getAppointmentDate());
        appointmentArchive.setPatientArchiveid(aptRepo.findOne(id).getPatient().getPatientID()); 
        appointmentArchive.setDoctorArchiveid(aptRepo.findOne(id).getDoctor().getDoctorID());
        appointmentArchive.setMedicalSymptoms(aptRepo.findOne(id).getMedicalSymptoms());
        
        System.out.println(aptRepo.findOne(id).getAppointmentDate());
        
        aptArchiveRepo.save(appointmentArchive);       
        System.out.println("Archived");

        System.out.println(aptArchiveRepo.findAll());

     
    }
    
    //delete archive records older than one year
    @RequestMapping(value = "/deleteArchiveoneyear")
    public String deleteArchivebydate() {
        
        System.out.println("deleteArchivebydate() method is reached");
        
        Calendar cal = Calendar.getInstance();
               
        cal.add(Calendar.YEAR, -1);
        java.util.Date expiryDate = cal.getTime();
        java.sql.Date sqlExpirydate = new java.sql.Date(expiryDate.getTime());
        
        System.out.println(sqlExpirydate);
        
        try
        {
            aptArchiveRepo.deleteByCreatedOnBefore(sqlExpirydate);
            System.out.println("deleted");
           
        }
        catch(NullPointerException npe)
        {              
            System.out.println("No such date");
        }
        
        return "redirect:/admin/patientappointment";
        
    }

        
}
