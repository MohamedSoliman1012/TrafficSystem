package com.AavengersTrafficControle.trafficsystem.app;

import com.AavengersTrafficControle.trafficsystem.model.*;

public class Main {
    public static void main(String[] args) {
        // Create a new Vehicle object
        Vehicle myVehicle = new Vehicle();

        // Print the vehicle info
        System.out.println("Vehicle created:");
        System.out.println(myVehicle);

        // Modify some details
        myVehicle.setColor("Black");
        myVehicle.setYear(2021);

        // Print updated info
        System.out.println("\nAfter updates:");
        System.out.println("Color: " + myVehicle.getColor());
        System.out.println("Year: " + myVehicle.getYear());
    }
}