package com.fody.app;

import java.util.Date;

public class entityPersonalData {

    private String Gender;
    private Integer Age;
    private String Weight,Target,Height;
    private String Country,Diet;
    private String Phisycs,Working;
    private String uid;
    private String Waist,Bust,HighHip,Hip;

    public entityPersonalData() {
    }

    public entityPersonalData(String uid
            ,String gender, Integer age, String weight, String target,String height,
                              String country, String diet, String phisycs,String working, String waist, String bust,String highHip,
                              String hip) {
        this.uid=uid;
        this.Gender= gender;
        this.Age= age;
        this.Weight=weight;
        this.Target=target;
        this.Height=height;
        this.Country=country;
        this.Diet=diet;
        this.Phisycs= phisycs;
        this.Working = working;
        this.Waist=waist;
        this.Bust=bust;
        this.HighHip=highHip;
        this.Hip =hip;


    }

    public String getUid() {
        return uid;
    }

    public String getGender() {
        return Gender;
    }

    public Integer getAge() {
        return Age;
    }

    public String getWeight() {
        return Weight;
    }

    public String getTarget() {
        return Target;
    }

    public String getHeight() {
        return Height;
    }

    public String getCountry() {
        return Country;
    }

    public String getDiet() {
        return Diet;
    }

    public String getPhisycs() {
        return Phisycs;
    }

    public String getWorking() {
        return Working;
    }

    public String getWaist() {
        return Waist;
    }

    public String getBust() {
        return Bust;
    }

    public String getHighHip() {
        return HighHip;
    }

    public String getHip() {
        return Hip;
    }
}
