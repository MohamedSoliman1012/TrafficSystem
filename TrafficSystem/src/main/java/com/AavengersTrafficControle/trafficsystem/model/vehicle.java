package com.AavengersTrafficControle.trafficsystem.model;

import java.util.Date;

public class Vehicle {
    private int vehicleId; // serial
    private String plateNumber;
    private String registrationNumber;
    private String type;
    private String make;
    private String model;
    private int year;
    private String color;
    private String fuelType;
    private String engineNumber;
    private String chassisNumber;
    private int seats;
    private String vehicleStatus;
    private Date registrationDate;
    private Date expiryDate;
    private String insuranceProvider;
    private Date insuranceExpiry;
    private int ownerId;
    private String currentLocation;
    private String notes;
    private String qrCode;

    // Constructor
    public Vehicle(int vehicleId, String plateNumber, String registrationNumber, String type, String make,
                   String model, int year, String color, String fuelType, String engineNumber, String chassisNumber,
                   int seats, String vehicleStatus, Date registrationDate, Date expiryDate,
                   String insuranceProvider, Date insuranceExpiry, int ownerId, String currentLocation, String notes) {
        this.vehicleId = vehicleId;
        this.plateNumber = plateNumber;
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.fuelType = fuelType;
        this.engineNumber = engineNumber;
        this.chassisNumber = chassisNumber;
        this.seats = seats;
        this.vehicleStatus = vehicleStatus;
        this.registrationDate = registrationDate;
        this.expiryDate = expiryDate;
        this.insuranceProvider = insuranceProvider;
        this.insuranceExpiry = insuranceExpiry;
        this.ownerId = ownerId;
        this.currentLocation = currentLocation;
        this.notes = notes;
    }

    // Default constructor
    public Vehicle() {}

    // Getters and setters
    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(String vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    public Date getInsuranceExpiry() {
        return insuranceExpiry;
    }

    public void setInsuranceExpiry(Date insuranceExpiry) {
        this.insuranceExpiry = insuranceExpiry;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", plateNumber='" + plateNumber + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", type='" + type + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", engineNumber='" + engineNumber + '\'' +
                ", chassisNumber='" + chassisNumber + '\'' +
                ", seats=" + seats +
                ", vehicleStatus='" + vehicleStatus + '\'' +
                ", registrationDate=" + registrationDate +
                ", expiryDate=" + expiryDate +
                ", insuranceProvider='" + insuranceProvider + '\'' +
                ", insuranceExpiry=" + insuranceExpiry +
                ", ownerId=" + ownerId +
                ", currentLocation='" + currentLocation + '\'' +
                ", notes='" + notes + '\'' +
                ", qrCode='" + qrCode + '\'' +
                '}';
    }
}