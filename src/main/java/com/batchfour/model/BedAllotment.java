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
import javax.persistence.Table;

@Entity
@Table(name = "bed_allotment")
public class BedAllotment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bed_allotment_id")
    private int bedAllotmentID;
    
    @Column(name = "bed_num")
    private int bedNumber;
    
    @Column(name = "ward_num")
    private int wardNumber;
    
    @Column(name = "status")
    private String bedAllotmentStatus;


    public String getBedAllotmentStatus() {
        return bedAllotmentStatus;
    }

    public void setBedAllotmentStatus(String bedAllotmentStatus) {
        this.bedAllotmentStatus = bedAllotmentStatus;
    }

    @OneToMany(mappedBy = "bedAllotment", cascade=CascadeType.ALL)
    private Set<Bed> bed = new HashSet<Bed>();
    
    
    public int getBedAllotmentID() {
        return bedAllotmentID;
    }

    public Set<Bed> getBed() {
        return bed;
    }

    public void setBed(Set<Bed> bed) {
        this.bed = bed;
    }

    public void setBedAllotmentID(int bedAllotmentID) {
        this.bedAllotmentID = bedAllotmentID;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public int getWardNumber() {
        return wardNumber;
    }

    public void setWardNumber(int wardNumber) {
        this.wardNumber = wardNumber;
    }
    
    

}
