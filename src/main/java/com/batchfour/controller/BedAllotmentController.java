package com.batchfour.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.batchfour.model.Bed;
import com.batchfour.model.BedAllotment;
import com.batchfour.model.Patient;
import com.batchfour.repository.BedAllotmentRepository;
import com.batchfour.repository.BedRepository;
import com.batchfour.repository.DoctorRepository;
import com.batchfour.repository.PatientRepository;


@Controller
@RequestMapping(value="/admin/bedallotment")
public class BedAllotmentController {

    @Autowired
    private BedRepository bedRepo;
    
    @Autowired
    private DoctorRepository drRepo;
    
    @Autowired
    private PatientRepository ptRepo;
    
    @Autowired
    private BedAllotmentRepository bedAllotmentRepo;
    
    public BedAllotmentController() {
        
    }

    // display data together with bedallotment
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String bedAllotment(Model model) {
        
        model.addAttribute("bed",  new Bed());

        model.addAttribute("bedList", bedRepo.findAll());
        model.addAttribute("bedAllotmentList", bedAllotmentRepo.findByBedAllotmentStatus(null));
        model.addAttribute("patientlist", ptRepo.findAllByWardStatus("Not allocated"));

        model.addAttribute("drCount", drRepo.count());
        model.addAttribute("ptCount", ptRepo.count());
        return "/admin/bedallotment";
    }

    // adding data
    @RequestMapping(value = "/addBedAllotment", method = RequestMethod.POST)
    public String addBedAllotment(Bed bed) {
        bedRepo.save(bed);
                
        BedAllotment toSaveStatus = bedAllotmentRepo.findOne(bed.getBedAllotment().getBedAllotmentID());
        Patient patient = ptRepo.findOne(bed.getPatient().getPatientID());
        toSaveStatus.setBedAllotmentStatus("Allocated");
        patient.setWardStatus("Warded");
        bedAllotmentRepo.save(toSaveStatus);
        ptRepo.save(patient);
        return "redirect:/admin/bedallotment";
    }

    //delete data 
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBed(@PathVariable("id") Integer bedID, Model model) {
        bedRepo.findOne(bedID).getBedAllotment().setBedAllotmentStatus(null);
        bedRepo.delete(bedID);
        return "redirect:/admin/bedallotment";
    }    
    
    @RequestMapping(value = "/edit/{bedID}", method = RequestMethod.GET)
    public String editBed(@PathVariable("bedID") int bedID, Model model){
        model.addAttribute("bed",bedRepo.findOne(bedID));
        model.addAttribute("drCount", drRepo.count());
        model.addAttribute("ptCount", ptRepo.count());
        return "/admin/EditBedAllotment";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveForEdit(Bed bed){
        bedRepo.save(bed);
        return "redirect:/admin/bedallotment";
    }


}
