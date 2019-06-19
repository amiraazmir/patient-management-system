package com.batchfour.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "doctor_id")
    private int doctorID;
    
    @Column(name = "doctor_name")
    private String doctorName;
    
    @Column(name = "mobile_no")
    private int mobileNo;

    @Column(name = "email")
    private String doctorEmail;
    
    @Column(name = "department")
    private String doctorDepartment;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name ="user_id", referencedColumnName="user_id")
    private User user;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="doctor_id", referencedColumnName="doctor_id")
    private Prescription prescription;
    
    
    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Doctor [doctorID=" + doctorID + ", doctorName=" + doctorName + ", mobileNo=" + mobileNo
                + ", doctorEmail=" + doctorEmail + ", doctorDepartment=" + doctorDepartment + ", user=" + user
                + ", appointment=" + appointment + "]";
    }



    public String getDoctorDepartment() {
        return doctorDepartment;
    }

    public void setDoctorDepartment(String doctorDepartment) {
        this.doctorDepartment = doctorDepartment;
    }



    @OneToMany(mappedBy = "doctor", cascade=CascadeType.ALL)
    private Set<Appointment> appointment = new HashSet<Appointment>();

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(int mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public Set<Appointment> getAppointment() {
        return appointment;
    }

    public void setAppointment(Set<Appointment> appointment) {
        this.appointment = appointment;
    }
    


    
}
