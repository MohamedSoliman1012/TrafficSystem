package dao;

import com.AavengersTrafficControle.trafficsystem.model.Police;
import com.AavengersTrafficControle.trafficsystem.model.Report;
import com.AavengersTrafficControle.trafficsystem.model.Person;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PoliceDAO extends VehicleDAO {
    
    public Police authenticate(String username, String password) {
        String query = "SELECT p.*, po.* FROM persons p " +
                      "JOIN police po ON p.person_id = po.person_id " +
                      "WHERE po.username = ? AND po.password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password); // In production, use proper password hashing
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extractPoliceFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error authenticating police: " + e.getMessage());
        }
        return null;
    }
    
    public List<Report> getReportsByPoliceId(int policeId) {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT * FROM reports WHERE police_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, policeId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                reports.add(extractReportFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error getting reports: " + e.getMessage());
        }
        return reports;
    }
    
    public boolean createReport(Report report) {
        String query = "INSERT INTO reports (police_id, driver_id, vehicle_id, report_date, " +
                      "violation_type, location, description, fine, points_deducted, status, " +
                      "evidence, witness_statement, due_date, is_paid) " +
                      "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, report.getPoliceId());
            stmt.setInt(2, report.getDriverId());
            stmt.setInt(3, report.getVehicleId());
            stmt.setTimestamp(4, new Timestamp(report.getReportDate().getTime()));
            stmt.setString(5, report.getViolationType());
            stmt.setString(6, report.getLocation());
            stmt.setString(7, report.getDescription());
            stmt.setDouble(8, report.getFine());
            stmt.setInt(9, report.getPointsDeducted());
            stmt.setString(10, report.getStatus());
            stmt.setString(11, report.getEvidence());
            stmt.setString(12, report.getWitnessStatement());
            stmt.setDate(13, new java.sql.Date(report.getDueDate().getTime()));
            stmt.setBoolean(14, report.isPaid());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error creating report: " + e.getMessage());
            return false;
        }
    }
    
    public Person findPersonByNationalId(String nationalId) {
        String query = "SELECT * FROM persons WHERE national_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nationalId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return extractPersonFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error finding person: " + e.getMessage());
        }
        return null;
    }

    public List<Report> getAllReports() {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT * FROM reports";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                reports.add(extractReportFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all reports: " + e.getMessage());
        }
        return reports;
    }
    
    private Police extractPoliceFromResultSet(ResultSet rs) throws SQLException {
        Police police = new Police();
        // Set person properties
        police.setPersonId(rs.getInt("person_id"));
        police.setFirstName(rs.getString("first_name"));
        police.setLastName(rs.getString("last_name"));
        police.setNationalId(rs.getString("national_id"));
        police.setDateOfBirth(rs.getDate("date_of_birth"));
        police.setAddress(rs.getString("address"));
        police.setPhoneNumber(rs.getString("phone_number"));
        police.setEmail(rs.getString("email"));
        police.setGender(rs.getString("gender"));
        police.setBloodType(rs.getString("blood_type"));
        
        // Set police-specific properties
        police.setBadgeNumber(rs.getInt("badge_number"));
        police.setRank(rs.getString("rank"));
        police.setDepartment(rs.getString("department"));
        police.setUsername(rs.getString("username"));
        police.setPassword(rs.getString("password"));
        police.setJoinDate(rs.getDate("join_date"));
        police.setStatus(rs.getString("status"));
        police.setSpecialization(rs.getString("specialization"));
        
        return police;
    }
    
    private Report extractReportFromResultSet(ResultSet rs) throws SQLException {
        Report report = new Report();
        report.setReportId(rs.getInt("report_id"));
        report.setPoliceId(rs.getInt("police_id"));
        report.setDriverId(rs.getInt("driver_id"));
        report.setVehicleId(rs.getInt("vehicle_id"));
        report.setReportDate(rs.getTimestamp("report_date"));
        report.setViolationType(rs.getString("violation_type"));
        report.setLocation(rs.getString("location"));
        report.setDescription(rs.getString("description"));
        report.setFine(rs.getDouble("fine"));
        report.setPointsDeducted(rs.getInt("points_deducted"));
        report.setStatus(rs.getString("status"));
        report.setEvidence(rs.getString("evidence"));
        report.setWitnessStatement(rs.getString("witness_statement"));
        report.setDueDate(rs.getDate("due_date"));
        report.setPaid(rs.getBoolean("is_paid"));
        return report;
    }

    private Person extractPersonFromResultSet(ResultSet rs) throws SQLException {
        Person person = new Person();
        person.setPersonId(rs.getInt("person_id"));
        person.setFirstName(rs.getString("first_name"));
        person.setLastName(rs.getString("last_name"));
        person.setNationalId(rs.getString("national_id"));
        person.setDateOfBirth(rs.getDate("date_of_birth"));
        person.setAddress(rs.getString("address"));
        person.setPhoneNumber(rs.getString("phone_number"));
        person.setEmail(rs.getString("email"));
        person.setGender(rs.getString("gender"));
        person.setBloodType(rs.getString("blood_type"));
        person.setEmergencyContact(rs.getString("emergency_contact"));
        person.setEmergencyPhone(rs.getString("emergency_phone"));
        return person;
    }
} 