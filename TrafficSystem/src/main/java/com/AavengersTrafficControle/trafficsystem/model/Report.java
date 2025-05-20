package com.AavengersTrafficControle.trafficsystem.model;

import java.util.Date;

public class Report {
    private int reportId;
    private int policeId;
    private int driverId;
    private int vehicleId;
    private Date reportDate;
    private String violationType;
    private String location;
    private String description;
    private double fine;
    private int pointsDeducted;
    private String status;
    private String evidence;
    private String witnessStatement;
    private Date dueDate;
    private boolean isPaid;
    private String reportType; // New field for report type

    public Report() {}

    public Report(int reportId, int policeId, int driverId, int vehicleId,
                 Date reportDate, String violationType, String location,
                 String description, double fine, int pointsDeducted,
                 String status, String evidence, String witnessStatement,
                 Date dueDate, boolean isPaid) {
        this.reportId = reportId;
        this.policeId = policeId;
        this.driverId = driverId;
        this.vehicleId = vehicleId;
        this.reportDate = reportDate;
        this.violationType = violationType;
        this.location = location;
        this.description = description;
        this.fine = fine;
        this.pointsDeducted = pointsDeducted;
        this.status = status;
        this.evidence = evidence;
        this.witnessStatement = witnessStatement;
        this.dueDate = dueDate;
        this.isPaid = isPaid;
    }

    // Getters and Setters
    public int getReportId() { return reportId; }
    public void setReportId(int reportId) { this.reportId = reportId; }
    public int getPoliceId() { return policeId; }
    public void setPoliceId(int policeId) { this.policeId = policeId; }
    public int getDriverId() { return driverId; }
    public void setDriverId(int driverId) { this.driverId = driverId; }
    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }
    public Date getReportDate() { return reportDate; }
    public void setReportDate(Date reportDate) { this.reportDate = reportDate; }
    public String getViolationType() { return violationType; }
    public void setViolationType(String violationType) { this.violationType = violationType; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getFine() { return fine; }
    public void setFine(double fine) { this.fine = fine; }
    public int getPointsDeducted() { return pointsDeducted; }
    public void setPointsDeducted(int pointsDeducted) { this.pointsDeducted = pointsDeducted; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getEvidence() { return evidence; }
    public void setEvidence(String evidence) { this.evidence = evidence; }
    public String getWitnessStatement() { return witnessStatement; }
    public void setWitnessStatement(String witnessStatement) { this.witnessStatement = witnessStatement; }
    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    public boolean isPaid() { return isPaid; }
    public void setPaid(boolean paid) { isPaid = paid; }
    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", policeId=" + policeId +
                ", driverId=" + driverId +
                ", vehicleId=" + vehicleId +
                ", reportDate=" + reportDate +
                ", violationType='" + violationType + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", fine=" + fine +
                ", pointsDeducted=" + pointsDeducted +
                ", status='" + status + '\'' +
                ", evidence='" + evidence + '\'' +
                ", witnessStatement='" + witnessStatement + '\'' +
                ", dueDate=" + dueDate +
                ", isPaid=" + isPaid +
                ", reportType='" + reportType + '\'' +
                '}';
    }
}