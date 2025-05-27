package com.AavengersTrafficControle.trafficsystem.model;

import java.util.Date;

public class Police extends Person {
    private int badgeNumber;
    private String rank;
    private String department;
    private String username;
    private String password;
    private Date joinDate;
    private String status;
    private String specialization;
    private int rankLevel;

    public Police() {
        super();
    }

    public Police(int personId, String firstName, String lastName, String nationalId,
                 Date dateOfBirth, String address, String phoneNumber, String email,
                 String gender, String bloodType, String emergencyContact, String emergencyPhone,
                 int badgeNumber, String rank, String department, String username,
                 String password, Date joinDate, String status, String specialization,
                 int rankLevel) {
        super(personId, firstName, lastName, nationalId, dateOfBirth, address, phoneNumber,
              email, gender, bloodType, emergencyContact, emergencyPhone);
        this.badgeNumber = badgeNumber;
        this.rank = rank;
        this.department = department;
        this.username = username;
        this.password = password;
        this.joinDate = joinDate;
        this.status = status;
        this.specialization = specialization;
        this.rankLevel = rankLevel;
    }

    // Getters and Setters
    public int getBadgeNumber() { return badgeNumber; }
    public void setBadgeNumber(int badgeNumber) { this.badgeNumber = badgeNumber; }
    public String getRank() { return rank; }
    public void setRank(String rank) { this.rank = rank; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Date getJoinDate() { return joinDate; }
    public void setJoinDate(Date joinDate) { this.joinDate = joinDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public int getRankLevel() {
        return rankLevel;
    }
    public void setRankLevel(int rankLevel) {
        this.rankLevel = rankLevel;
    }

    // Get the user's role as a string (admin/search)
    public String getRole() {
        // Let's say rankLevel 1 is admin, others are search
        if (this.rankLevel == 1) return "admin";
        return "search";
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Badge Number: " + badgeNumber +
                ", Rank: " + rank +
                ", Department: " + department +
                ", Username: " + username +
                ", Join Date: " + joinDate +
                ", Status: " + status +
                ", Specialization: " + specialization +
                ", Rank Level: " + rankLevel;
    }
}