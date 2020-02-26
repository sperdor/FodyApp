package com.fody.app;

import java.util.Date;

public class entityPersonalData {

    private String gender;
    private Integer age;
    private String weight,target,height;
    private String country,diet;
    private String phisycs,working;
    private String uid;
    private String waist,bust,highHip,hip;

    public entityPersonalData() {
    }

    public entityPersonalData(String uid
            ,String gender, Integer age, String weight, String target,String height,
                              String country, String diet, String phisycs,String working, String waist, String bust,String highHip,
                              String hip) {
        this.uid=uid;
        this.gender= gender;
        this.age= age;
        this.weight=weight;
        this.target=target;
        this.height=height;
        this.country=country;
        this.diet=diet;
        this.phisycs= phisycs;
        this.working = working;
        this.waist=waist;
        this.bust=bust;
        this.highHip=highHip;
        this.hip =hip;


    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getPhisycs() {
        return phisycs;
    }

    public void setPhisycs(String phisycs) {
        this.phisycs = phisycs;
    }

    public String getWorking() {
        return working;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getBust() {
        return bust;
    }

    public void setBust(String bust) {
        this.bust = bust;
    }

    public String getHighHip() {
        return highHip;
    }

    public void setHighHip(String highHip) {
        this.highHip = highHip;
    }

    public String getHip() {
        return hip;
    }

    public void setHip(String hip) {
        this.hip = hip;
    }
}
