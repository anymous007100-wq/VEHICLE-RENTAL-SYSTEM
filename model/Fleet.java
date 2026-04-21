package com.rental.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fleet {
    private List<Vehicle> vehicles;

    public Fleet() {
        this.vehicles = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void removeVehicle(String vehicleId) {
        vehicles.removeIf(v -> v.getVehicleId().equals(vehicleId));
    }

    public List<Vehicle> getAvailable() {
        return vehicles.stream()
                .filter(Vehicle::isAvailable)
                .collect(Collectors.toList());
    }

    public Vehicle findById(String id) {
        return vehicles.stream()
                .filter(v -> v.getVehicleId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehicles);
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        return vehicles.stream()
                .filter(v -> v.getVehicleType().equals(vehicleType))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("Fleet[Total Vehicles: %d, Available: %d]", 
                vehicles.size(), getAvailable().size());
    }
}
