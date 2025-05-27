package dao;

import com.AavengersTrafficControle.trafficsystem.model.Person;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Handles all the person DB stuff, ya know
public class PersonDAO {
    private Connection connection;

    public PersonDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    public Person findById(int personId) {
        String query = "SELECT * FROM persons WHERE person_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, personId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractPersonFromResultSet(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error finding person: " + e.getMessage());
        }
        return null;
    }

    public List<Person> findByLastName(String lastName) {
        List<Person> people = new ArrayList<>();
        String query = "SELECT * FROM persons WHERE last_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, lastName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                people.add(extractPersonFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error finding people: " + e.getMessage());
        }
        return people;
    }

    public boolean insert(Person person) {
        String query = "INSERT INTO persons (first_name, last_name, national_id, date_of_birth, address, phone_number, email, gender, blood_type, emergency_contact, emergency_phone) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.setString(3, person.getNationalId());
            stmt.setDate(4, new java.sql.Date(person.getDateOfBirth().getTime()));
            stmt.setString(5, person.getAddress());
            stmt.setString(6, person.getPhoneNumber());
            stmt.setString(7, person.getEmail());
            stmt.setString(8, person.getGender());
            stmt.setString(9, person.getBloodType());
            stmt.setString(10, person.getEmergencyContact());
            stmt.setString(11, person.getEmergencyPhone());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating person failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    person.setPersonId(generatedKeys.getInt(1));
                }
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error inserting person: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Person person) {
        String query = "UPDATE persons SET first_name=?, last_name=?, national_id=?, date_of_birth=?, address=?, phone_number=?, email=?, gender=?, blood_type=?, emergency_contact=?, emergency_phone=? WHERE person_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.setString(3, person.getNationalId());
            stmt.setDate(4, new java.sql.Date(person.getDateOfBirth().getTime()));
            stmt.setString(5, person.getAddress());
            stmt.setString(6, person.getPhoneNumber());
            stmt.setString(7, person.getEmail());
            stmt.setString(8, person.getGender());
            stmt.setString(9, person.getBloodType());
            stmt.setString(10, person.getEmergencyContact());
            stmt.setString(11, person.getEmergencyPhone());
            stmt.setInt(12, person.getPersonId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating person: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(int personId) {
        String query = "DELETE FROM persons WHERE person_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, personId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting person: " + e.getMessage());
            return false;
        }
    }

    public List<Person> getAllPersons() {
        List<Person> people = new ArrayList<>();
        String query = "SELECT * FROM persons";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                people.add(extractPersonFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all persons: " + e.getMessage());
        }
        return people;
    }

    // Get criminal status for a person
    public String getCriminalStatusById(int personId) {
        String sql = "SELECT criminal_status FROM persons WHERE person_id = ?";
        try (java.sql.Connection conn = DatabaseConnection.getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, personId);
            java.sql.ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("criminal_status");
            }
        } catch (Exception e) {
            System.out.println("Error fetching criminal status: " + e.getMessage());
        }
        return "Unknown";
    }

    private Person extractPersonFromResultSet(ResultSet rs) throws SQLException {
        return new Person(
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
            rs.getString("emergency_phone")
        );
    }
}
