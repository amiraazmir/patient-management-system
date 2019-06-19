package com.batchfour.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.batchfour.model.Doctor;
import com.batchfour.model.Role;
import com.batchfour.model.User;
import com.batchfour.repository.DoctorRepository;
import com.batchfour.repository.RoleRepository;
import com.batchfour.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
	@Autowired
	private DoctorRepository docRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	   @Override
	    public User findByUserID(int userID) {
	        return userRepository.findOne(userID);
	    }
	//to implement on doctor
    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
    
  //for changing password
    @Override
    public void savePassword(User user) {
        // TODO Auto-generated method stub
        //-in the controller-
//      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);      
    }
    
    //update doctor setting
    @Override
    public void saveDetails(Doctor doc) {
        docRepository.findOne(doc.getDoctorID());
        docRepository.save(doc);
    }

    @Override
    public void saveDoctor(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole("DOCTOR");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void saveDoctorWithoutEncryption(User user) {
        // TODO Auto-generated method stub
        user.setPassword(user.getPassword());
        Role userRole = roleRepository.findByRole("DOCTOR");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
        
    }

}
