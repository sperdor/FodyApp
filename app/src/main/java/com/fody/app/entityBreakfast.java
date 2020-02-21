package com.fody.app;

import java.sql.Timestamp;
import java.util.Date;

public class entityBreakfast {

    private String food;
    private Date date;
    private String uid;
    private Integer quantity;
    private String nameFood;
    private Double score;


    public entityBreakfast() {

    }

    public entityBreakfast(String food, Date date, String uid, Integer quantity, String nameFood, Double score) {
        this.food = food;
        this.date = date;
        this.uid = uid;
        this.quantity= quantity;
        this.nameFood =nameFood;
        this.score = score;

    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
