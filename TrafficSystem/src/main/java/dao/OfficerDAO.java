/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.AavengersTrafficControle.trafficsystem.model.Officer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohamedsoliman
 */
public class OfficerDAO {
    private Connection connection;

    public OfficerDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public Officer findById(int officerId) {
        String query = "SELECT * FROM officers WHERE person_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, officerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractOfficerFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error finding officer: " + e.getMessage());
        }
        return null;
    }

    public List<Officer> findByLastName(String lastName) {
        List<Officer> officers = new ArrayList<>();
        String query = "SELECT * FROM officers WHERE last_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, lastName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                officers.add(extractOfficerFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error finding officers: " + e.getMessage());
        }
        return officers;
    }

    public boolean insert(Officer officer) {
        String query = "INSERT INTO officers (first_name, last_name, national_id, date_of_birth, address, phone_number, email, gender, blood_type, emergency_contact, emergency_phone) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, officer.getFirstName());
            stmt.setString(2, officer.getLastName());
            stmt.setString(3, officer.getNationalId());
            stmt.setDate(4, new java.sql.Date(officer.getDateOfBirth().getTime()));
            stmt.setString(5, officer.getAddress());
            stmt.setString(6, officer.getPhoneNumber());
            stmt.setString(7, officer.getEmail());
            stmt.setString(8, officer.getGender());
            stmt.setString(9, officer.getBloodType());
            stmt.setString(10, officer.getEmergencyContact());
            stmt.setString(11, officer.getEmergencyPhone());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating officer failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    officer.setPersonId(generatedKeys.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error inserting officer: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Officer officer) {
        String query = "UPDATE officers SET first_name=?, last_name=?, national_id=?, date_of_birth=?, address=?, phone_number=?, email=?, gender=?, blood_type=?, emergency_contact=?, emergency_phone=? WHERE person_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, officer.getFirstName());
            stmt.setString(2, officer.getLastName());
            stmt.setString(3, officer.getNationalId());
            stmt.setDate(4, new java.sql.Date(officer.getDateOfBirth().getTime()));
            stmt.setString(5, officer.getAddress());
            stmt.setString(6, officer.getPhoneNumber());
            stmt.setString(7, officer.getEmail());
            stmt.setString(8, officer.getGender());
            stmt.setString(9, officer.getBloodType());
            stmt.setString(10, officer.getEmergencyContact());
            stmt.setString(11, officer.getEmergencyPhone());
            stmt.setInt(12, officer.getPersonId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating officer: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int officerId) {
        String query = "DELETE FROM officers WHERE person_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, officerId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting officer: " + e.getMessage());
            return false;
        }
    }

    private Officer extractOfficerFromResultSet(ResultSet rs) throws SQLException {
        Officer officer = new Officer();
        officer.setPersonId(rs.getInt("person_id"));
        officer.setFirstName(rs.getString("first_name"));
        officer.setLastName(rs.getString("last_name"));
        officer.setNationalId(rs.getString("national_id"));
        officer.setDateOfBirth(rs.getDate("date_of_birth"));
        officer.setAddress(rs.getString("address"));
        officer.setPhoneNumber(rs.getString("phone_number"));
        officer.setEmail(rs.getString("email"));
        officer.setGender(rs.getString("gender"));
        officer.setBloodType(rs.getString("blood_type"));
        officer.setEmergencyContact(rs.getString("emergency_contact"));
        officer.setEmergencyPhone(rs.getString("emergency_phone"));
        return officer;
    }
}
