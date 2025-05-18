/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.AavengersTrafficControle.trafficsystem.model.Truck;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohamedsoliman
 */
public class TruckDAO extends VehicleDAO {
    public TruckDAO() {
        super();
    }

    public Truck findById(int truckId) {
        String query = "SELECT v.*, t.* FROM vehicles v " +
                      "JOIN trucks t ON v.vehicle_id = t.vehicle_id " +
                      "WHERE v.vehicle_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, truckId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractTruckFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error finding truck: " + e.getMessage());
        }
        return null;
    }

    public List<Truck> findByTruckType(String truckType) {
        List<Truck> trucks = new ArrayList<>();
        String query = "SELECT v.*, t.* FROM vehicles v " +
                      "JOIN trucks t ON v.vehicle_id = t.vehicle_id " +
                      "WHERE t.truck_type = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, truckType);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                trucks.add(extractTruckFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error finding trucks: " + e.getMessage());
        }
        return trucks;
    }

    public boolean insert(Truck truck) {
        String vehicleQuery = "INSERT INTO vehicles (plate_number, registration_number, type, " +
            "make, model, year, color, fuel_type, engine_number, chassis_number, " +
            "seats, vehicle_status, registration_date, expiry_date, insurance_provider, " +
            "insurance_expiry, owner_id, current_location, notes) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String truckQuery = "INSERT INTO trucks (vehicle_id, truck_type, axle_count, max_payload_kg, gross_vehicle_weight_kg, trailer_attached, trailer_type, trailer_length_m, cargo_type_allowed, company_name, registration_country, inspection_due_date) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement stmt = connection.prepareStatement(vehicleQuery, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, truck.getPlateNumber());
                stmt.setString(2, truck.getRegistrationNumber());
                stmt.setString(3, truck.getType());
                stmt.setString(4, truck.getMake());
                stmt.setString(5, truck.getModel());
                stmt.setInt(6, truck.getYear());
                stmt.setString(7, truck.getColor());
                stmt.setString(8, truck.getFuelType());
                stmt.setString(9, truck.getEngineNumber());
                stmt.setString(10, truck.getChassisNumber());
                stmt.setInt(11, truck.getSeats());
                stmt.setString(12, truck.getVehicleStatus());
                stmt.setDate(13, new java.sql.Date(truck.getRegistrationDate().getTime()));
                stmt.setDate(14, new java.sql.Date(truck.getExpiryDate().getTime()));
                stmt.setString(15, truck.getInsuranceProvider());
                stmt.setDate(16, new java.sql.Date(truck.getInsuranceExpiry().getTime()));
                stmt.setInt(17, truck.getOwnerId());
                stmt.setString(18, truck.getCurrentLocation());
                stmt.setString(19, truck.getNotes());
                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating vehicle failed, no rows affected.");
                }
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int vehicleId = generatedKeys.getInt(1);
                        try (PreparedStatement tStmt = connection.prepareStatement(truckQuery)) {
                            tStmt.setInt(1, vehicleId);
                            tStmt.setString(2, truck.getTruckType());
                            tStmt.setInt(3, truck.getAxleCount());
                            tStmt.setInt(4, truck.getMaxPayloadKg());
                            tStmt.setInt(5, truck.getGrossVehicleWeightKg());
                            tStmt.setBoolean(6, truck.isTrailerAttached());
                            tStmt.setString(7, truck.getTrailerType());
                            tStmt.setDouble(8, truck.getTrailerLengthM());
                            tStmt.setString(9, truck.getCargoTypeAllowed());
                            tStmt.setString(10, truck.getCompanyName());
                            tStmt.setString(11, truck.getRegistrationCountry());
                            tStmt.setDate(12, new java.sql.Date(truck.getInspectionDueDate().getTime()));
                            tStmt.executeUpdate();
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
            System.out.println("Error inserting truck: " + e.getMessage());
            return false;
        } finally {
            try { connection.setAutoCommit(true); } catch (SQLException e) { System.out.println("Error resetting auto-commit: " + e.getMessage()); }
        }
    }

    public boolean update(Truck truck) {
        String vehicleQuery = "UPDATE vehicles SET plate_number=?, registration_number=?, type=?, make=?, model=?, year=?, color=?, fuel_type=?, engine_number=?, chassis_number=?, seats=?, vehicle_status=?, registration_date=?, expiry_date=?, insurance_provider=?, insurance_expiry=?, owner_id=?, current_location=?, notes=? WHERE vehicle_id=?";
        String truckQuery = "UPDATE trucks SET truck_type=?, axle_count=?, max_payload_kg=?, gross_vehicle_weight_kg=?, trailer_attached=?, trailer_type=?, trailer_length_m=?, cargo_type_allowed=?, company_name=?, registration_country=?, inspection_due_date=? WHERE vehicle_id=?";
        try {
            connection.setAutoCommit(false);
            try (PreparedStatement stmt = connection.prepareStatement(vehicleQuery)) {
                stmt.setString(1, truck.getPlateNumber());
                stmt.setString(2, truck.getRegistrationNumber());
                stmt.setString(3, truck.getType());
                stmt.setString(4, truck.getMake());
                stmt.setString(5, truck.getModel());
                stmt.setInt(6, truck.getYear());
                stmt.setString(7, truck.getColor());
                stmt.setString(8, truck.getFuelType());
                stmt.setString(9, truck.getEngineNumber());
                stmt.setString(10, truck.getChassisNumber());
                stmt.setInt(11, truck.getSeats());
                stmt.setString(12, truck.getVehicleStatus());
                stmt.setDate(13, new java.sql.Date(truck.getRegistrationDate().getTime()));
                stmt.setDate(14, new java.sql.Date(truck.getExpiryDate().getTime()));
                stmt.setString(15, truck.getInsuranceProvider());
                stmt.setDate(16, new java.sql.Date(truck.getInsuranceExpiry().getTime()));
                stmt.setInt(17, truck.getOwnerId());
                stmt.setString(18, truck.getCurrentLocation());
                stmt.setString(19, truck.getNotes());
                stmt.setInt(20, truck.getVehicleId());
                stmt.executeUpdate();
            }
            try (PreparedStatement stmt = connection.prepareStatement(truckQuery)) {
                stmt.setString(1, truck.getTruckType());
                stmt.setInt(2, truck.getAxleCount());
                stmt.setInt(3, truck.getMaxPayloadKg());
                stmt.setInt(4, truck.getGrossVehicleWeightKg());
                stmt.setBoolean(5, truck.isTrailerAttached());
                stmt.setString(6, truck.getTrailerType());
                stmt.setDouble(7, truck.getTrailerLengthM());
                stmt.setString(8, truck.getCargoTypeAllowed());
                stmt.setString(9, truck.getCompanyName());
                stmt.setString(10, truck.getRegistrationCountry());
                stmt.setDate(11, new java.sql.Date(truck.getInspectionDueDate().getTime()));
                stmt.setInt(12, truck.getVehicleId());
                stmt.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            try { connection.rollback(); } catch (SQLException ex) { System.out.println("Error rolling back transaction: " + ex.getMessage()); }
            System.out.println("Error updating truck: " + e.getMessage());
            return false;
        } finally {
            try { connection.setAutoCommit(true); } catch (SQLException e) { System.out.println("Error resetting auto-commit: " + e.getMessage()); }
        }
    }

    public boolean delete(int truckId) {
        String query = "DELETE FROM vehicles WHERE vehicle_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, truckId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting truck: " + e.getMessage());
            return false;
        }
    }

    private Truck extractTruckFromResultSet(ResultSet rs) throws SQLException {
        return new Truck(
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
            rs.getString("truck_type"),
            rs.getInt("axle_count"),
            rs.getInt("max_payload_kg"),
            rs.getInt("gross_vehicle_weight_kg"),
            rs.getBoolean("trailer_attached"),
            rs.getString("trailer_type"),
            rs.getDouble("trailer_length_m"),
            rs.getString("cargo_type_allowed"),
            rs.getString("company_name"),
            rs.getString("registration_country"),
            rs.getDate("inspection_due_date")
        );
    }
}
