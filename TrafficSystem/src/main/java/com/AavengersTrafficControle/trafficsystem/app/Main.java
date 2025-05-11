package com.AavengersTrafficControle.trafficsystem.app;

import com.AavengersTrafficControle.trafficsystem.model.*;
import dao.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleDAO vehicleDAO = new VehicleDAO();
        CarDAO carDAO = new CarDAO();
        
        while (true) {
            System.out.println("\n=== Traffic System Menu ===");
            System.out.println("1. Search vehicle by ID");
            System.out.println("2. Search vehicle by plate number");
            System.out.println("3. Search vehicles by type");
            System.out.println("4. List all cars");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter vehicle ID: ");
                    int id = scanner.nextInt();
                    Vehicle vehicle = vehicleDAO.findById(id);
                    if (vehicle != null) {
                        System.out.println("\nVehicle found:");
                        System.out.println(vehicle);
                    } else {
                        System.out.println("Vehicle not found.");
                    }
                    break;
                    
                case 2:
                    System.out.print("Enter plate number: ");
                    String plateNumber = scanner.nextLine();
                    List<Vehicle> vehiclesByPlate = vehicleDAO.findByPlateNumber(plateNumber);
                    if (!vehiclesByPlate.isEmpty()) {
                        System.out.println("\nVehicles found:");
                        for (Vehicle v : vehiclesByPlate) {
                            System.out.println(v);
                        }
                    } else {
                        System.out.println("No vehicles found with that plate number.");
                    }
                    break;
                    
                case 3:
                    System.out.print("Enter vehicle type (Car/Motorcycle/Bus): ");
                    String type = scanner.nextLine();
                    List<Vehicle> vehiclesByType = vehicleDAO.findByType(type);
                    if (!vehiclesByType.isEmpty()) {
                        System.out.println("\nVehicles found:");
                        for (Vehicle v : vehiclesByType) {
                            System.out.println(v);
                        }
                    } else {
                        System.out.println("No vehicles found of that type.");
                    }
                    break;
                    
                case 4:
                    List<Car> cars = carDAO.findAllCars();
                    if (!cars.isEmpty()) {
                        System.out.println("\nAll cars:");
                        for (Car car : cars) {
                            System.out.println(car);
                        }
                    } else {
                        System.out.println("No cars found in the database.");
                    }
                    break;
                    
                case 5:
                    System.out.println("Exiting program...");
                    DatabaseConnection.closeConnection();
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}