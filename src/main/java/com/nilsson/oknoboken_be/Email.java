package com.nilsson.oknoboken_be;

public class Email {
    private String name;
    private String address;
    private String message;

    public Email() {
    }

    public Email(String name, String address, String message) {
        this.name = name;
        this.address = address;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String email) {
        this.address = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
