package com.batchfour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/*import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;*/

import com.batchfour.model.PatientCondition;
import com.batchfour.repository.DoctorRepository;
import com.batchfour.repository.PatientConditionRepository;
import com.batchfour.repository.PatientRepository;
import com.batchfour.service.PatientService;

@Controller
public class PatientConditionController {
    @Autowired
    PatientConditionRepository patientConditionRepo;
    @Autowired
    PatientService patientService;
    
    @Autowired
    private DoctorRepository drRepo;
    
    @Autowired
    private PatientRepository ptRepo;

    @RequestMapping(value="/addPatient", method=RequestMethod.POST)
    public String addPatient(PatientCondition patientCondition, Model model){ 
      
            patientConditionRepo.save(patientCondition);
      
       
        return "redirect:/doctor/patientcondition";
    }
    
    @RequestMapping(value="doctor/patientcondition/edit/{conditionID}",  method=RequestMethod.GET)
    public String edit(@PathVariable("conditionID") int conditionID, Model model){
        model.addAttribute("patientCondition", patientService.findConditionByConditonID(conditionID));
        model.addAttribute("drCount", drRepo.count());
        model.addAttribute("ptCount", ptRepo.count());
        return "doctor/editPatientCondition";
    }
    
/*    //edit for modal 
    @RequestMapping(value="/doctordashboard/patientcondition/edit",  method=RequestMethod.GET)
    @ResponseBody
    public String editModal(@RequestParam int conditionID, Model model){
        model.addAttribute("patientCondition", patientService.findConditionByConditonID(conditionID));
        return "/doctordashboard/patientcondition/edit";
    }*/
    
    
 //edit for another page
    @RequestMapping(value = "doctor/patientcondition/edit/{conditionID}/update", method = RequestMethod.POST)
    public String update(PatientCondition patientCondition){
        patientConditionRepo.save(patientCondition);
        System.out.println("patient updated");
        
        return "redirect:/doctor/patientcondition";
    }
 
    
    @RequestMapping(value = "doctor/patientcondition/delete/{conditionID}", method=RequestMethod.GET)
    public String delete(@PathVariable("conditionID") int conditionID){
        patientConditionRepo.delete(conditionID);
        System.out.println("patient deleted");
        return "redirect:/doctor/patientcondition";
    }
 
}
