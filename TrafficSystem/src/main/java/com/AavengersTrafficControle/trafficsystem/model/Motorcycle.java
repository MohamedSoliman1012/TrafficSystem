package com.AavengersTrafficControle.trafficsystem.model;

// Yo, this is the Motorcycle class, for all the bike stuff in the system
public class Motorcycle extends Vehicle {

    private int engineCapacityCc;
    private String motorcycleType;
    private String gearboxType;
    private String coolingSystem;
    private boolean hasAbs;
    private int wheelBaseMm;
    private int seatHeightMm;
    private double fuelTankCapacity;
    private int numberOfStrokes;
    private int topSpeedKph;

    // Constructor (just sets up the motorcycle, nothing fancy)
    public Motorcycle(int vehicleId, String plateNumber, String registrationNumber, String type,
                      String make, String model, int year, String color, String fuelType,
                      String engineNumber, String chassisNumber, int seats, String vehicleStatus,
                      java.util.Date registrationDate, java.util.Date expiryDate, String insuranceProvider,
                      java.util.Date insuranceExpiry, int ownerId, String currentLocation, String notes,
                      int engineCapacityCc, String motorcycleType, String gearboxType, String coolingSystem,
                      boolean hasAbs, int wheelBaseMm, int seatHeightMm, double fuelTankCapacity,
                      int numberOfStrokes, int topSpeedKph) {

        // Call parent constructor
        super(vehicleId, plateNumber, registrationNumber, type, make, model, year, color, fuelType,
              engineNumber, chassisNumber, seats, vehicleStatus, registrationDate, expiryDate,
              insuranceProvider, insuranceExpiry, ownerId, currentLocation, notes);

        // Motorcycle-specific fields
        this.engineCapacityCc = engineCapacityCc;
        this.motorcycleType = motorcycleType;
        this.gearboxType = gearboxType;
        this.coolingSystem = coolingSystem;
        this.hasAbs = hasAbs;
        this.wheelBaseMm = wheelBaseMm;
        this.seatHeightMm = seatHeightMm;
        this.fuelTankCapacity = fuelTankCapacity;
        this.numberOfStrokes = numberOfStrokes;
        this.topSpeedKph = topSpeedKph;
    }

    // Getters and Setters (just the usual, nothing wild)
    public int getEngineCapacityCc() {
        return engineCapacityCc;
    }

    public void setEngineCapacityCc(int engineCapacityCc) {
        this.engineCapacityCc = engineCapacityCc;
    }

    public String getMotorcycleType() {
        return motorcycleType;
    }

    public void setMotorcycleType(String motorcycleType) {
        this.motorcycleType = motorcycleType;
    }

    public String getGearboxType() {
        return gearboxType;
    }

    public void setGearboxType(String gearboxType) {
        this.gearboxType = gearboxType;
    }

    public String getCoolingSystem() {
        return coolingSystem;
    }

    public void setCoolingSystem(String coolingSystem) {
        this.coolingSystem = coolingSystem;
    }

    public boolean hasAbs() {
        return hasAbs;
    }

    public void setHasAbs(boolean hasAbs) {
        this.hasAbs = hasAbs;
    }

    public int getWheelBaseMm() {
        return wheelBaseMm;
    }

    public void setWheelBaseMm(int wheelBaseMm) {
        this.wheelBaseMm = wheelBaseMm;
    }

    public int getSeatHeightMm() {
        return seatHeightMm;
    }

    public void setSeatHeightMm(int seatHeightMm) {
        this.seatHeightMm = seatHeightMm;
    }

    public double getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(double fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public int getNumberOfStrokes() {
        return numberOfStrokes;
    }

    public void setNumberOfStrokes(int numberOfStrokes) {
        this.numberOfStrokes = numberOfStrokes;
    }

    public int getTopSpeedKph() {
        return topSpeedKph;
    }

    public void setTopSpeedKph(int topSpeedKph) {
        this.topSpeedKph = topSpeedKph;
    }

    // Override toString to add more info
    @Override
    public String toString() {
        // This just prints out all the motorcycle info, kinda long but whatever
        return super.toString() + 
               ", Motorcycle Type: " + motorcycleType +
               ", Engine CC: " + engineCapacityCc +
               ", Gearbox: " + gearboxType +
               ", ABS: " + (hasAbs ? "Yes" : "No") +
               ", Top Speed: " + topSpeedKph + " kph";
    }
}