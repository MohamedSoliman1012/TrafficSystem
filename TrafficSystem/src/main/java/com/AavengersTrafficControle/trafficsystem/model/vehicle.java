
package com.AavengersTrafficControle.trafficsystem.model;

public class Vehicle {
    private int id;
    private String plateNumber;
    private String model;
    private String color;
    private int year;

    // Constructor
    public Vehicle(int id, String plateNumber, String model, String color, int year) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.model = model;
        this.color = color;
        this.year = year;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public int getYear() {
        return year;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // ToString (for printing)
    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", plateNumber='" + plateNumber + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", year=" + year +
                '}';
    }
}