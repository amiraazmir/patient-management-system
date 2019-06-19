package com.batchfour.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appointment_id")
    private int appointmentID;

    @ManyToOne(optional=false)
    @JoinColumn(name ="patient_id",referencedColumnName="patient_id")
    private Patient patient;

    @ManyToOne(optional=false)
    @JoinColumn(name ="doctor_id",referencedColumnName="doctor_id")
    private Doctor doctor;

    @Column(name = "appointment_date")
    private Date appointmentDate;

    @Column(name = "appointment_time")
    private Time appointmentTime;

    @Column(name = "medical_symptoms")
    private String medicalSymptoms;
       
    @Column(name = "appointment_time_stamp")
    private String appointmentTimestamp;

    public int getAppointmentID() {
        return this.appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Time getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(Time appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getMedicalSymptoms() {
        return medicalSymptoms;
    }

    public void setMedicalSymptoms(String medicalSymptoms) {
        this.medicalSymptoms = medicalSymptoms;
    }

    public String getAppointmentTimestamp() {
        return appointmentTimestamp;
    }

    public void setAppointmentTimestamp(String appointmentTimestamp) {
        this.appointmentTimestamp = appointmentTimestamp;
    }

    
}
