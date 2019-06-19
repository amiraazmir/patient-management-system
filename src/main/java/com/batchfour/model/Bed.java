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
@Table(name = "bed")
public class Bed {
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bed_id")
    private int bedID;

    @Column(name = "allotment_date")
    private Date allotmentDate;
    
    @Column(name = "discharged_date")
    private Date dischargeDate;
    

    @ManyToOne(optional=false)
    @JoinColumn(name ="patient_id",referencedColumnName="patient_id")
    private Patient patient;
    
    @ManyToOne(optional=false)
    @JoinColumn(name ="bed_allotment_id",referencedColumnName="bed_allotment_id")
    private BedAllotment bedAllotment;

    public int getBedID() {
        return bedID;
    }

    public void setBedID(int bedID) {
        this.bedID = bedID;
    }

    public Date getAllotmentDate() {
        return allotmentDate;
    }

    public void setAllotmentDate(Date allotmentDate) {
        this.allotmentDate = allotmentDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public BedAllotment getBedAllotment() {
        return bedAllotment;
    }

    public void setBedAllotment(BedAllotment bedAllotment) {
        this.bedAllotment = bedAllotment;
    }
    
    

     

}
