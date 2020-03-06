package com.fody.app;

public class entityAnamnesis {

    Integer smoke;
    Integer disease;
    Integer surgery;
    Integer genetic;
    Integer drugs;
    String uid;
    String textSmoke, textDiseas,textSurgery,textGenetic,textDrugs;

    public entityAnamnesis(Integer smoke, Integer disease, Integer surgery, Integer genetic, Integer drugs, String uid, String textSmoke, String textDiseas, String textSurgery, String textGenetic, String textDrugs) {
        this.smoke = smoke;
        this.disease = disease;
        this.surgery = surgery;
        this.genetic = genetic;
        this.drugs = drugs;
        this.uid = uid;
        this.textSmoke = textSmoke;
        this.textDiseas = textDiseas;
        this.textSurgery = textSurgery;
        this.textGenetic = textGenetic;
        this.textDrugs = textDrugs;
    }

    public entityAnamnesis() {


    }

    public Integer getSmoke() {
        return smoke;
    }

    public void setSmoke(Integer smoke) {
        this.smoke = smoke;
    }

    public Integer getDisease() {
        return disease;
    }

    public void setDisease(Integer disease) {
        this.disease = disease;
    }

    public Integer getSurgery() {
        return surgery;
    }

    public void setSurgery(Integer surgery) {
        this.surgery = surgery;
    }

    public Integer getGenetic() {
        return genetic;
    }

    public void setGenetic(Integer genetic) {
        this.genetic = genetic;
    }

    public Integer getDrugs() {
        return drugs;
    }

    public void setDrugs(Integer drugs) {
        this.drugs = drugs;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTextSmoke() {
        return textSmoke;
    }

    public void setTextSmoke(String textSmoke) {
        this.textSmoke = textSmoke;
    }

    public String getTextDiseas() {
        return textDiseas;
    }

    public void setTextDiseas(String textDiseas) {
        this.textDiseas = textDiseas;
    }

    public String getTextSurgery() {
        return textSurgery;
    }

    public void setTextSurgery(String textSurgery) {
        this.textSurgery = textSurgery;
    }

    public String getTextGenetic() {
        return textGenetic;
    }

    public void setTextGenetic(String textGenetic) {
        this.textGenetic = textGenetic;
    }

    public String getTextDrugs() {
        return textDrugs;
    }

    public void setTextDrugs(String textDrugs) {
        this.textDrugs = textDrugs;
    }
}
