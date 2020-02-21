package com.fody.app;

import java.util.List;

public class entityFood {

    String name;
    Double score;
    String id;
    Double calories;
    Double carbo,grassi,proteins;

    public entityFood() {
    }

    public entityFood(String name, Double score, String id, Double calories, Double carbo, Double grassi, Double proteins) {
        this.name = name;
        this.score = score;
        this.id = id;
        this.calories = calories;
        this.carbo = carbo;
        this.grassi = grassi;
        this.proteins = proteins;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Number getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Number getCarbo() {
        return carbo;
    }

    public void setCarbo(Double carbo) {
        this.carbo = carbo;
    }

    public Number getGrassi() {
        return grassi;
    }

    public void setGrassi(Double grassi) {
        this.grassi = grassi;
    }

    public Number getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }


}
