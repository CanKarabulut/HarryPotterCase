package com.cankarabulut.task.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
//hepsini verme benim istediklerimi ver

@JsonIgnoreProperties(ignoreUnknown = true)
public class Film {

    private String imdbID;
    private String Title;
    private int Year;
    private String Type;
    private String Released;

    public String getImdbID() {
        return imdbID;
    }

    @JsonProperty("imdbID") //büyüktür küçüktür hikayesi
    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return Title;
    }

    @JsonProperty("Title")
    public void setTitle(String title) {
        Title = title;
    }

    public int getYear() {
        return Year;
    }

    @JsonProperty("Year")
    public void setYear(int year) {
        Year = year;
    }

    public String getType() {
        return Type;
    }

    @JsonProperty("Type")
    public void setType(String type) {
        Type = type;
    }

    @JsonProperty("Released")
    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        Released = released;
    }
}
