package com.nishatsultana.myehr.Model;

public class Reports {

   String date, testName, assignedBy, url;

    public Reports(String date, String testName, String assignedBy, String url) {
        this.date = date;
        this.testName = testName;
        this.assignedBy = assignedBy;
        this.url = url;
    }

    public Reports() {  }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public  String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
