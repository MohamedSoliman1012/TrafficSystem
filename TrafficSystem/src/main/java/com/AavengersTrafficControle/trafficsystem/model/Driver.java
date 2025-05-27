package com.AavengersTrafficControle.trafficsystem.model;

import java.util.Date;

// Yo, this is the Driver class, handles all the driver stuff for the system
public class Driver extends Person {
    private String licenseNumber;
    private Date licenseIssueDate;
    private Date licenseExpiryDate;
    private String licenseType;
    private int points;
    private String status;
    private String restrictions;
    private int totalViolations;

    // Constructor (just sets up the driver, nothing special)
    public Driver() {
        super();
    }

    public Driver(int personId, String firstName, String lastName, String nationalId,
                 Date dateOfBirth, String address, String phoneNumber, String email,
                 String gender, String bloodType, String emergencyContact, String emergencyPhone,
                 String licenseNumber, Date licenseIssueDate, Date licenseExpiryDate,
                 String licenseType, int points, String status, String restrictions,
                 int totalViolations) {
        super(personId, firstName, lastName, nationalId, dateOfBirth, address, phoneNumber,
              email, gender, bloodType, emergencyContact, emergencyPhone);
        this.licenseNumber = licenseNumber;
        this.licenseIssueDate = licenseIssueDate;
        this.licenseExpiryDate = licenseExpiryDate;
        this.licenseType = licenseType;
        this.points = points;
        this.status = status;
        this.restrictions = restrictions;
        this.totalViolations = totalViolations;
    }

    // Getters and Setters (just the usual, nothing wild)
    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public Date getLicenseIssueDate() { return licenseIssueDate; }
    public void setLicenseIssueDate(Date licenseIssueDate) { this.licenseIssueDate = licenseIssueDate; }
    public Date getLicenseExpiryDate() { return licenseExpiryDate; }
    public void setLicenseExpiryDate(Date licenseExpiryDate) { this.licenseExpiryDate = licenseExpiryDate; }
    public String getLicenseType() { return licenseType; }
    public void setLicenseType(String licenseType) { this.licenseType = licenseType; }
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getRestrictions() { return restrictions; }
    public void setRestrictions(String restrictions) { this.restrictions = restrictions; }
    public int getTotalViolations() { return totalViolations; }
    public void setTotalViolations(int totalViolations) { this.totalViolations = totalViolations; }

    @Override
    public String toString() {
        // This just prints out all the driver info, kinda long but whatever
        return super.toString() +
                ", License Number: " + licenseNumber +
                ", License Issue Date: " + licenseIssueDate +
                ", License Expiry Date: " + licenseExpiryDate +
                ", License Type: " + licenseType +
                ", Points: " + points +
                ", Status: " + status +
                ", Restrictions: " + restrictions +
                ", Total Violations: " + totalViolations;
    }
}
