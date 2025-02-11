package com.nilsson.oknoboken_be;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Subscription {
    private String email;
    private String date;

    public Subscription() {
    }

    public Subscription(String email) {
        this.email = email;
    }

    public Subscription(String email, String date) {
        this.email = email;
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
