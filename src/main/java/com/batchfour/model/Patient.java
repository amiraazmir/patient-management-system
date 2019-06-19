package com.batchfour.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

import com.batchfour.annotation.UniqueNric;

@Entity
public class Patient {

 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_id")
    private int patientID;
    
    @NotEmpty
    @Column(name = "patient_name")
    private String patientName;
    
    @Column(name = "mobile_no")
    private int mobileNo;

    @Column(name = "ic_no")
    @UniqueNric(message = "Such NRIC already exists !")
    private String patientNric;
    
    @Column(name = "email")
    private String patientEmail;
    
    @Column(name = "gender")    
    private String patientGender;
    
    @Column(name = "ward_status")
    private String wardStatus;


    @OneToMany(mappedBy = "patient", cascade=CascadeType.ALL)
    private Set<Appointment> appointment = new HashSet<Appointment>();

    @OneToMany(mappedBy = "patient", cascade=CascadeType.ALL)
    private Set<Bed> bed = new HashSet<Bed>();
    
    public Set<Bed> getBed() {
        return bed;
    }

    public void setBed(Set<Bed> bed) {
        this.bed = bed;
    }
    
    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(int mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPatientNric() {
        return patientNric;
    }

    public void setPatientNric(String patientNric) {
        this.patientNric = patientNric;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getWardStatus() {
        return wardStatus;
    }

    public void setWardStatus(String wardStatus) {
        this.wardStatus = wardStatus;
    }

    
    public Set<Appointment> getAppointment() {
        return appointment;
    }   

    public void setAppointment(Set<Appointment> appointment) {
        this.appointment = appointment;
    }
    

    @Override
    public String toString() {
        return "Patient [patientName=" + patientName + ", mobileNo=" + mobileNo + ", patientNric=" + patientNric
                + ", patientEmail=" + patientEmail + ", patientGender=" + patientGender + ", wardStatus=" + wardStatus
                + ", appointment=" + appointment + "]";
    }
}
