package com.nishatsultana.myehr.Model;

public class BP_Pojo {

    //String DateTime;
    long timeValue;
    long sysValue;
    long diasValue;
    long heartRateValue;


    public BP_Pojo(){

    }

    public BP_Pojo(long timeValue, long sysValue, long diasValue,long heartRateValue) {
        this.timeValue = timeValue;
        this.sysValue = sysValue;
        this.diasValue = diasValue;
        this.heartRateValue = heartRateValue;
    }



    public long getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(long timeValue) {
        this.timeValue = timeValue;
    }

    public long getSysValue() {
        return sysValue;
    }

    public void setSysValue(long sysValue) {
        this.sysValue = sysValue;
    }

    public long getDiasValue() {
        return diasValue;
    }

    public void setDiasValue(long diasValue) {
        this.diasValue = diasValue;
    }

    public long getHeartRateValue() {
        return heartRateValue;
    }

    public void setHeartRateValue(long heartRateValue) {
        this.heartRateValue = heartRateValue;
    }
}
