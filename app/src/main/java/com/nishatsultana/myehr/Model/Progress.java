package com.nishatsultana.myehr.Model;

public class Progress {

    String doctorName, doctorPhone, progressReportnDate, progressText;

    Progress(){}

    public Progress(String doctorName, String doctorPhone, String progressReportnDate, String progressText) {
        this.doctorName = doctorName;
        this.doctorPhone = doctorPhone;
        this.progressReportnDate = progressReportnDate;
        this.progressText = progressText;
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

    public String getProgressReportnDate() {
        return progressReportnDate;
    }

    public void setProgressReportnDate(String progressReportnDate) {
        this.progressReportnDate = progressReportnDate;
    }

    public String getProgressText() {
        return progressText;
    }

    public void setProgressText(String progressText) {
        this.progressText = progressText;
    }
}
