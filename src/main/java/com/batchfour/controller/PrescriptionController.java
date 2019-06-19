package com.batchfour.controller;

import java.util.Arrays;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.batchfour.repository.DoctorRepository;
import com.batchfour.repository.MedicineRepository;
import com.batchfour.repository.PatientRepository;
import com.batchfour.repository.PrescriptionRepository;
import com.batchfour.model.Medicine;
import com.batchfour.model.Prescription;

@Controller
public class PrescriptionController {

	
	@Autowired
    private PrescriptionRepository prescriptionRepo;
	

	@Autowired
    private MedicineRepository medicineRepo;
	
	 
    @Autowired
    private DoctorRepository drRepo;
    
    @Autowired
    private PatientRepository ptRepo;
	
/*	
	@RequestMapping(value = "/doctordashboard", method = RequestMethod.GET)
    public String doctordashboard() {
        return "doctordashboard";
    }*/

    // display data together with prescription
/*    @RequestMapping(value = "prescription", method = RequestMethod.GET)
    public String prescriptionMedicine(Model model) {
    	
    	model.addAttribute("prescription",new Prescription());
        model.addAttribute("prescriptions", prescriptionRepo.findAll());
        model.addAttribute("patient", patientRepo.findAll());
        model.addAttribute("medicine", medicineRepo.findAll());
        return "prescriptionpagedynamic";
    }*/

    
    // Add prescription
    @RequestMapping(value = "/addPrescriptions", method = RequestMethod.POST)
    public String save(Prescription prescription) {
    	HashMap<String, String> medicineMap = new HashMap<String,String>();
        medicineMap.put(prescription.getMedicineName(), prescription.getQuantity());
        prescriptionRepo.save(prescription);
        
        String[] qtySplit = prescription.getQuantity().split(",");
        String[] medicineSplit = prescription.getMedicineName().split(",");
        System.out.println("----Quantity Array------" + Arrays.toString(qtySplit));
        System.out.println("----Medicine Name Array------" + Arrays.toString(medicineSplit));
        //int[] medicineQuantity;
        for (int i =0; i < qtySplit.length; i++) {
           int medicineQuantitySplit = Integer.parseInt(qtySplit[i]);
           String medicineNameSplit = medicineSplit[i];
           //System.out.println("----medicineQuantity------" + medicineQuantitySplit);
           Medicine medicine = medicineRepo.findByMedicineName(medicineNameSplit);
           int finalQuantity = medicine.getQuantity() - medicineQuantitySplit;
           //System.out.println("----medicineQuantity------" + finalQuantity);
           medicine.setQuantity(finalQuantity);
           medicineRepo.save(medicine);
        }
       
        return "redirect:/doctor/prescription";
    }

    //delete data
    @RequestMapping(value = "doctor/prescription/delete/{id}", method = RequestMethod.GET)
    public String deletePrescription(@PathVariable("id") int prescriptionID) {
        prescriptionRepo.delete(prescriptionID);
        return "redirect:/doctor/prescription";
    }    
    
    @RequestMapping(value = "/edit/{prescriptionID}", method = RequestMethod.GET)
    public String edit(@PathVariable("prescriptionID") int prescriptionID, Model model){
    	model.addAttribute("medicine", medicineRepo.findAll());
        model.addAttribute("prescription",prescriptionRepo.findOne(prescriptionID));
        model.addAttribute("drCount", drRepo.count());
        model.addAttribute("ptCount", ptRepo.count());
        return "/doctor/editPrescription";
    }
    
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveForEdit(Prescription prescription){
        prescriptionRepo.save(prescription);
        return "/doctor/prescription";
    }

}
