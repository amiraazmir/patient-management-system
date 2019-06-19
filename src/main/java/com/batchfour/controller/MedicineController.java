package com.batchfour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.batchfour.model.Medicine;
import com.batchfour.repository.DoctorRepository;
import com.batchfour.repository.MedicineRepository;
import com.batchfour.repository.PatientRepository;

@Controller
@RequestMapping(value="/admin/medicinepage")
public class MedicineController {
    
    @Autowired
    private MedicineRepository medRepo;
    
    @Autowired
    private PatientRepository ptRepo;
   
    @Autowired
    private DoctorRepository drRepo;
    


    // display data together with bedallotment
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String medicinepage(Model model) {
        model.addAttribute("medicines", medRepo.findAll());
        model.addAttribute("drCount", drRepo.count());
        model.addAttribute("ptCount", ptRepo.count());
        return "/admin/medicinepage";
    }
  
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBed(@PathVariable("id") int medicineID, Model model){
        model.addAttribute("medicine",medRepo.findOne(medicineID));
        model.addAttribute("drCount", drRepo.count());
        model.addAttribute("ptCount", ptRepo.count());
        return "/admin/EditMedicinePage";
    }
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveForEdit(Medicine medicine){
        medRepo.save(medicine);
        return "redirect:/admin/medicinepage";
    }

}
