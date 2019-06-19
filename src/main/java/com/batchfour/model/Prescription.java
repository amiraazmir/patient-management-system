package com.batchfour.model;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prescription")
public class Prescription {

		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO) 
		@Column(name = "prescription_id")
	    private int prescriptionID;
		
		@OneToOne(mappedBy = "prescription")
		private Doctor doctor;
		
	    @Column(name = "doctor_name")
	    private String doctorName;
		
		@Column(name = "patient_name")
	    private String patientName;
	    
		@Column(name = "case_history")
		 private String casehistory;
		
		@Column(name = "medicine_name")
	    private String medicineName; 
		
		@Column(name = "quantity")
	    private String quantity;
		
		@Column(name = "prescription_date")
	    private Date pdate;
		
	/*
		Medicine medicine;

		public Medicine getMedicine() {
            return medicine;
        }

        public void setMedicine(Medicine medicine) {
            this.medicine = medicine;
        }*/

        public int getPrescriptionID() {
			return prescriptionID;
		}

		public Doctor getDoctor() {
            return doctor;
        }

        public void setDoctor(Doctor doctor) {
            this.doctor = doctor;
        }

        public void setPrescriptionID(int prescriptionID) {
			this.prescriptionID = prescriptionID;
		}

		public String getDoctorName() {
			return doctorName;
		}

		public void setDoctorName(String doctorName) {
			this.doctorName = doctorName;
		}

		public String getPatientName() {
			return patientName;
		}

		public void setPatientName(String patientName) {
			this.patientName = patientName;
		}

		public String getMedicineName() {
			return medicineName;
		}

		public void setMedicineName(String medicineName) {
			this.medicineName = medicineName;
		}

		public String getQuantity() {
			return quantity;
		}

		public void setQuantity(String quantity) {
			this.quantity = quantity;
		}


		public Date getPdate() {
			return pdate;
		}

		public void setPdate(Date pdate) {
			this.pdate = pdate;
		}

		public String getCasehistory() {
			return casehistory;
		}

		public void setCasehistory(String casehistory) {
			this.casehistory = casehistory;
		}
	    
		
}
