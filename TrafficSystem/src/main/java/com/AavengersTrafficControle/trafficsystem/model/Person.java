/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.AavengersTrafficControle.trafficsystem.model;

import java.util.Date;

/**
 *
 * @author mohamedsoliman
 */
public class Person {
    private int personId;
    private String firstName;
    private String lastName;
    private String nationalId;
    private Date dateOfBirth;
    private String address;
    private String phoneNumber;
    private String email;
    private String gender;
    private String bloodType;
    private String emergencyContact;
    private String emergencyPhone;

    public Person() {}

    public Person(int personId, String firstName, String lastName, String nationalId, 
                 Date dateOfBirth, String address, String phoneNumber, String email,
                 String gender, String bloodType, String emergencyContact, String emergencyPhone) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gender = gender;
        this.bloodType = bloodType;
        this.emergencyContact = emergencyContact;
        this.emergencyPhone = emergencyPhone;
    }

    // Getters and Setters
    public int getPersonId() { return personId; }
    public void setPersonId(int personId) { this.personId = personId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getNationalId() { return nationalId; }
    public void setNationalId(String nationalId) { this.nationalId = nationalId; }
    public Date getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getBloodType() { return bloodType; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }
    public String getEmergencyContact() { return emergencyContact; }
    public void setEmergencyContact(String emergencyContact) { this.emergencyContact = emergencyContact; }
    public String getEmergencyPhone() { return emergencyPhone; }
    public void setEmergencyPhone(String emergencyPhone) { this.emergencyPhone = emergencyPhone; }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", emergencyPhone='" + emergencyPhone + '\'' +
                '}';
    }
}
