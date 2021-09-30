package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    private String title;

    @JsonProperty("woeid")
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Location{" +
                "title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
