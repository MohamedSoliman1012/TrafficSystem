package App;

import com.AavengersTrafficControle.trafficsystem.model.*;
import dao.*;
import java.util.List;
import java.util.Scanner;
import java.util.Date;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static VehicleDAO vehicleDAO = new VehicleDAO();
    private static final CarDAO carDAO = new CarDAO();
    private static PoliceDAO policeDAO = new PoliceDAO();
    private static Police currentUser = null;

    public static void main(String[] args) {

        
        while (true) {
            if (currentUser == null) {
                if (!login()) {
                    System.out.println("Login failed. Exiting program...");
                    DatabaseConnection.closeConnection();
                    return;
                }
            }
            
            showMainMenu();
        }
    }

    private static boolean login() {
        int maxAttempts = 5;
        int attempts = 0;
        
        while (attempts < maxAttempts) {
            System.out.println("\n=== Police Login ===");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            currentUser = policeDAO.authenticate(username, password);
            if (currentUser != null) {
                System.out.println("Welcome, " + currentUser.getFirstName() + " " + currentUser.getLastName());
                System.out.println("Rank: " + currentUser.getRank() + " (Level " + currentUser.getRankLevel() + ")");
                return true;
            }
            
            attempts++;
            if (attempts < maxAttempts) {
                System.out.println("Invalid username or password. You have " + (maxAttempts - attempts) + " attempts remaining.");
            } else {
                System.out.println("Maximum login attempts reached. Please try again later.");
            }
        }
        return false;
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\n=== Traffic System Menu ===");
            
            // Show menu options based on rank level
            if (currentUser.getRankLevel() == 1) {
                System.out.println("1. Search vehicle by plate number");
                System.out.println("2. Search person by national ID");
                System.out.println("3. Create new report");
                System.out.println("4. View my reports");
                System.out.println("5. View all reports");
                System.out.println("6. Logout");
                System.out.println("7. Exit");
                System.out.print("Enter your choice (1-7): ");
            } else if (currentUser.getRankLevel() == 2) {
                System.out.println("1. Search vehicle by plate number");
                System.out.println("2. Logout");
                System.out.println("3. Exit");
                System.out.print("Enter your choice (1-3): ");
            } else if (currentUser.getRankLevel() == 3) {
                System.out.println("1. Search person by national ID");
                System.out.println("2. Logout");
                System.out.println("3. Exit");
                System.out.print("Enter your choice (1-3): ");
            } else {
                System.out.println("1. Search vehicle by plate number");
                System.out.println("2. Search person by national ID");
                System.out.println("3. Create new report");
                System.out.println("4. View my reports");
                System.out.println("5. Logout");
                System.out.println("6. Exit");
                System.out.print("Enter your choice (1-6): ");
            }

            try {
                String input = scanner.nextLine().trim();
                int choice = Integer.parseInt(input);
                
                boolean validChoice = false;
                switch (currentUser.getRankLevel()) {
                    case 1:
                        if (choice >= 1 && choice <= 7) validChoice = true;
                        break;
                    case 2:
                    case 3:
                        if (choice >= 1 && choice <= 3) validChoice = true;
                        break;
                    default:
                        if (choice >= 1 && choice <= 6) validChoice = true;
                }

                if (!validChoice) {
                    System.out.println("Invalid choice. Please enter a number from the menu.");
                    continue;
                }

                switch (currentUser.getRankLevel()) {
                    case 1:
                        handleRank1Menu(choice);
                        break;
                    case 2:
                        handleRank2Menu(choice);
                        break;
                    case 3:
                        handleRank3Menu(choice);
                        break;
                    default:
                        handleRank4Menu(choice);
                }
                
                // If we get here and the user is null, they logged out
                if (currentUser == null) {
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number from the menu.");
            }
        }
    }

    private static void handleRank1Menu(int choice) {
        switch (choice) {
            case 1:
                searchVehicleByPlate();
                break;
            case 2:
                searchPersonByNationalId();
                break;
            case 3:
                createNewReport();
                break;
            case 4:
                viewMyReports();
                break;
            case 5:
                viewAllReports();
                break;
            case 6:
                currentUser = null;
                break;
            case 7:
                System.out.println("Exiting program...");
                DatabaseConnection.closeConnection();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void handleRank2Menu(int choice) {
        switch (choice) {
            case 1:
                searchVehicleByPlate();
                break;
            case 2:
                currentUser = null;
                break;
            case 3:
                System.out.println("Exiting program...");
                DatabaseConnection.closeConnection();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void handleRank3Menu(int choice) {
        switch (choice) {
            case 1:
                searchPersonByNationalId();
                break;
            case 2:
                currentUser = null;
                break;
            case 3:
                System.out.println("Exiting program...");
                DatabaseConnection.closeConnection();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void handleRank4Menu(int choice) {
        switch (choice) {
            case 1:
                searchVehicleByPlate();
                break;
            case 2:
                searchPersonByNationalId();
                break;
            case 3:
                createNewReport();
                break;
            case 4:
                viewMyReports();
                break;
            case 5:
                currentUser = null;
                break;
            case 6:
                System.out.println("Exiting program...");
                DatabaseConnection.closeConnection();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void searchVehicleByPlate() {
        System.out.print("Enter plate number: ");
        String plateNumber = scanner.nextLine();
        List<Vehicle> vehicles = vehicleDAO.findByPlateNumber(plateNumber);
        if (!vehicles.isEmpty()) {
            System.out.println("\nVehicles found:");
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        } else {
            System.out.println("No vehicles found with that plate number.");
        }
    }

    private static void searchPersonByNationalId() {
        System.out.print("Enter national ID: ");
        String nationalId = scanner.nextLine();
        Object result = policeDAO.findPersonByNationalId(nationalId);
        if (result != null && result instanceof Person) {
            Person person = (Person) result;
            System.out.println("\nPerson found:");
            System.out.println(person);
        } else {
            System.out.println("No person found with that national ID.");
        }
    }

    private static void createNewReport() {
        Report report = new Report();
        report.setPoliceId(currentUser.getPersonId());
        
        System.out.print("Enter driver ID: ");
        report.setDriverId(scanner.nextInt());
        scanner.nextLine();
        
        System.out.print("Enter vehicle ID: ");
        report.setVehicleId(scanner.nextInt());
        scanner.nextLine();
        
        report.setReportDate(new Date());
        
        System.out.print("Enter violation type: ");
        report.setViolationType(scanner.nextLine());
        
        System.out.print("Enter location: ");
        report.setLocation(scanner.nextLine());
        
        System.out.print("Enter description: ");
        report.setDescription(scanner.nextLine());
        
        System.out.print("Enter fine amount: ");
        report.setFine(scanner.nextDouble());
        scanner.nextLine();
        
        System.out.print("Enter points to deduct: ");
        report.setPointsDeducted(scanner.nextInt());
        scanner.nextLine();
        
        report.setStatus("Pending");
        report.setPaid(false);
        
        if (policeDAO.createReport(report)) {
            System.out.println("Report created successfully.");
        } else {
            System.out.println("Failed to create report.");
        }
    }

    private static void viewMyReports() {
        List<Report> reports = policeDAO.getReportsByPoliceId(currentUser.getPersonId());
        if (!reports.isEmpty()) {
            System.out.println("\nYour reports:");
            for (Report report : reports) {
                System.out.println(report);
            }
        } else {
            System.out.println("No reports found.");
        }
    }

    private static void viewAllReports() {
        List<Report> reports = policeDAO.getAllReports();
        if (!reports.isEmpty()) {
            System.out.println("\nAll reports:");
            for (Report report : reports) {
                System.out.println(report);
            }
        } else {
            System.out.println("No reports found.");
        }
    }
} 