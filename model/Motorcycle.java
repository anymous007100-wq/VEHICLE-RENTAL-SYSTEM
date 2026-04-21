package com.rental.model;

public class Motorcycle extends Vehicle {
    private int engineCC;
    private String bikeType;
    private boolean hasSidecar;

    public Motorcycle(String vehicleId, String make, String model, int year, double dailyRate,
                      int engineCC, String bikeType, boolean hasSidecar) {
        super(vehicleId, make, model, year, dailyRate);
        this.engineCC = engineCC;
        this.bikeType = bikeType;
        this.hasSidecar = hasSidecar;
    }

    @Override
    public String getVehicleType() {
        return "Motorcycle";
    }

    @Override
    public double calculateRentalCost(int numberOfDays) {
        return dailyRate * numberOfDays;
    }

    public int getEngineCC() {
        return engineCC;
    }

    public String getBikeType() {
        return bikeType;
    }

    public boolean hasSidecar() {
        return hasSidecar;
    }

    @Override
    public String toString() {
        return String.format("%s - %dcc %s%s",
                super.toString(), engineCC, bikeType, hasSidecar ? " with sidecar" : "");
    }
}
