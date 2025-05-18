/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.AavengersTrafficControle.trafficsystem.model.Motorcycle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohamedsoliman
 */
public class MotorcycleDAO extends VehicleDAO {
    public MotorcycleDAO() {
        super();
    }

    public Motorcycle findById(int motorcycleId) {
        String query = "SELECT v.*, m.* FROM vehicles v " +
                      "JOIN motorcycles m ON v.vehicle_id = m.vehicle_id " +
                      "WHERE v.vehicle_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, motorcycleId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractMotorcycleFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error finding motorcycle: " + e.getMessage());
        }
        return null;
    }

    public List<Motorcycle> findByMotorcycleType(String motorcycleType) {
        List<Motorcycle> motorcycles = new ArrayList<>();
        String query = "SELECT v.*, m.* FROM vehicles v " +
                      "JOIN motorcycles m ON v.vehicle_id = m.vehicle_id " +
                      "WHERE m.motorcycle_type = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, motorcycleType);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                motorcycles.add(extractMotorcycleFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error finding motorcycles: " + e.getMessage());
        }
        return motorcycles;
    }

    public boolean insert(Motorcycle motorcycle) {
        String vehicleQuery = "INSERT INTO vehicles (plate_number, registration_number, type, " +
            "make, model, year, color, fuel_type, engine_number, chassis_number, " +
            "seats, vehicle_status, registration_date, expiry_date, insurance_provider, " +
            "insurance_expiry, owner_id, current_location, notes) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String motorcycleQuery = "INSERT INTO motorcycles (vehicle_id, engine_capacity_cc, motorcycle_type, gearbox_type, cooling_system, has_abs, wheel_base_mm, seat_height_mm, fuel_tank_capacity, number_of_strokes, top_speed_kph) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement stmt = connection.prepareStatement(vehicleQuery, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, motorcycle.getPlateNumber());
                stmt.setString(2, motorcycle.getRegistrationNumber());
                stmt.setString(3, motorcycle.getType());
                stmt.setString(4, motorcycle.getMake());
                stmt.setString(5, motorcycle.getModel());
                stmt.setInt(6, motorcycle.getYear());
                stmt.setString(7, motorcycle.getColor());
                stmt.setString(8, motorcycle.getFuelType());
                stmt.setString(9, motorcycle.getEngineNumber());
                stmt.setString(10, motorcycle.getChassisNumber());
                stmt.setInt(11, motorcycle.getSeats());
                stmt.setString(12, motorcycle.getVehicleStatus());
                stmt.setDate(13, new java.sql.Date(motorcycle.getRegistrationDate().getTime()));
                stmt.setDate(14, new java.sql.Date(motorcycle.getExpiryDate().getTime()));
                stmt.setString(15, motorcycle.getInsuranceProvider());
                stmt.setDate(16, new java.sql.Date(motorcycle.getInsuranceExpiry().getTime()));
                stmt.setInt(17, motorcycle.getOwnerId());
                stmt.setString(18, motorcycle.getCurrentLocation());
                stmt.setString(19, motorcycle.getNotes());
                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating vehicle failed, no rows affected.");
                }
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int vehicleId = generatedKeys.getInt(1);
                        try (PreparedStatement mStmt = connection.prepareStatement(motorcycleQuery)) {
                            mStmt.setInt(1, vehicleId);
                            mStmt.setInt(2, motorcycle.getEngineCapacityCc());
                            mStmt.setString(3, motorcycle.getMotorcycleType());
                            mStmt.setString(4, motorcycle.getGearboxType());
                            mStmt.setString(5, motorcycle.getCoolingSystem());
                            mStmt.setBoolean(6, motorcycle.hasAbs());
                            mStmt.setInt(7, motorcycle.getWheelBaseMm());
                            mStmt.setInt(8, motorcycle.getSeatHeightMm());
                            mStmt.setDouble(9, motorcycle.getFuelTankCapacity());
                            mStmt.setInt(10, motorcycle.getNumberOfStrokes());
                            mStmt.setInt(11, motorcycle.getTopSpeedKph());
                            mStmt.executeUpdate();
                        }
                    } else {
                        throw new SQLException("Creating vehicle failed, no ID obtained.");
                    }
                }
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            try { connection.rollback(); } catch (SQLException ex) { System.out.println("Error rolling back transaction: " + ex.getMessage()); }
            System.out.println("Error inserting motorcycle: " + e.getMessage());
            return false;
        } finally {
            try { connection.setAutoCommit(true); } catch (SQLException e) { System.out.println("Error resetting auto-commit: " + e.getMessage()); }
        }
    }

    public boolean update(Motorcycle motorcycle) {
        String vehicleQuery = "UPDATE vehicles SET plate_number=?, registration_number=?, type=?, make=?, model=?, year=?, color=?, fuel_type=?, engine_number=?, chassis_number=?, seats=?, vehicle_status=?, registration_date=?, expiry_date=?, insurance_provider=?, insurance_expiry=?, owner_id=?, current_location=?, notes=? WHERE vehicle_id=?";
        String motorcycleQuery = "UPDATE motorcycles SET engine_capacity_cc=?, motorcycle_type=?, gearbox_type=?, cooling_system=?, has_abs=?, wheel_base_mm=?, seat_height_mm=?, fuel_tank_capacity=?, number_of_strokes=?, top_speed_kph=? WHERE vehicle_id=?";
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement stmt = connection.prepareStatement(vehicleQuery)) {
                stmt.setString(1, motorcycle.getPlateNumber());
                stmt.setString(2, motorcycle.getRegistrationNumber());
                stmt.setString(3, motorcycle.getType());
                stmt.setString(4, motorcycle.getMake());
                stmt.setString(5, motorcycle.getModel());
                stmt.setInt(6, motorcycle.getYear());
                stmt.setString(7, motorcycle.getColor());
                stmt.setString(8, motorcycle.getFuelType());
                stmt.setString(9, motorcycle.getEngineNumber());
                stmt.setString(10, motorcycle.getChassisNumber());
                stmt.setInt(11, motorcycle.getSeats());
                stmt.setString(12, motorcycle.getVehicleStatus());
                stmt.setDate(13, new java.sql.Date(motorcycle.getRegistrationDate().getTime()));
                stmt.setDate(14, new java.sql.Date(motorcycle.getExpiryDate().getTime()));
                stmt.setString(15, motorcycle.getInsuranceProvider());
                stmt.setDate(16, new java.sql.Date(motorcycle.getInsuranceExpiry().getTime()));
                stmt.setInt(17, motorcycle.getOwnerId());
                stmt.setString(18, motorcycle.getCurrentLocation());
                stmt.setString(19, motorcycle.getNotes());
                stmt.setInt(20, motorcycle.getVehicleId());
                stmt.executeUpdate();
            }
            try (PreparedStatement stmt = connection.prepareStatement(motorcycleQuery)) {
                stmt.setInt(1, motorcycle.getEngineCapacityCc());
                stmt.setString(2, motorcycle.getMotorcycleType());
                stmt.setString(3, motorcycle.getGearboxType());
                stmt.setString(4, motorcycle.getCoolingSystem());
                stmt.setBoolean(5, motorcycle.hasAbs());
                stmt.setInt(6, motorcycle.getWheelBaseMm());
                stmt.setInt(7, motorcycle.getSeatHeightMm());
                stmt.setDouble(8, motorcycle.getFuelTankCapacity());
                stmt.setInt(9, motorcycle.getNumberOfStrokes());
                stmt.setInt(10, motorcycle.getTopSpeedKph());
                stmt.setInt(11, motorcycle.getVehicleId());
                stmt.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            try { connection.rollback(); } catch (SQLException ex) { System.out.println("Error rolling back transaction: " + ex.getMessage()); }
            System.out.println("Error updating motorcycle: " + e.getMessage());
            return false;
        } finally {
            try { connection.setAutoCommit(true); } catch (SQLException e) { System.out.println("Error resetting auto-commit: " + e.getMessage()); }
        }
    }

    public boolean delete(int motorcycleId) {
        String query = "DELETE FROM vehicles WHERE vehicle_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, motorcycleId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting motorcycle: " + e.getMessage());
            return false;
        }
    }

    private Motorcycle extractMotorcycleFromResultSet(ResultSet rs) throws SQLException {
        return new Motorcycle(
            rs.getInt("vehicle_id"),
            rs.getString("plate_number"),
            rs.getString("registration_number"),
            rs.getString("type"),
            rs.getString("make"),
            rs.getString("model"),
            rs.getInt("year"),
            rs.getString("color"),
            rs.getString("fuel_type"),
            rs.getString("engine_number"),
            rs.getString("chassis_number"),
            rs.getInt("seats"),
            rs.getString("vehicle_status"),
            rs.getDate("registration_date"),
            rs.getDate("expiry_date"),
            rs.getString("insurance_provider"),
            rs.getDate("insurance_expiry"),
            rs.getInt("owner_id"),
            rs.getString("current_location"),
            rs.getString("notes"),
            rs.getInt("engine_capacity_cc"),
            rs.getString("motorcycle_type"),
            rs.getString("gearbox_type"),
            rs.getString("cooling_system"),
            rs.getBoolean("has_abs"),
            rs.getInt("wheel_base_mm"),
            rs.getInt("seat_height_mm"),
            rs.getDouble("fuel_tank_capacity"),
            rs.getInt("number_of_strokes"),
            rs.getInt("top_speed_kph")
        );
    }
}
