package com.batchfour.service;

import com.batchfour.model.Doctor;
import com.batchfour.model.User;

//by rasyidah not using repo
public interface UserService {
	public void saveUser(User user);
    public void savePassword(User user);
    public void saveDetails(Doctor doc);
    public void saveDoctor(User user);
    public void saveDoctorWithoutEncryption(User user);
    public User findByUsername(String username);
    public User findByUserID(int userID);
}
