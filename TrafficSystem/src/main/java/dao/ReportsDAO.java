package dao;

import com.AavengersTrafficControle.trafficsystem.model.Report;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// This class is for handling reports in the DB, nothing too crazy
public class ReportsDAO {
    private Connection connection;

    public ReportsDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public Report findById(int reportId) {
        String query = "SELECT * FROM reports WHERE report_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reportId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractReportFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error finding report: " + e.getMessage());
        }
        return null;
    }

    public List<Report> findByDriverId(int driverId) {
        List<Report> reports = new ArrayList<>();
        String query = "SELECT * FROM reports WHERE driver_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, driverId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reports.add(extractReportFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error finding reports: " + e.getMessage());
        }
        return reports;
    }

    public boolean insert(Report report) {
        String query = "INSERT INTO reports (police_id, driver_id, vehicle_id, report_date, violation_type, location, description, fine, points_deducted, status, evidence, witness_statement, due_date, is_paid) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, report.getPoliceId());
            stmt.setInt(2, report.getDriverId());
            stmt.setInt(3, report.getVehicleId());
            stmt.setDate(4, new java.sql.Date(report.getReportDate().getTime()));
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
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating report failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    report.setReportId(generatedKeys.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error inserting report: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Report report) {
        String query = "UPDATE reports SET police_id=?, driver_id=?, vehicle_id=?, report_date=?, violation_type=?, location=?, description=?, fine=?, points_deducted=?, status=?, evidence=?, witness_statement=?, due_date=?, is_paid=? WHERE report_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, report.getPoliceId());
            stmt.setInt(2, report.getDriverId());
            stmt.setInt(3, report.getVehicleId());
            stmt.setDate(4, new java.sql.Date(report.getReportDate().getTime()));
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
            stmt.setInt(15, report.getReportId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating report: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int reportId) {
        String query = "DELETE FROM reports WHERE report_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, reportId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting report: " + e.getMessage());
            return false;
        }
    }

    private Report extractReportFromResultSet(ResultSet rs) throws SQLException {
        return new Report(
            rs.getInt("report_id"),
            rs.getInt("police_id"),
            rs.getInt("driver_id"),
            rs.getInt("vehicle_id"),
            rs.getDate("report_date"),
            rs.getString("violation_type"),
            rs.getString("location"),
            rs.getString("description"),
            rs.getDouble("fine"),
            rs.getInt("points_deducted"),
            rs.getString("status"),
            rs.getString("evidence"),
            rs.getString("witness_statement"),
            rs.getDate("due_date"),
            rs.getBoolean("is_paid")
        );
    }
}
