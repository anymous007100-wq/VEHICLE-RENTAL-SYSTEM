package com.rental.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerId;
    private String name;
    private String licenseNo;
    private List<Booking> rentals;

    public Customer(String customerId, String name, String licenseNo) {
        this.customerId = customerId;
        this.name = name;
        this.licenseNo = licenseNo;
        this.rentals = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public List<Booking> getRentalHistory() {
        return new ArrayList<>(rentals);
    }

    public void addBooking(Booking booking) {
        rentals.add(booking);
    }

    @Override
    public String toString() {
        return String.format("Customer[ID: %s, Name: %s, License: %s]", customerId, name, licenseNo);
    }
}
