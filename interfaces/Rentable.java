package com.rental.interfaces;

import com.rental.model.Customer;

public interface Rentable {
    boolean rent(Customer customer);
    void returnVehicle();
    boolean isAvailable();
}
