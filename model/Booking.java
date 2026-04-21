package com.rental.model;

import java.time.LocalDate;

public class Booking {
    private String bookingId;
    private Vehicle vehicle;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;

    public Booking(String bookingId, Vehicle vehicle, Customer customer, LocalDate startDate, LocalDate endDate) {
        this.bookingId = bookingId;
        this.vehicle = vehicle;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = calculateTotal();
    }

    public String getBookingId() {
        return bookingId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double calculateTotal() {
        int days = getDuration();
        return vehicle.calculateRentalCost(days);
    }

    public int getDuration() {
        return (int) java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
    }

    @Override
    public String toString() {
        return String.format("Booking[ID: %s, Vehicle: %s, Customer: %s, Period: %s to %s, Cost: $%.2f]",
                bookingId, vehicle.getVehicleId(), customer.getName(), startDate, endDate, totalCost);
    }
}
