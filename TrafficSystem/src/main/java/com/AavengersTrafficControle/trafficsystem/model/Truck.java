package com.AavengersTrafficControle.trafficsystem.model;

import java.util.Date;

public class Truck extends Vehicle {

    private String truckType;
    private int axleCount;
    private int maxPayloadKg;
    private int grossVehicleWeightKg;
    private boolean trailerAttached;
    private String trailerType;
    private double trailerLengthM;
    private String cargoTypeAllowed;
    private String companyName;
    private String registrationCountry;
    private Date inspectionDueDate;

    // Constructor
    public Truck(int vehicleId, String plateNumber, String registrationNumber, String type,
                 String make, String model, int year, String color, String fuelType,
                 String engineNumber, String chassisNumber, int seats, String vehicleStatus,
                 Date registrationDate, Date expiryDate, String insuranceProvider,
                 Date insuranceExpiry, int ownerId, String currentLocation, String notes,
                 String truckType, int axleCount, int maxPayloadKg, int grossVehicleWeightKg,
                 boolean trailerAttached, String trailerType, double trailerLengthM,
                 String cargoTypeAllowed, String companyName, String registrationCountry,
                 Date inspectionDueDate) {

        super(vehicleId, plateNumber, registrationNumber, type, make, model, year, color,
              fuelType, engineNumber, chassisNumber, seats, vehicleStatus, registrationDate,
              expiryDate, insuranceProvider, insuranceExpiry, ownerId, currentLocation, notes);

        this.truckType = truckType;
        this.axleCount = axleCount;
        this.maxPayloadKg = maxPayloadKg;
        this.grossVehicleWeightKg = grossVehicleWeightKg;
        this.trailerAttached = trailerAttached;
        this.trailerType = trailerType;
        this.trailerLengthM = trailerLengthM;
        this.cargoTypeAllowed = cargoTypeAllowed;
        this.companyName = companyName;
        this.registrationCountry = registrationCountry;
        this.inspectionDueDate = inspectionDueDate;
    }

    // Getters and Setters
    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public int getAxleCount() {
        return axleCount;
    }

    public void setAxleCount(int axleCount) {
        this.axleCount = axleCount;
    }

    public int getMaxPayloadKg() {
        return maxPayloadKg;
    }

    public void setMaxPayloadKg(int maxPayloadKg) {
        this.maxPayloadKg = maxPayloadKg;
    }

    public int getGrossVehicleWeightKg() {
        return grossVehicleWeightKg;
    }

    public void setGrossVehicleWeightKg(int grossVehicleWeightKg) {
        this.grossVehicleWeightKg = grossVehicleWeightKg;
    }

    public boolean isTrailerAttached() {
        return trailerAttached;
    }

    public void setTrailerAttached(boolean trailerAttached) {
        this.trailerAttached = trailerAttached;
    }

    public String getTrailerType() {
        return trailerType;
    }

    public void setTrailerType(String trailerType) {
        this.trailerType = trailerType;
    }

    public double getTrailerLengthM() {
        return trailerLengthM;
    }

    public void setTrailerLengthM(double trailerLengthM) {
        this.trailerLengthM = trailerLengthM;
    }

    public String getCargoTypeAllowed() {
        return cargoTypeAllowed;
    }

    public void setCargoTypeAllowed(String cargoTypeAllowed) {
        this.cargoTypeAllowed = cargoTypeAllowed;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegistrationCountry() {
        return registrationCountry;
    }

    public void setRegistrationCountry(String registrationCountry) {
        this.registrationCountry = registrationCountry;
    }

    public Date getInspectionDueDate() {
        return inspectionDueDate;
    }

    public void setInspectionDueDate(Date inspectionDueDate) {
        this.inspectionDueDate = inspectionDueDate;
    }

    @Override
    public String toString() {
        return super.toString() +
               ", Truck Type: " + truckType +
               ", Axles: " + axleCount +
               ", Max Payload (kg): " + maxPayloadKg +
               ", GVW (kg): " + grossVehicleWeightKg +
               ", Trailer Attached: " + (trailerAttached ? "Yes" : "No") +
               ", Trailer Type: " + trailerType +
               ", Trailer Length (m): " + trailerLengthM +
               ", Cargo Type Allowed: " + cargoTypeAllowed +
               ", Company Name: " + companyName +
               ", Registration Country: " + registrationCountry +
               ", Inspection Due: " + inspectionDueDate;
    }
}