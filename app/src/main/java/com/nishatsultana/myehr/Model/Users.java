package com.nishatsultana.myehr.Model;

public class Users {

    String fullname, licenceno, speciality, email, phone, dateofBirth, gender, bloodgroup, password, role;


    public Users()
    {

    }

    public Users(String fullname, String licenceno, String speciality, String email, String phone, String dateofBirth, String gender, String bloodgroup, String password, String role) {
        this.fullname = fullname;
        this.licenceno = licenceno;
        this.speciality = speciality;
        this.email = email;
        this.phone = phone;
        this.dateofBirth = dateofBirth;
        this.gender = gender;
        this.bloodgroup = bloodgroup;
        this.password = password;
        this.role = role;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLicenceno() {
        return fullname;
    }

    public void setLicenceno(String fullname) {
        this.fullname = fullname;
    }

    public String getSpeciality() {
        return fullname;
    }

    public void setSpeciality(String fullname) {
        this.fullname = fullname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(String dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
