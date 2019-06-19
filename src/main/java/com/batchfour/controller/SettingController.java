package com.batchfour.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.batchfour.model.Doctor;
import com.batchfour.model.User;
import com.batchfour.repository.DoctorRepository;
import com.batchfour.repository.PatientRepository;
import com.batchfour.service.UserService;

@Controller
public class SettingController {

	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private DoctorRepository docRepo;

	@Autowired
	private PatientRepository ptRepo;
	
	//admin change password
	@RequestMapping(value = "/admin/settings", method = RequestMethod.GET)
	public ModelAndView passwordAdmin(ModelAndView modelView, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // osf.security.core.Authentication
		User user = userService.findByUsername(auth.getName());
		
		modelView.addObject("drCount", docRepo.count());
		modelView.addObject("ptCount", ptRepo.count());
		
		modelView.addObject("userName", user.getUsername());
		
		return modelView;

	}

    @RequestMapping(value = "/admin/settingsSave", method = RequestMethod.POST)
    public ModelAndView passwordChangeAdmin(ModelAndView modelView,
            @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // osf.security.core.Authentication
        User user = userService.findByUsername(auth.getName());
        user.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));
        userService.savePassword(user);
        redir.addFlashAttribute("successMessage", "You have successfully change your password.  You may re-login.");
        modelView.setViewName("redirect:/login");

        return modelView;
    }


    //doctor change password
    @RequestMapping(value = "/doctor/settings", method = RequestMethod.GET)
    public ModelAndView passwordDoc( ModelAndView modelView) {
        modelView.addObject("drCount", docRepo.count());
        modelView.addObject("ptCount", ptRepo.count());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // osf.security.core.Authentication
        User user = userService.findByUsername(auth.getName());
        modelView.addObject("userName", user.getUsername());
        modelView.addObject("user", new User());
        //modelView.addObject("doc", docRepo.findOne(doctorID));
        return modelView;
    }


    @RequestMapping(value = "/doctor/settingsSave", method = RequestMethod.POST)
    public ModelAndView passwordChangeDoc(ModelAndView modelView, 
            @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // osf.security.core.Authentication
        User user = userService.findByUsername(auth.getName());
        user.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));
        userService.savePassword(user);
        redir.addFlashAttribute("successMessage", "You have successfully change your password.  You may re-login.");
        modelView.setViewName("redirect:/login");

        return modelView;
    }
    // doctor change details
    @RequestMapping(value = "/doctor/settingsDet", method = RequestMethod.POST)
    public ModelAndView settingChangeDoc(ModelAndView modelView, Doctor doc) {
        userService.saveDetails(doc);
        modelView.setViewName("doctor/settings");
        return modelView;
    }
}