package com.nishatsultana.myehr.Model;

public class Symptoms {

    private  String symptomdate, symptomdescription;

    public Symptoms(){}

    public Symptoms(String symptomdate, String symptomdescription) {
        this.symptomdate = symptomdate;
        this.symptomdescription = symptomdescription;
    }

    public String getSymptomdate() {
        return symptomdate;
    }

    public void setSymptomdate(String symptomdate) {
        this.symptomdate = symptomdate;
    }

    public String getSymptomdescription() {
        return symptomdescription;
    }

    public void setSymptomdescription(String symptomdescription) {
        this.symptomdescription = symptomdescription;
    }
}
