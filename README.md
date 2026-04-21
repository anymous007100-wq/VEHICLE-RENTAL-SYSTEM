# VEHICLE-RENTAL-SYSTEM
🚗 Vehicle Rental System (OOP Project)
A clean, Java-based system designed to demonstrate core Object-Oriented Programming (OOP) principles through a real-world car rental scenario.

🎯 Quick Project Overview
This project manages a fleet of vehicles (Cars and Motorcycles). It allows a user to:

View a list of available vehicles.

Rent a vehicle for a specific number of days.

Calculate the cost based on the vehicle type.

Return the vehicle to the fleet.

🛠️ The "Big Five" OOP Concepts
If your lecturer asks "How did you use OOP?", use these exact points:

Inheritance: I created a parent class Vehicle. Both Car and Motorcycle inherit from it, so I don't have to rewrite code for things like "Model" or "Year."

Polymorphism: Each vehicle calculates its price differently. I use one method name calculateRentalCost(), but Car and Motorcycle provide their own specific logic.

Abstraction: The Vehicle class is abstract. You can't rent a "generic" vehicle; it must be a specific Car or Motorcycle.

Encapsulation: I kept all data (like price and ID) private. Access is only allowed through Getters and Setters to protect the data.

Interfaces: I used a Rentable interface. This acts as a "contract" that ensures any new vehicle type added to the system must have rent and return functionality.

📂 Simplified Project Structure
      Package,      Component  ,   Description
🛡️ Interfaces,    Rentable.java,  The Contract: Defines essential rental behaviors.
🏗️ Model,Vehicle.java,The Parent: Abstract base class for all vehicles.
🚗 Model,Car.java,Subclass: Concrete implementation of a car.
🏍️ Model,Motorcycle.java,Subclass: Concrete implementation of a motorcycle.
👤 Model,Customer.java,Entity: Stores user and rental history data.
🏢 Model,Fleet.java,"Manager: The ""Garage"" that stores the collection."
💻 GUI,SimpleRentalApp.java,View: The User Interface for the system.
🚀 Root,VehicleRentalSystemDemo.java,Main: The entry point to start the application.
