package com.AavengersTrafficControle.trafficsystem.model;

import java.util.Date;

public class Car extends Vehicle {

    private int numberOfDoors;
    private String bodyStyle;
    private String transmission;
    private String driveType;
    private int trunkCapacityLiters;
    private boolean sunroof;
    private String infotainmentSystem;
    private int airbags;
    private boolean isLuxury;

    // Constructor
    public Car(int vehicleId, String plateNumber, String registrationNumber, String type,
               String make, String model, int year, String color, String fuelType,
               String engineNumber, String chassisNumber, int seats, String vehicleStatus,
               Date registrationDate, Date expiryDate, String insuranceProvider,
               Date insuranceExpiry, int ownerId, String currentLocation, String notes,
               int numberOfDoors, String bodyStyle, String transmission, String driveType,
               int trunkCapacityLiters, boolean sunroof, String infotainmentSystem,
               int airbags, boolean isLuxury) {

        super(vehicleId, plateNumber, registrationNumber, type, make, model, year, color,
              fuelType, engineNumber, chassisNumber, seats, vehicleStatus, registrationDate,
              expiryDate, insuranceProvider, insuranceExpiry, ownerId, currentLocation, notes);

        this.numberOfDoors = numberOfDoors;
        this.bodyStyle = bodyStyle;
        this.transmission = transmission;
        this.driveType = driveType;
        this.trunkCapacityLiters = trunkCapacityLiters;
        this.sunroof = sunroof;
        this.infotainmentSystem = infotainmentSystem;
        this.airbags = airbags;
        this.isLuxury = isLuxury;
    }

    // Getters and Setters
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public int getTrunkCapacityLiters() {
        return trunkCapacityLiters;
    }

    public void setTrunkCapacityLiters(int trunkCapacityLiters) {
        this.trunkCapacityLiters = trunkCapacityLiters;
    }

    public boolean hasSunroof() {
        return sunroof;
    }

    public void setSunroof(boolean sunroof) {
        this.sunroof = sunroof;
    }

    public String getInfotainmentSystem() {
        return infotainmentSystem;
    }

    public void setInfotainmentSystem(String infotainmentSystem) {
        this.infotainmentSystem = infotainmentSystem;
    }

    public int getAirbags() {
        return airbags;
    }

    public void setAirbags(int airbags) {
        this.airbags = airbags;
    }

    public boolean isLuxury() {
        return isLuxury;
    }

    public void setLuxury(boolean luxury) {
        isLuxury = luxury;
    }

    @Override
    public String toString() {
        return super.toString() +
               ", Number of Doors: " + numberOfDoors +
               ", Body Style: " + bodyStyle +
               ", Transmission: " + transmission +
               ", Drive Type: " + driveType +
               ", Trunk Capacity (L): " + trunkCapacityLiters +
               ", Sunroof: " + (sunroof ? "Yes" : "No") +
               ", Infotainment: " + infotainmentSystem +
               ", Airbags: " + airbags +
               ", Luxury: " + (isLuxury ? "Yes" : "No");
    }
}