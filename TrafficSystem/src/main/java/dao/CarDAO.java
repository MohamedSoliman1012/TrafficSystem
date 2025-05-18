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
    
    public CarDAO() {
        super();
    }
    
    public Car findById(int carId) {
        String query = "SELECT v.*, c.* FROM vehicles v " +
                      "JOIN cars c ON v.vehicle_id = c.vehicle_id " +
                      "WHERE v.vehicle_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, carId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extractCarFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error finding car: " + e.getMessage());
        }
        return null;
    }
    
    public List<Car> findByBodyStyle(String bodyStyle) {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT v.*, c.* FROM vehicles v " +
                      "JOIN cars c ON v.vehicle_id = c.vehicle_id " +
                      "WHERE c.body_style = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, bodyStyle);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                cars.add(extractCarFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error finding cars: " + e.getMessage());
        }
        return cars;
    }
    
    public List<Car> findByTransmission(String transmission) {
        List<Car> cars = new ArrayList<>();
        String query = "SELECT v.*, c.* FROM vehicles v " +
                      "JOIN cars c ON v.vehicle_id = c.vehicle_id " +
                      "WHERE c.transmission = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, transmission);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                cars.add(extractCarFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error finding cars: " + e.getMessage());
        }
        return cars;
    }
    
    public boolean insert(Car car) {
        String vehicleQuery = "INSERT INTO vehicles (plate_number, registration_number, type, " +
                            "make, model, year, color, fuel_type, engine_number, chassis_number, " +
                            "seats, vehicle_status, registration_date, expiry_date, insurance_provider, " +
                            "insurance_expiry, owner_id, current_location, notes) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        String carQuery = "INSERT INTO cars (vehicle_id, number_of_doors, body_style, transmission, " +
                         "drive_type, trunk_capacity_liters, sunroof, infotainment_system, airbags, is_luxury) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            connection.setAutoCommit(false);
            
            // Insert into vehicles table
            try (PreparedStatement stmt = connection.prepareStatement(vehicleQuery, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, car.getPlateNumber());
                stmt.setString(2, car.getRegistrationNumber());
                stmt.setString(3, car.getType());
                stmt.setString(4, car.getMake());
                stmt.setString(5, car.getModel());
                stmt.setInt(6, car.getYear());
                stmt.setString(7, car.getColor());
                stmt.setString(8, car.getFuelType());
                stmt.setString(9, car.getEngineNumber());
                stmt.setString(10, car.getChassisNumber());
                stmt.setInt(11, car.getSeats());
                stmt.setString(12, car.getVehicleStatus());
                stmt.setDate(13, new java.sql.Date(car.getRegistrationDate().getTime()));
                stmt.setDate(14, new java.sql.Date(car.getExpiryDate().getTime()));
                stmt.setString(15, car.getInsuranceProvider());
                stmt.setDate(16, new java.sql.Date(car.getInsuranceExpiry().getTime()));
                stmt.setInt(17, car.getOwnerId());
                stmt.setString(18, car.getCurrentLocation());
                stmt.setString(19, car.getNotes());
                
                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating vehicle failed, no rows affected.");
                }
                
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int vehicleId = generatedKeys.getInt(1);
                        
                        // Insert into cars table
                        try (PreparedStatement carStmt = connection.prepareStatement(carQuery)) {
                            carStmt.setInt(1, vehicleId);
                            carStmt.setInt(2, car.getNumberOfDoors());
                            carStmt.setString(3, car.getBodyStyle());
                            carStmt.setString(4, car.getTransmission());
                            carStmt.setString(5, car.getDriveType());
                            carStmt.setInt(6, car.getTrunkCapacityLiters());
                            carStmt.setBoolean(7, car.hasSunroof());
                            carStmt.setString(8, car.getInfotainmentSystem());
                            carStmt.setInt(9, car.getAirbags());
                            carStmt.setBoolean(10, car.isLuxury());
                            
                            carStmt.executeUpdate();
                        }
                    } else {
                        throw new SQLException("Creating vehicle failed, no ID obtained.");
                    }
                }
            }
            
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Error rolling back transaction: " + ex.getMessage());
            }
            System.out.println("Error inserting car: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }
    
    public boolean update(Car car) {
        String vehicleQuery = "UPDATE vehicles SET plate_number=?, registration_number=?, type=?, " +
                            "make=?, model=?, year=?, color=?, fuel_type=?, engine_number=?, " +
                            "chassis_number=?, seats=?, vehicle_status=?, registration_date=?, " +
                            "expiry_date=?, insurance_provider=?, insurance_expiry=?, owner_id=?, " +
                            "current_location=?, notes=? WHERE vehicle_id=?";
        
        String carQuery = "UPDATE cars SET number_of_doors=?, body_style=?, transmission=?, " +
                         "drive_type=?, trunk_capacity_liters=?, sunroof=?, infotainment_system=?, " +
                         "airbags=?, is_luxury=? WHERE vehicle_id=?";
        
        try {
            connection.setAutoCommit(false);
            
            // Update vehicles table
            try (PreparedStatement stmt = connection.prepareStatement(vehicleQuery)) {
                stmt.setString(1, car.getPlateNumber());
                stmt.setString(2, car.getRegistrationNumber());
                stmt.setString(3, car.getType());
                stmt.setString(4, car.getMake());
                stmt.setString(5, car.getModel());
                stmt.setInt(6, car.getYear());
                stmt.setString(7, car.getColor());
                stmt.setString(8, car.getFuelType());
                stmt.setString(9, car.getEngineNumber());
                stmt.setString(10, car.getChassisNumber());
                stmt.setInt(11, car.getSeats());
                stmt.setString(12, car.getVehicleStatus());
                stmt.setDate(13, new java.sql.Date(car.getRegistrationDate().getTime()));
                stmt.setDate(14, new java.sql.Date(car.getExpiryDate().getTime()));
                stmt.setString(15, car.getInsuranceProvider());
                stmt.setDate(16, new java.sql.Date(car.getInsuranceExpiry().getTime()));
                stmt.setInt(17, car.getOwnerId());
                stmt.setString(18, car.getCurrentLocation());
                stmt.setString(19, car.getNotes());
                stmt.setInt(20, car.getVehicleId());
                
                stmt.executeUpdate();
            }
            
            // Update cars table
            try (PreparedStatement stmt = connection.prepareStatement(carQuery)) {
                stmt.setInt(1, car.getNumberOfDoors());
                stmt.setString(2, car.getBodyStyle());
                stmt.setString(3, car.getTransmission());
                stmt.setString(4, car.getDriveType());
                stmt.setInt(5, car.getTrunkCapacityLiters());
                stmt.setBoolean(6, car.hasSunroof());
                stmt.setString(7, car.getInfotainmentSystem());
                stmt.setInt(8, car.getAirbags());
                stmt.setBoolean(9, car.isLuxury());
                stmt.setInt(10, car.getVehicleId());
                
                stmt.executeUpdate();
            }
            
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Error rolling back transaction: " + ex.getMessage());
            }
            System.out.println("Error updating car: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }
    
    public boolean delete(int carId) {
        String query = "DELETE FROM vehicles WHERE vehicle_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, carId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting car: " + e.getMessage());
            return false;
        }
    }
    
    private Car extractCarFromResultSet(ResultSet rs) throws SQLException {
        Car car = new Car();
        // Set vehicle properties
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
