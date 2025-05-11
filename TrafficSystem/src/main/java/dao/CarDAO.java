/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.AavengersTrafficControle.trafficsystem.model.Car;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohamedsoliman
 */
public class CarDAO extends VehicleDAO {
    
    public Car findCarById(int vehicleId) {
        String query = "SELECT v.*, c.* FROM vehicles v " +
                      "JOIN cars c ON v.vehicle_id = c.vehicle_id " +
                      "WHERE v.vehicle_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extractCarFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error finding car: " + e.getMessage());
        }
        return null;
    }
    
    public List<Car> findAllCars() {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT v.*, c.* FROM vehicles v " +
                      "JOIN cars c ON v.vehicle_id = c.vehicle_id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                cars.add(extractCarFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error finding cars: " + e.getMessage());
        }
        return cars;
    }
    
    private Car extractCarFromResultSet(ResultSet rs) throws SQLException {
        Car car = new Car();
        // Set base vehicle properties
        car.setVehicleId(rs.getInt("vehicle_id"));
        car.setPlateNumber(rs.getString("plate_number"));
        car.setRegistrationNumber(rs.getString("registration_number"));
        car.setType(rs.getString("type"));
        car.setMake(rs.getString("make"));
        car.setModel(rs.getString("model"));
        car.setYear(rs.getInt("year"));
        car.setColor(rs.getString("color"));
        car.setFuelType(rs.getString("fuel_type"));
        car.setEngineNumber(rs.getString("engine_number"));
        car.setChassisNumber(rs.getString("chassis_number"));
        car.setSeats(rs.getInt("seats"));
        car.setVehicleStatus(rs.getString("vehicle_status"));
        car.setRegistrationDate(rs.getDate("registration_date"));
        car.setExpiryDate(rs.getDate("expiry_date"));
        car.setInsuranceProvider(rs.getString("insurance_provider"));
        car.setInsuranceExpiry(rs.getDate("insurance_expiry"));
        car.setOwnerId(rs.getInt("owner_id"));
        car.setCurrentLocation(rs.getString("current_location"));
        car.setNotes(rs.getString("notes"));
        
        // Set car-specific properties
        car.setNumberOfDoors(rs.getInt("number_of_doors"));
        car.setBodyStyle(rs.getString("body_style"));
        car.setTransmission(rs.getString("transmission"));
        car.setDriveType(rs.getString("drive_type"));
        car.setTrunkCapacityLiters(rs.getInt("trunk_capacity_liters"));
        car.setSunroof(rs.getBoolean("sunroof"));
        car.setInfotainmentSystem(rs.getString("infotainment_system"));
        car.setAirbags(rs.getInt("airbags"));
        car.setLuxury(rs.getBoolean("is_luxury"));
        
        return car;
    }
}
