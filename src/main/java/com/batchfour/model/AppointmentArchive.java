package com.batchfour.model;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AppointmentArchive {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appointment_archive_id")
    private int appointmentArchiveId;

   
    @Column(name = "patient_archive_id")
    private int patientArchiveid;
    
    @Column(name = "created_on")
    private Date createdOn;
  
    
    @Column(name = "doctor_archive_id")
    private int doctorArchiveid;
//
    @Column(name = "appointment_date_archive")
    private Date appointmentDate;

    @Column(name = "appointment_time_archive")
    private Time appointmentTime;

    @Column(name = "medical_symptoms_archive")
    private String medicalSymptoms;

    public int getAppointmentArchiveId() {
        return appointmentArchiveId;
    }

    public void setAppointmentArchiveId(int appointmentArchiveId) {
        this.appointmentArchiveId = appointmentArchiveId;
    }

    public int getPatientArchiveid() {
        return patientArchiveid;
    }

    public void setPatientArchiveid(int patientArchiveid) {
        this.patientArchiveid = patientArchiveid;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public int getDoctorArchiveid() {
        return doctorArchiveid;
    }

    public void setDoctorArchiveid(int doctorArchiveid) {
        this.doctorArchiveid = doctorArchiveid;
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

   
}
