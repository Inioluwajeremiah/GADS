package com.example.gads;

public class IQdata {

    String name, country, image_link;
    String score;
    public IQdata(String name,String score, String country, String image_link) {
        this.name = name;
        this.country = country;
        this.image_link = image_link;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
