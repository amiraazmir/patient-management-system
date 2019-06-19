/**

* Model for Patient Condition.

*

* Copyright (c) Virtusa Corporation 2017, All Rights Reserved.<br><br>.

*

* This class provides a model for the Patient Condition.

*

* @author Amira

*

* @version <version of the class>

*

* @see see also

*/


package com.batchfour.model;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="patient_condition")
public class PatientCondition{
    
/*Id for */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name="condition_id")
    private int conditionID;
    
    @ManyToOne 
    @JoinColumn(name="patient_id", updatable=false)
    private Patient patient;
    
    @Column (name="condition_name")
    private String conditionName;
    
    @Column (name="appointment_date")
    private Date apptDate;
    
    public PatientCondition() {
        super();
    }
    
    public PatientCondition(int conditionID, Patient patient, String conditionName, int patientID, Date appointmentDate) {
        super();
        this.conditionID = conditionID;
        this.patient = patient;
        this.conditionName = conditionName;
        //this.patientID = patientID;
        this.apptDate = appointmentDate;
        //this.patientName = name;
    }
    public int getConditionID() {
        return conditionID;
    }
    public void setConditionID(int conditionID) {
        this.conditionID = conditionID;
    }
    public String getConditionName() {
        return conditionName;
    }
    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }
  /*  public int getPatientID() {
        return patientID;
    }
    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }*/
   

    public Patient getPatient() {
        return patient;
    }
    public Date getApptDate() {
        return apptDate;
    }

    public void setApptDate(Date apptDate) {
        this.apptDate = apptDate;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

  /*  public Appointment getAppointment() {
        return appointment;
    }*/

/*
    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }*/

/*    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    } 
    */
    
    
}
