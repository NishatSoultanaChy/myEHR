package com.nishatsultana.myehr.Model;

public class Prescription {

    String doctorName, doctorPhone, prescriptionDate, prescriptionText;

    Prescription(){}

    public Prescription(String doctorName, String doctorPhone, String prescriptionDate, String prescriptionText) {
        this.doctorName = doctorName;
        this.doctorPhone = doctorPhone;
        this.prescriptionDate = prescriptionDate;
        this.prescriptionText = prescriptionText;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(String prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public String getPrescriptionText() {
        return prescriptionText;
    }

    public void setPrescriptionText(String prescriptionText) {
        this.prescriptionText = prescriptionText;
    }
}
