package com.batchfour.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.batchfour.model.Patient;
import com.batchfour.repository.DoctorRepository;
import com.batchfour.repository.PatientRepository;

@Controller
@RequestMapping(value = "/admin/patientpage")
public class PatientController  {
    
    
    @Autowired
    private PatientRepository ptRepo;
   
    @Autowired
    private DoctorRepository drRepo;
    
    int i=0;
    private String ic;

    
//    @Query("select  from Customer c where c.email = :email")
//    Stream<Patient> findByNRICReturnStream(@Param("email") String email);


    // display data together with bedallotment
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String patientpage(Model model) {
        
        model.addAttribute("patientPage", ptRepo.findAll()); 
      
        model.addAttribute("drCount", drRepo.count());
        model.addAttribute("ptCount", ptRepo.count());
        
        if(i== -1) {
            model.addAttribute("error", "That NRIC : "+ ic+" is already registered in the system");
        } 
        return "/admin/patientpage";
    }
    

    // adding data
    @RequestMapping(value = "/addPatient", method = RequestMethod.POST)
    public String addPatient(Patient patient,Model model, BindingResult result) {
        
        List<Patient> patients = (List<Patient>) ptRepo.findAll();
        
        if (patients.isEmpty()) 
        {
            ptRepo.save(patient);

        } else
        {
            for(i=0;i<patients.size();i++) {
                if(patients.get(i).getPatientNric().equals(patient.getPatientNric()))
                {
                    System.out.println("NRIC EXIST:"+patients.get(i).getPatientNric());
                    
                    i=0;
                    break;
                } 
            }
            
            if(i!=0) {
                ptRepo.save(patient);
                return "redirect:/admin/patientpage";
            } 
            else
            {
                i= -1;
                ic = patient.getPatientNric();
                // to display here          
            }
        }
        
        
        return "redirect:/admin/patientpage";

    }

        

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBed(@PathVariable("id") Integer patientID, Model model) {
//        System.out.println(ptRepo.findOne(patientID));
//        System.out.println(patientID);
        ptRepo.delete(patientID);
        return "redirect:/admin/patientpage";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editDoctor(@PathVariable("id") int patientID, Model model){
        model.addAttribute("patient",ptRepo.findOne(patientID));
        model.addAttribute("drCount", drRepo.count());
        model.addAttribute("ptCount", ptRepo.count());
        return "/admin/EditPatientPage";
    }
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveForEditDoctor(Patient patient,Model model){
        ptRepo.save(patient);
        return "redirect:/admin/patientpage";
    }
    


}
