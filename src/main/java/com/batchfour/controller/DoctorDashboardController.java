package com.batchfour.controller;

import java.sql.Date;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.batchfour.model.Appointment;
import com.batchfour.model.Patient;
import com.batchfour.model.PatientCondition;
import com.batchfour.model.Prescription;
import com.batchfour.model.User;
import com.batchfour.repository.AppointmentRepository;
import com.batchfour.repository.DoctorRepository;
import com.batchfour.repository.MedicineRepository;
import com.batchfour.repository.PatientConditionRepository;
import com.batchfour.repository.PatientRepository;
import com.batchfour.repository.PrescriptionRepository;
import com.batchfour.service.PatientService;
import com.batchfour.service.UserService;

@Controller
public class DoctorDashboardController {
    
    @Autowired
    PatientConditionRepository patientConditionRepo;
    
    @Autowired
    PatientRepository patientRepo;
    
    @Autowired
    PatientService patientService;
    
    @Autowired
    private UserService userService;
    @Autowired
    private PrescriptionRepository prescriptionRepo;
   
    @Autowired
    private AppointmentRepository appointmentRepo;
    
    @Autowired
    private MedicineRepository medicineRepo;
    
    @Autowired
    private DoctorRepository doctorRepo;
    
    User user;
    
    //Maping to the doctor dashboard
    @RequestMapping(value = "/doctor/dashboard", method = RequestMethod.GET)
    public ModelAndView homeD(Model model) {
        model.addAttribute("ptCount", patientRepo.count());
        model.addAttribute("drCount", doctorRepo.count());
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        user = userService.findByUsername(auth.getName());
        modelAndView.addObject("userName", "Welcome Dr." + user.getDoctor().getDoctorName());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("doctor/dashboard");
        return modelAndView;
    }
    
    //Mapping to the patient condition module
    @RequestMapping(value="/doctor/patientcondition", method=RequestMethod.GET)
    public String patientConditionList(Model model, Patient patient, Appointment apt) {
        model.addAttribute("ptCount", patientRepo.count());
        model.addAttribute("drCount", doctorRepo.count());
        model.addAttribute("patientCondition", new PatientCondition());
        model.addAttribute("patientList", patientRepo.findAll());
        model.addAttribute("appointmentDateList", appointmentRepo.findAll());
        model.addAttribute("patientsconditions", patientConditionRepo.findAll());
       return "doctor/patientcondition";
    }
    
   
    // Controller method to get appointment dates for patient condition date drop down list  
    @RequestMapping(value = "/appointmentDates")
    @ResponseBody
    public Set<Date> getAppointmentDates(@RequestParam int patientID) {   
        Map<Integer, Set<Date>> dates = patientService.getDates(patientID);
                return dates.get(patientID);
        
    }
    
    //Mapping to the prescription module
    @RequestMapping(value="/doctor/prescription", method=RequestMethod.GET)
    public String prescription(Model model) {
        //ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("ptCount", patientRepo.count());
        model.addAttribute("drCount", doctorRepo.count());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        user = userService.findByUsername(auth.getName());
       //modelAndView.addObject("doctorName", user.getDoctor().getDoctorName());
        model.addAttribute("doctorName",  user.getDoctor().getDoctorName());
        model.addAttribute("prescription",new Prescription());
        model.addAttribute("prescriptions", prescriptionRepo.findAll());
        model.addAttribute("patient", patientRepo.findAll());
       
       
       // model.addAttribute("doctor",doctorRepo.findOne(hereToBeDynamic).getDoctorName());
        
        model.addAttribute("medicine", medicineRepo.findAll());
       return "doctor/prescription";
    }
    //testing smth
/*    @RequestMapping(value = "/doctordashboard/prescription", method = RequestMethod.GET)
    public ModelAndView prescriptionList(Model model) {
        model.addAttribute("prescription",new Prescription());
        model.addAttribute("patient", patientRepo.findAll());
        model.addAttribute("medicine", medicineRepo.findAll());
        Prescription pres = new Prescription();
        model.addAttribute("prescriptions", prescriptionRepo.findAll());

        return new ModelAndView("prescription", "prescriptions", pres);
    }*/
    
    
    //Mapping to the view appointment module 
    @RequestMapping(value="/doctor/viewAppointment", method=RequestMethod.GET)
    public String viewAppointment(Model model) {
        model.addAttribute("ptCount", patientRepo.count());
        model.addAttribute("drCount", doctorRepo.count());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        user = userService.findByUsername(auth.getName());
        model.addAttribute("doctorAppointments", appointmentRepo.findAllByDoctorDoctorID(user.getDoctor().getDoctorID()));
       return "doctor/viewAppointment";
    }
    
    @RequestMapping(value = "/doctor/home", method = RequestMethod.GET)
    public String goBackHome(Model model) {
        return "/doctor/home";
    }
}
