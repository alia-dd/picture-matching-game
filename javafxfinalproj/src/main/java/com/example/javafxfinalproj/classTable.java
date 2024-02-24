package com.example.javafxfinalproj;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class classTable {

    private final StringProperty id;
    private final StringProperty level;
    private final StringProperty score;


    public classTable() {

        id = new SimpleStringProperty(this, "id");
        level = new SimpleStringProperty(this, "level");
        score = new SimpleStringProperty(this, "score");

    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getLevel() {
        return level.get();
    }

    public StringProperty levelProperty() {
        return level;
    }

    public String getScore() {
        return score.get();
    }

    public StringProperty scoreProperty() {
        return score;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setLevel(String level) {
        this.level.set(level);
    }

    public void setScore(String score) {
        this.score.set(score);
    }




}