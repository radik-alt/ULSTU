package com.example.gameulstu.DB;

public class Model {

    private long id;
    private String date;
    private String scores;

    public Model (long id, String date, String scores) {
        this.id = id;
        this.date = date;
        this.scores = scores;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScores() {
        return scores;
    }

    public void setScores(String scores) {
        this.scores = scores;
    }

    @Override
    public String toString() {
        return this.date + " : " + this.scores;
    }
}
