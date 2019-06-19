package com.batchfour.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;

import com.batchfour.model.Doctor;
import com.batchfour.model.User;
import com.batchfour.repository.DoctorRepository;
import com.batchfour.repository.PatientRepository;
import com.batchfour.service.UserService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/admin/doctorpage")
public class DoctorController {

    @Autowired
    private DoctorRepository drRepo;

    @Autowired
    private PatientRepository ptRepo;

    @Autowired
    private UserService userService;

    // display data together with all doctors
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String doctorpage(Model model) {

        model.addAttribute("doctor", new Doctor());

        model.addAttribute("doctorList", drRepo.findAll());

        model.addAttribute("drCount", drRepo.count());
        model.addAttribute("ptCount", ptRepo.count());
        return "/admin/doctorpage";
    }

    @RequestMapping(value = "/edit/{doctorID}", method = RequestMethod.GET)
    public String editDoctor(@PathVariable("doctorID") int doctorID, Model model) {
        model.addAttribute("doctor", drRepo.findOne(doctorID));

        model.addAttribute("drCount", drRepo.count());
        model.addAttribute("ptCount", ptRepo.count());
        return "/admin/EditDoctorPage";
    }

    // delete data
    @RequestMapping(value = "/delete/{doctorID}", method = RequestMethod.GET)
    public String deleteDoctor(@PathVariable("doctorID") Integer doctorID, Model model) {
        System.out.println("--------------------passed DELETE--------");
        drRepo.delete(doctorID);

        return "redirect:/admin/doctorpage";
    }

    @RequestMapping(value = "/addDoctor", method = RequestMethod.POST)
    public String saveDoctor(@Valid Doctor doctor, BindingResult bindingResult, User user,
            final RedirectAttributes redirectAttributes, Model model) {
        //ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("doctorList", drRepo.findAll());

        model.addAttribute("drCount", drRepo.count());
        model.addAttribute("ptCount", ptRepo.count());
        
        User userExists = userService.findByUsername(doctor.getUser().getUsername());
        Doctor doctorExists = drRepo.findByMobileNo(doctor.getMobileNo());

        if (userExists != null) {
            bindingResult.rejectValue("user.username", "error.doctor",
                    "There is already a user registered with the email provided");
        }
        
        if (doctorExists != null) {
            bindingResult.rejectValue("mobileNo", "error.doctor",
                    "There is already a doctor registered with the mobile number provided");
        }
        
        if (bindingResult.hasErrors()) {

            return "admin/doctorpage";
        } else {
            userService.saveDoctor(doctor.getUser());
            drRepo.save(doctor);
            redirectAttributes.addFlashAttribute("successMessage", "User has been registered successfully");
        }
        return "redirect:/admin/doctorpage";
    }

    /*
     * @RequestMapping(value = "/addDoctor", method = RequestMethod.POST) public
     * String saveDoctorTest(@Valid Doctor doctor, BindingResult bindingResult) {
     * System.out.println("---------------here name of depart :"+doctor.
     * getDoctorDepartment());
     * 
     * ModelAndView modelAndView = new ModelAndView(); User userExists =
     * userService.findByUsername(doctor.getUser().getUsername()); if (userExists !=
     * null) { bindingResult.rejectValue("user.username", "error.doctor",
     * "There is already a user registered with the email provided");
     * 
     * }
     * 
     * System.out.println("----------doc department BELOW------------");
     * System.out.println(doctor.getDoctorDepartment());
     * System.out.println("----------doc email BELOW------------");
     * System.out.println(doctor.getDoctorEmail());
     * System.out.println("----------doc name BELOW------------");
     * System.out.println(doctor.getDoctorName());
     * System.out.println("----------------------------username----------"+doctor.
     * getUser().getUsername()); User user = new User();
     * user.setUsername(doctor.getUser().getUsername());
     * user.setPassword(doctor.getUser().getPassword());
     * System.out.println("-------------------after saving 5------------" +
     * user.getUsername()); userService.saveDoctor(user);
     * 
     * 
     * drRepo.save(doctor); modelAndView.addObject("successMessage",
     * "User has been registered successfully");
     * modelAndView.setViewName("/admin/doctorpage");
     * 
     * // //insert registration methods of userService return
     * "redirect:/admin/doctorpage"; }
     */

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String saveForEditDoctor(Doctor doctor) {
        drRepo.save(doctor);
        return "redirect:/admin/doctorpage";
    }

}
