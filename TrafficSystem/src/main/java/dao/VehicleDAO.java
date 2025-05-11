/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.AavengersTrafficControle.trafficsystem.model.Vehicle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohamedsoliman
 */
public class VehicleDAO {
    protected Connection connection;
    
    public VehicleDAO() {
        this.connection = DatabaseConnection.getConnection();
    }
    
    public Vehicle findById(int vehicleId) {
        String query = "SELECT * FROM vehicles WHERE vehicle_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, vehicleId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extractVehicleFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error finding vehicle: " + e.getMessage());
        }
        return null;
    }
    
    public List<Vehicle> findByPlateNumber(String plateNumber) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE plate_number LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + plateNumber + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                vehicles.add(extractVehicleFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error finding vehicles: " + e.getMessage());
        }
        return vehicles;
    }
    
    public List<Vehicle> findByType(String type) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE type = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, type);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                vehicles.add(extractVehicleFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error finding vehicles: " + e.getMessage());
        }
        return vehicles;
    }
    
    protected Vehicle extractVehicleFromResultSet(ResultSet rs) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(rs.getInt("vehicle_id"));
        vehicle.setPlateNumber(rs.getString("plate_number"));
        vehicle.setRegistrationNumber(rs.getString("registration_number"));
        vehicle.setType(rs.getString("type"));
        vehicle.setMake(rs.getString("make"));
        vehicle.setModel(rs.getString("model"));
        vehicle.setYear(rs.getInt("year"));
        vehicle.setColor(rs.getString("color"));
        vehicle.setFuelType(rs.getString("fuel_type"));
        vehicle.setEngineNumber(rs.getString("engine_number"));
        vehicle.setChassisNumber(rs.getString("chassis_number"));
        vehicle.setSeats(rs.getInt("seats"));
        vehicle.setVehicleStatus(rs.getString("vehicle_status"));
        vehicle.setRegistrationDate(rs.getDate("registration_date"));
        vehicle.setExpiryDate(rs.getDate("expiry_date"));
        vehicle.setInsuranceProvider(rs.getString("insurance_provider"));
        vehicle.setInsuranceExpiry(rs.getDate("insurance_expiry"));
        vehicle.setOwnerId(rs.getInt("owner_id"));
        vehicle.setCurrentLocation(rs.getString("current_location"));
        vehicle.setNotes(rs.getString("notes"));
        return vehicle;
    }
}
