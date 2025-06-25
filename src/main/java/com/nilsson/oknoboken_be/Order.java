package com.nilsson.oknoboken_be;

public class Order {
    private String name;
    private String email;
    private String deliveryMethod;
    private String message;
    private String address;
    private String postalCode;
    private String city;
    private String phoneNumber;
    private String bookAmount;

    public Order() {
    }

    public Order(String name, String email, String deliveryMethod, String message, String address, String postalCode, String city, String phoneNumber, String bookAmount) {
        this.name = name;
        this.email = email;
        this.deliveryMethod = deliveryMethod;
        this.message = message;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.bookAmount = bookAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBookAmount() {
        return bookAmount;
    }

    public void setBookAmount(String bookAmount) {
        this.bookAmount = bookAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", message='" + message + '\'' +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bookAmount='" + bookAmount + '\'' +
                '}';
    }
}
