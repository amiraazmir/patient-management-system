package com.batchfour.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medicine {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "medicine_id")
    private int medicineID;
    
    @Column(name = "medicine_name")
    private String medicineName; 
    
    @Column(name = "quantity")
    private int quantity;
    
    public int getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(int medicineID) {
        this.medicineID = medicineID;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    } 
    

}
