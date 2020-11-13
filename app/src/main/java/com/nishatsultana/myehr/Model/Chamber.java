package com.nishatsultana.myehr.Model;

public class Chamber {

    String chamber,location,visitingHour;

    public Chamber(){}

    public Chamber(String chamber, String location, String visitingHour) {
        this.chamber = chamber;
        this.location = location;
        this.visitingHour = visitingHour;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVisitingHour() {
        return visitingHour;
    }

    public void setVisitingHour(String visitingHour) {
        this.visitingHour = visitingHour;
    }
}
