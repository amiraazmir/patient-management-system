package com.batchfour.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.batchfour.model.Appointment;
import com.batchfour.repository.AppointmentRepository;
import com.batchfour.repository.DoctorRepository;
import com.batchfour.repository.PatientRepository;


@Controller
@RequestMapping(value = "/admin/patientappointment")
public class AppointmentController {

    @Autowired
    private PatientRepository ptRepo;
    
    @Autowired
    private DoctorRepository drRepo;
    
    @Autowired
    private AppointmentRepository aptRepo;
    
    @Autowired
    AppointmentArchiveController aptArchiveController;
    
    
    // display data in an appointment list in the patient appointment page
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String patientAppointment(Model model) {

        System.out.println("Patient appointment page reached");
        model.addAttribute("drCount", drRepo.count());
        model.addAttribute("ptCount", ptRepo.count());
        
        displayHtmllist(model);     
        model.addAttribute("appointment",  new Appointment()); 
       
        return "/admin/patientappointment";
    }

    // add data
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String savePatientappointment(@Valid Appointment appointment, BindingResult bindingResult, Model model,
            final RedirectAttributes redirectAttributes) {

        displayHtmllist(model);

        if(validAppointment(appointment) == false)
        {
            bindingResult
            .rejectValue("appointmentTimestamp", "error.appointment",
                    "Please enter a valid appointment slot!");

        }

        if (bindingResult.hasErrors()) {
            return "admin/patientappointment";
        }
        else 
        {
            String timeStamp = appointment.getAppointmentDate().toString()+ " " + appointment.getAppointmentTime().toString();
            appointment.setAppointmentTimestamp(timeStamp);

            System.out.println("Saved");     
            aptRepo.save(appointment);
            
            redirectAttributes.addFlashAttribute("successMessage", "Appointment has been scheduled successfully.");           
        }

        return "redirect:/admin/patientappointment";

    }

    //update data
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updatePatientappointment(@Valid Appointment appointment, BindingResult bindingResult, 
            Model model, final RedirectAttributes redirectAttributes) {

        displayHtmllist(model);

        if(validAppointment(appointment) == false)
        {
            bindingResult
            .rejectValue("appointmentTimestamp", "error.appointment",
                    "Please enter a valid appointment slot!");

        }

        if (bindingResult.hasErrors()) {
            return "admin/EditPatientAppointment";
        }
        else 
        {
            String timeStamp = appointment.getAppointmentDate().toString()+ " " + appointment.getAppointmentTime().toString();
            appointment.setAppointmentTimestamp(timeStamp);

            System.out.println("Saved");     
            aptRepo.save(appointment);
            
        }

        return "redirect:/admin/patientappointment";

    }

    //edit data page
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPatientappointment(@PathVariable("id") int id, Model model){

        model.addAttribute("appointment",aptRepo.findOne(id));
        model.addAttribute("doctorlist", drRepo.findAll());
        model.addAttribute("drCount", drRepo.count());
        model.addAttribute("ptCount", ptRepo.count());

        return "/admin/EditPatientAppointment";
    }


    //check validity
    public Boolean validAppointment(Appointment appointment) {

        boolean isValid = true;

        System.out.println("Checking validity");

        try {

            if (appointment.getAppointmentDate().toString() == null || appointment.getAppointmentTime().toString() == null)
            {
                return !isValid;
            }

        }

        catch (NullPointerException npe) {

            return !isValid;
        }

        String givenTimestamp = appointment.getAppointmentDate().toString()+ " " + appointment.getAppointmentTime().toString();

        int givenAppointmentid = appointment.getAppointmentID();
        int givenDoctorid = appointment.getDoctor().getDoctorID();


        ArrayList<Appointment> appointmentlist = (ArrayList<Appointment>) aptRepo.findAll();

        for(Appointment appointment1: appointmentlist)
        {
            String timeStamp = appointment1.getAppointmentTimestamp();

            int readyAppointmentid = appointment1.getAppointmentID();          
            int readyDoctorid = appointment1.getDoctor().getDoctorID();

            if(givenAppointmentid != readyAppointmentid && givenDoctorid == readyDoctorid && givenTimestamp.equals(timeStamp))
            {   
                System.out.println(givenTimestamp);
                System.out.println(timeStamp);
                
                return !isValid;
            }
        }

        return isValid;
    }


    //for displaying main and drop down lists
    public void displayHtmllist(Model model)
    {                  
        aptArchiveController.deleteArchivebydate();
        
        model.addAttribute("drCount", drRepo.count());
        model.addAttribute("ptCount", ptRepo.count());
        
        model.addAttribute("appointmentlist", aptRepo.findAll());
        model.addAttribute("patientlist", ptRepo.findAll());
        model.addAttribute("doctorlist", drRepo.findAll());

    }
    
   
    
}

    

