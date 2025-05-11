package com.AavengersTrafficControle.trafficsystem.model;

import java.util.Date;

public class Bus extends Vehicle {

    private String busType;
    private int seatingCapacity;
    private int standingCapacity;
    private boolean isAccessible;
    private String assignedRoute;
    private String routeType;
    private String operatorCompany;
    private Date inspectionDueDate;
    private boolean hasCCTV;
    private String ticketingSystemType;
    private boolean airConditioned;

    // Constructor
    public Bus(int vehicleId, String plateNumber, String registrationNumber, String type,
               String make, String model, int year, String color, String fuelType,
               String engineNumber, String chassisNumber, int seats, String vehicleStatus,
               Date registrationDate, Date expiryDate, String insuranceProvider,
               Date insuranceExpiry, int ownerId, String currentLocation, String notes,
               String busType, int seatingCapacity, int standingCapacity, boolean isAccessible,
               String assignedRoute, String routeType, String operatorCompany,
               Date inspectionDueDate, boolean hasCCTV, String ticketingSystemType,
               boolean airConditioned) {

        super(vehicleId, plateNumber, registrationNumber, type, make, model, year, color,
              fuelType, engineNumber, chassisNumber, seats, vehicleStatus, registrationDate,
              expiryDate, insuranceProvider, insuranceExpiry, ownerId, currentLocation, notes);

        this.busType = busType;
        this.seatingCapacity = seatingCapacity;
        this.standingCapacity = standingCapacity;
        this.isAccessible = isAccessible;
        this.assignedRoute = assignedRoute;
        this.routeType = routeType;
        this.operatorCompany = operatorCompany;
        this.inspectionDueDate = inspectionDueDate;
        this.hasCCTV = hasCCTV;
        this.ticketingSystemType = ticketingSystemType;
        this.airConditioned = airConditioned;
    }

    // Getters and Setters
    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public int getStandingCapacity() {
        return standingCapacity;
    }

    public void setStandingCapacity(int standingCapacity) {
        this.standingCapacity = standingCapacity;
    }

    public boolean isAccessible() {
        return isAccessible;
    }

    public void setAccessible(boolean accessible) {
        isAccessible = accessible;
    }

    public String getAssignedRoute() {
        return assignedRoute;
    }

    public void setAssignedRoute(String assignedRoute) {
        this.assignedRoute = assignedRoute;
    }

    public String getRouteType() {
        return routeType;
    }

    public void setRouteType(String routeType) {
        this.routeType = routeType;
    }

    public String getOperatorCompany() {
        return operatorCompany;
    }

    public void setOperatorCompany(String operatorCompany) {
        this.operatorCompany = operatorCompany;
    }

    public Date getInspectionDueDate() {
        return inspectionDueDate;
    }

    public void setInspectionDueDate(Date inspectionDueDate) {
        this.inspectionDueDate = inspectionDueDate;
    }

    public boolean isHasCCTV() {
        return hasCCTV;
    }

    public void setHasCCTV(boolean hasCCTV) {
        this.hasCCTV = hasCCTV;
    }

    public String getTicketingSystemType() {
        return ticketingSystemType;
    }

    public void setTicketingSystemType(String ticketingSystemType) {
        this.ticketingSystemType = ticketingSystemType;
    }

    public boolean isAirConditioned() {
        return airConditioned;
    }

    public void setAirConditioned(boolean airConditioned) {
        this.airConditioned = airConditioned;
    }

    @Override
    public String toString() {
        return super.toString() +
               ", Bus Type: " + busType +
               ", Seating Capacity: " + seatingCapacity +
               ", Standing Capacity: " + standingCapacity +
               ", Accessible: " + (isAccessible ? "Yes" : "No") +
               ", Assigned Route: " + assignedRoute +
               ", Route Type: " + routeType +
               ", Operator Company: " + operatorCompany +
               ", Inspection Due: " + inspectionDueDate +
               ", Has CCTV: " + (hasCCTV ? "Yes" : "No") +
               ", Ticketing System: " + ticketingSystemType +
               ", Air Conditioned: " + (airConditioned ? "Yes" : "No");
    }
}