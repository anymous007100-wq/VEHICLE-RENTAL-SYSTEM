package com.rental.model;

import com.rental.interfaces.Rentable;

public abstract class Vehicle implements Rentable {
    private String vehicleId;
    private String make;
    private String model;
    private int year;
    protected double dailyRate;
    protected boolean available;

    public Vehicle(String vehicleId, String make, String model, int year, double dailyRate) {
        this.vehicleId = vehicleId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.dailyRate = dailyRate;
        this.available = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public abstract String getVehicleType();
    public abstract double calculateRentalCost(int numberOfDays);

    @Override
    public boolean rent(Customer customer) {
        if (available && customer != null) {
            available = false;
            return true;
        }
        return false;
    }

    @Override
    public void returnVehicle() {
        available = true;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d (%s) - $%.2f/day", make, model, year, getVehicleType(), dailyRate);
    }
}
