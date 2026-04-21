package com.rental;

import com.rental.model.*;
import com.rental.gui.SimpleRentalApp;

public class VehicleRentalSystemDemo {
    public static void main(String[] args) {
        Fleet fleet = new Fleet();

        Car car1 = new Car("CAR001", "Toyota", "Camry", 2022, 50.00, 4, "Automatic");
        Car car2 = new Car("CAR002", "Honda", "Civic", 2023, 45.00, 4, "Manual");
        Motorcycle moto1 = new Motorcycle("MOTO001", "Yamaha", "MT-07", 2022, 35.00, 689, "Sport", false);
        Motorcycle moto2 = new Motorcycle("MOTO002", "Harley", "Street 750", 2021, 60.00, 750, "Cruiser", true);

        fleet.addVehicle(car1);
        fleet.addVehicle(car2);
        fleet.addVehicle(moto1);
        fleet.addVehicle(moto2);

        SimpleRentalApp.launch(fleet);
    }
}
