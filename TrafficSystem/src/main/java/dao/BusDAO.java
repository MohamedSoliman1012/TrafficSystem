package dao;

import com.AavengersTrafficControle.trafficsystem.model.Bus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Yo, this is the BusDAO, deals with bus stuff in the DB
public class BusDAO extends VehicleDAO {
    
    public BusDAO() {
        super();
    }
    
    public Bus findById(int busId) {
        String query = "SELECT v.*, b.* FROM vehicles v " +
                      "JOIN buses b ON v.vehicle_id = b.vehicle_id " +
                      "WHERE v.vehicle_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, busId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extractBusFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error finding bus: " + e.getMessage());
        }
        return null;
    }
    
    public List<Bus> findByBusType(String busType) {
        List<Bus> buses = new ArrayList<>();
        String query = "SELECT v.*, b.* FROM vehicles v " +
                      "JOIN buses b ON v.vehicle_id = b.vehicle_id " +
                      "WHERE b.bus_type = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, busType);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                buses.add(extractBusFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error finding buses: " + e.getMessage());
        }
        return buses;
    }
    
    public List<Bus> findByRoute(String route) {
        List<Bus> buses = new ArrayList<>();
        String query = "SELECT v.*, b.* FROM vehicles v " +
                      "JOIN buses b ON v.vehicle_id = b.vehicle_id " +
                      "WHERE b.assigned_route = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, route);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                buses.add(extractBusFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error finding buses: " + e.getMessage());
        }
        return buses;
    }
    
    public List<Bus> findByOperatorCompany(String company) {
        List<Bus> buses = new ArrayList<>();
        String query = "SELECT v.*, b.* FROM vehicles v " +
                      "JOIN buses b ON v.vehicle_id = b.vehicle_id " +
                      "WHERE b.operator_company = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, company);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                buses.add(extractBusFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error finding buses: " + e.getMessage());
        }
        return buses;
    }
    
    public boolean insert(Bus bus) {
        String vehicleQuery = "INSERT INTO vehicles (plate_number, registration_number, type, " +
                            "make, model, year, color, fuel_type, engine_number, chassis_number, " +
                            "seats, vehicle_status, registration_date, expiry_date, insurance_provider, " +
                            "insurance_expiry, owner_id, current_location, notes) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        String busQuery = "INSERT INTO buses (vehicle_id, bus_type, seating_capacity, standing_capacity, " +
                         "is_accessible, assigned_route, route_type, operator_company, inspection_due_date, " +
                         "has_cctv, ticketing_system_type, air_conditioned) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            connection.setAutoCommit(false);
            
            // Insert into vehicles table
            try (PreparedStatement stmt = connection.prepareStatement(vehicleQuery, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, bus.getPlateNumber());
                stmt.setString(2, bus.getRegistrationNumber());
                stmt.setString(3, bus.getType());
                stmt.setString(4, bus.getMake());
                stmt.setString(5, bus.getModel());
                stmt.setInt(6, bus.getYear());
                stmt.setString(7, bus.getColor());
                stmt.setString(8, bus.getFuelType());
                stmt.setString(9, bus.getEngineNumber());
                stmt.setString(10, bus.getChassisNumber());
                stmt.setInt(11, bus.getSeats());
                stmt.setString(12, bus.getVehicleStatus());
                stmt.setDate(13, new java.sql.Date(bus.getRegistrationDate().getTime()));
                stmt.setDate(14, new java.sql.Date(bus.getExpiryDate().getTime()));
                stmt.setString(15, bus.getInsuranceProvider());
                stmt.setDate(16, new java.sql.Date(bus.getInsuranceExpiry().getTime()));
                stmt.setInt(17, bus.getOwnerId());
                stmt.setString(18, bus.getCurrentLocation());
                stmt.setString(19, bus.getNotes());
                
                int affectedRows = stmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating vehicle failed, no rows affected.");
                }
                
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int vehicleId = generatedKeys.getInt(1);
                        
                        // Insert into buses table
                        try (PreparedStatement busStmt = connection.prepareStatement(busQuery)) {
                            busStmt.setInt(1, vehicleId);
                            busStmt.setString(2, bus.getBusType());
                            busStmt.setInt(3, bus.getSeatingCapacity());
                            busStmt.setInt(4, bus.getStandingCapacity());
                            busStmt.setBoolean(5, bus.isAccessible());
                            busStmt.setString(6, bus.getAssignedRoute());
                            busStmt.setString(7, bus.getRouteType());
                            busStmt.setString(8, bus.getOperatorCompany());
                            busStmt.setDate(9, new java.sql.Date(bus.getInspectionDueDate().getTime()));
                            busStmt.setBoolean(10, bus.isHasCCTV());
                            busStmt.setString(11, bus.getTicketingSystemType());
                            busStmt.setBoolean(12, bus.isAirConditioned());
                            
                            busStmt.executeUpdate();
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
            System.out.println("Error inserting bus: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }
    
    public boolean update(Bus bus) {
        String vehicleQuery = "UPDATE vehicles SET plate_number=?, registration_number=?, type=?, " +
                            "make=?, model=?, year=?, color=?, fuel_type=?, engine_number=?, " +
                            "chassis_number=?, seats=?, vehicle_status=?, registration_date=?, " +
                            "expiry_date=?, insurance_provider=?, insurance_expiry=?, owner_id=?, " +
                            "current_location=?, notes=? WHERE vehicle_id=?";
        
        String busQuery = "UPDATE buses SET bus_type=?, seating_capacity=?, standing_capacity=?, " +
                         "is_accessible=?, assigned_route=?, route_type=?, operator_company=?, " +
                         "inspection_due_date=?, has_cctv=?, ticketing_system_type=?, air_conditioned=? " +
                         "WHERE vehicle_id=?";
        
        try {
            connection.setAutoCommit(false);
            
            // Update vehicles table
            try (PreparedStatement stmt = connection.prepareStatement(vehicleQuery)) {
                stmt.setString(1, bus.getPlateNumber());
                stmt.setString(2, bus.getRegistrationNumber());
                stmt.setString(3, bus.getType());
                stmt.setString(4, bus.getMake());
                stmt.setString(5, bus.getModel());
                stmt.setInt(6, bus.getYear());
                stmt.setString(7, bus.getColor());
                stmt.setString(8, bus.getFuelType());
                stmt.setString(9, bus.getEngineNumber());
                stmt.setString(10, bus.getChassisNumber());
                stmt.setInt(11, bus.getSeats());
                stmt.setString(12, bus.getVehicleStatus());
                stmt.setDate(13, new java.sql.Date(bus.getRegistrationDate().getTime()));
                stmt.setDate(14, new java.sql.Date(bus.getExpiryDate().getTime()));
                stmt.setString(15, bus.getInsuranceProvider());
                stmt.setDate(16, new java.sql.Date(bus.getInsuranceExpiry().getTime()));
                stmt.setInt(17, bus.getOwnerId());
                stmt.setString(18, bus.getCurrentLocation());
                stmt.setString(19, bus.getNotes());
                stmt.setInt(20, bus.getVehicleId());
                
                stmt.executeUpdate();
            }
            
            // Update buses table
            try (PreparedStatement stmt = connection.prepareStatement(busQuery)) {
                stmt.setString(1, bus.getBusType());
                stmt.setInt(2, bus.getSeatingCapacity());
                stmt.setInt(3, bus.getStandingCapacity());
                stmt.setBoolean(4, bus.isAccessible());
                stmt.setString(5, bus.getAssignedRoute());
                stmt.setString(6, bus.getRouteType());
                stmt.setString(7, bus.getOperatorCompany());
                stmt.setDate(8, new java.sql.Date(bus.getInspectionDueDate().getTime()));
                stmt.setBoolean(9, bus.isHasCCTV());
                stmt.setString(10, bus.getTicketingSystemType());
                stmt.setBoolean(11, bus.isAirConditioned());
                stmt.setInt(12, bus.getVehicleId());
                
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
            System.out.println("Error updating bus: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error resetting auto-commit: " + e.getMessage());
            }
        }
    }
    
    public boolean delete(int busId) {
        String query = "DELETE FROM vehicles WHERE vehicle_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, busId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting bus: " + e.getMessage());
            return false;
        }
    }
    
    private Bus extractBusFromResultSet(ResultSet rs) throws SQLException {
        Bus bus = new Bus(
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
            rs.getString("bus_type"),
            rs.getInt("seating_capacity"),
            rs.getInt("standing_capacity"),
            rs.getBoolean("is_accessible"),
            rs.getString("assigned_route"),
            rs.getString("route_type"),
            rs.getString("operator_company"),
            rs.getDate("inspection_due_date"),
            rs.getBoolean("has_cctv"),
            rs.getString("ticketing_system_type"),
            rs.getBoolean("air_conditioned")
        );
        return bus;
    }
}
