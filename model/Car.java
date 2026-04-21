package com.rental.model;

public class Car extends Vehicle {
    private int numDoors;
    private String transmission;

    public Car(String vehicleId, String make, String model, int year, double dailyRate, 
               int numDoors, String transmission) {
        super(vehicleId, make, model, year, dailyRate);
        this.numDoors = numDoors;
        this.transmission = transmission;
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }

    @Override
    public double calculateRentalCost(int numberOfDays) {
        return dailyRate * numberOfDays;
    }

    public int getNumDoors() {
        return numDoors;
    }

    public String getTransmission() {
        return transmission;
    }

    @Override
    public String toString() {
        return String.format("%s - %d doors, %s transmission", 
                super.toString(), numDoors, transmission);
    }
}
