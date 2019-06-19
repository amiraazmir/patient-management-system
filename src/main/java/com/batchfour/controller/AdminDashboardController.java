package com.batchfour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.batchfour.model.User;
import com.batchfour.repository.DoctorRepository;
import com.batchfour.repository.PatientRepository;
import com.batchfour.service.UserService;

@Controller
public class AdminDashboardController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private DoctorRepository drRepo;
    
    @Autowired
    private PatientRepository ptRepo;

    @RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
    public ModelAndView homeA(Model model) {
        model.addAttribute("drCount", drRepo.count());
        model.addAttribute("ptCount", ptRepo.count());
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getUsername());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/dashboard");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public String goBackHome(Model model) {
        return "/admin/home";
    }


}
