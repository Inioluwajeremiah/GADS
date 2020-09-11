package com.example.gads.learning_leaders_frag;

public class LLData {

    public String img_url, name, country;
    public  String hour;

    public LLData( String name, String hour ,String country,String img_url) {
        this.img_url = img_url;
        this.name = name;
        this.country = country;
        this.hour = hour;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}

