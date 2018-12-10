package com.example.anuj4.birdwatching_to626;

import java.util.Date;

public class Bird {

    String birdname, zipcode, date, user;
    int importance;

    public Bird(String birdname, String zipcode, String date, String user, int importance) {
        this.birdname = birdname;
        this.zipcode = zipcode;
        this.date = date;
        this.user = user;
        this.importance = importance;
    }

    public Bird() {
    }
}


