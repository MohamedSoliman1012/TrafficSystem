/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.AavengersTrafficControle.trafficsystem.model.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohamedsoliman
 */
public class DriverDAO {
    private Connection connection;

    public DriverDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public Driver findById(int driverId) {
        String query = "SELECT * FROM drivers WHERE person_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, driverId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractDriverFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error finding driver: " + e.getMessage());
        }
        return null;
    }

    public List<Driver> findByLastName(String lastName) {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM drivers WHERE last_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, lastName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                drivers.add(extractDriverFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error finding drivers: " + e.getMessage());
        }
        return drivers;
    }

    public boolean insert(Driver driver) {
        String query = "INSERT INTO drivers (first_name, last_name, national_id, date_of_birth, address, phone_number, email, gender, blood_type, emergency_contact, emergency_phone, license_number, license_issue_date, license_expiry_date, license_type, points, status, restrictions, total_violations) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, driver.getFirstName());
            stmt.setString(2, driver.getLastName());
            stmt.setString(3, driver.getNationalId());
            stmt.setDate(4, new java.sql.Date(driver.getDateOfBirth().getTime()));
            stmt.setString(5, driver.getAddress());
            stmt.setString(6, driver.getPhoneNumber());
            stmt.setString(7, driver.getEmail());
            stmt.setString(8, driver.getGender());
            stmt.setString(9, driver.getBloodType());
            stmt.setString(10, driver.getEmergencyContact());
            stmt.setString(11, driver.getEmergencyPhone());
            stmt.setString(12, driver.getLicenseNumber());
            stmt.setDate(13, new java.sql.Date(driver.getLicenseIssueDate().getTime()));
            stmt.setDate(14, new java.sql.Date(driver.getLicenseExpiryDate().getTime()));
            stmt.setString(15, driver.getLicenseType());
            stmt.setInt(16, driver.getPoints());
            stmt.setString(17, driver.getStatus());
            stmt.setString(18, driver.getRestrictions());
            stmt.setInt(19, driver.getTotalViolations());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating driver failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    driver.setPersonId(generatedKeys.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error inserting driver: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Driver driver) {
        String query = "UPDATE drivers SET first_name=?, last_name=?, national_id=?, date_of_birth=?, address=?, phone_number=?, email=?, gender=?, blood_type=?, emergency_contact=?, emergency_phone=?, license_number=?, license_issue_date=?, license_expiry_date=?, license_type=?, points=?, status=?, restrictions=?, total_violations=? WHERE person_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, driver.getFirstName());
            stmt.setString(2, driver.getLastName());
            stmt.setString(3, driver.getNationalId());
            stmt.setDate(4, new java.sql.Date(driver.getDateOfBirth().getTime()));
            stmt.setString(5, driver.getAddress());
            stmt.setString(6, driver.getPhoneNumber());
            stmt.setString(7, driver.getEmail());
            stmt.setString(8, driver.getGender());
            stmt.setString(9, driver.getBloodType());
            stmt.setString(10, driver.getEmergencyContact());
            stmt.setString(11, driver.getEmergencyPhone());
            stmt.setString(12, driver.getLicenseNumber());
            stmt.setDate(13, new java.sql.Date(driver.getLicenseIssueDate().getTime()));
            stmt.setDate(14, new java.sql.Date(driver.getLicenseExpiryDate().getTime()));
            stmt.setString(15, driver.getLicenseType());
            stmt.setInt(16, driver.getPoints());
            stmt.setString(17, driver.getStatus());
            stmt.setString(18, driver.getRestrictions());
            stmt.setInt(19, driver.getTotalViolations());
            stmt.setInt(20, driver.getPersonId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating driver: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int driverId) {
        String query = "DELETE FROM drivers WHERE person_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, driverId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting driver: " + e.getMessage());
            return false;
        }
    }

    private Driver extractDriverFromResultSet(ResultSet rs) throws SQLException {
        return new Driver(
            rs.getInt("person_id"),
            rs.getString("first_name"),
            rs.getString("last_name"),
            rs.getString("national_id"),
            rs.getDate("date_of_birth"),
            rs.getString("address"),
            rs.getString("phone_number"),
            rs.getString("email"),
            rs.getString("gender"),
            rs.getString("blood_type"),
            rs.getString("emergency_contact"),
            rs.getString("emergency_phone"),
            rs.getString("license_number"),
            rs.getDate("license_issue_date"),
            rs.getDate("license_expiry_date"),
            rs.getString("license_type"),
            rs.getInt("points"),
            rs.getString("status"),
            rs.getString("restrictions"),
            rs.getInt("total_violations")
        );
    }
}
