package com.rental.gui;

import com.rental.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class SimpleRentalApp extends JFrame {
    // Instance variables for GUI components and data
    private Fleet fleet;
    private DefaultListModel<String> vehicleListModel;
    private JList<String> vehicleList;
    private Customer customer;

    // Constructor: Sets up the window and initializes components
    public SimpleRentalApp(Fleet fleet) {
        this.fleet = fleet;
        this.customer = new Customer("CUST001", "John Doe", "DL12345678");
        
        // Window Setup
        setTitle("Vehicle Rental System");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Add padding between regions

        // Initialize all UI components
        initComponents();
        
        // Populate the vehicle list with available vehicles
        refreshVehicleList();
    }

    // Creating UI Components: Sets up title, list, and buttons
    private void initComponents() {
        // Create the vehicle list model and JList
        vehicleListModel = new DefaultListModel<>();
        vehicleList = new JList<>(vehicleListModel);
        vehicleList.setFont(new Font("Arial", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(vehicleList);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        
        // Create title panel with dark blue background
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(44, 62, 80)); // Dark blue
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Add padding
        
        JLabel titleLabel = new JLabel("Available Vehicles", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);
        
        // Create buttons with distinct colors
        JButton rentButton = new JButton("Rent Vehicle");
        rentButton.setBackground(new Color(46, 204, 113)); // Green
        rentButton.setForeground(Color.WHITE);
        rentButton.setFocusPainted(false); // Remove focus border
        rentButton.setOpaque(true);
        
        JButton returnButton = new JButton("Return Vehicle");
        returnButton.setBackground(new Color(231, 76, 60)); // Red
        returnButton.setForeground(Color.WHITE);
        returnButton.setFocusPainted(false);
        returnButton.setOpaque(true);
        
        JButton refreshButton = new JButton("Refresh List");
        refreshButton.setBackground(new Color(52, 152, 219)); // Blue
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.setOpaque(true);

        // Button Logic: Add action listeners for each button
        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rentSelectedVehicle();
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnSelectedVehicle();
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshVehicleList();
            }
        });

        // Create button panel with light gray background and padding
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(236, 240, 241)); // Light gray
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15)); // Add spacing
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 15, 10)); // Add padding
        buttonPanel.add(rentButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(refreshButton);

        // Add components to the window using BorderLayout
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Refresh Vehicle List: Updates the list with current available vehicles
    private void refreshVehicleList() {
        vehicleListModel.clear();
        for (Vehicle v : fleet.getAvailable()) {
            vehicleListModel.addElement(v.toString());
        }
    }

    // Rent Selected Vehicle: Handles the rent button click
    private void rentSelectedVehicle() {
        int selectedIndex = vehicleList.getSelectedIndex();
        
        // Check if a vehicle is selected
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(this, "Please select a vehicle to rent.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get the selected vehicle
        Vehicle selectedVehicle = fleet.getAvailable().get(selectedIndex);
        
        // Ask for customer name using simple input dialog
        String customerName = JOptionPane.showInputDialog(this, "Enter Customer Name:", customer.getName());
        
        if (customerName != null && !customerName.trim().isEmpty()) {
            customer = new Customer("CUST001", customerName, "DL12345678");
            
            // Rent the vehicle using business logic from model class
            if (selectedVehicle.rent(customer)) {
                LocalDate startDate = LocalDate.now();
                LocalDate endDate = startDate.plusDays(5);
                Booking booking = new Booking("BK" + System.currentTimeMillis(), selectedVehicle, customer, startDate, endDate);
                customer.addBooking(booking);
                
                // Show success message
                JOptionPane.showMessageDialog(this, 
                    "Success! Vehicle rented.\n" +
                    "Vehicle: " + selectedVehicle.getVehicleId() + "\n" +
                    "Customer: " + customer.getName() + "\n" +
                    "Cost: $" + selectedVehicle.calculateRentalCost(5),
                    "Rental Success", JOptionPane.INFORMATION_MESSAGE);
                
                refreshVehicleList();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to rent vehicle.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Return Selected Vehicle: Handles the return button click
    private void returnSelectedVehicle() {
        // Ask for vehicle ID using simple input dialog
        String vehicleId = JOptionPane.showInputDialog(this, "Enter Vehicle ID to return (e.g., CAR001):");
        
        if (vehicleId != null && !vehicleId.trim().isEmpty()) {
            Vehicle vehicle = fleet.findById(vehicleId);
            
            // Check if vehicle exists and is currently rented
            if (vehicle != null && !vehicle.isAvailable()) {
                vehicle.returnVehicle();
                JOptionPane.showMessageDialog(this, 
                    "Success! Vehicle returned: " + vehicle.getVehicleId(),
                    "Return Success", JOptionPane.INFORMATION_MESSAGE);
                refreshVehicleList();
            } else if (vehicle != null && vehicle.isAvailable()) {
                JOptionPane.showMessageDialog(this, "This vehicle is already available.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Vehicle not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Launch Method: Starts the GUI on the Event Dispatch Thread
    public static void launch(Fleet fleet) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleRentalApp(fleet).setVisible(true);
            }
        });
    }
}
