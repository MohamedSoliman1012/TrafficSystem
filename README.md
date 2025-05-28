# Traffic System

A Java Swing-based Traffic System for managing vehicles, persons, police, and traffic violation reports. The system supports both a graphical user interface (GUI) and a command-line interface (CLI) for police officers to log in, search, and manage traffic-related data.

## Features

- **User Authentication:** Police officers log in with a username and password.
- **Role-Based Menus:** Menu options and permissions depend on the officer's rank.
- **Vehicle Management:** Search vehicles by plate number or QR code.
- **Person Management:** Search persons by national ID or QR code.
- **Report Management:** Create, view, and pay for traffic violation reports.
- **GUI:** Modern, styled Java Swing interface with background image support.
- **CLI:** Console-based interface for basic operations.
- **QR Code Integration:** Scan QR codes using a webcam to quickly find vehicles or persons.

## Project Structure

```
TrafficSystem/
├── src/
│   └── main/
│       ├── java/
│       │   ├── App/
│       │   │   ├── GUIAPP.java
│       │   │   ├── Main.java
│       │   │   └── QRCodeScanner.java
│       │   ├── com/AavengersTrafficControle/trafficsystem/model/
│       │   │   ├── Person.java
│       │   │   ├── Police.java
│       │   │   ├── Driver.java
│       │   │   ├── Vehicle.java
│       │   │   ├── Car.java
│       │   │   └── Report.java
│       │   └── dao/
│       │       ├── PersonDAO.java
│       │       ├── PoliceDAO.java
│       │       ├── ReportsDAO.java
│       │       ├── VehicleDAO.java
│       │       └── DatabaseConnection.java
│       └── resources/
│           ├── background.png
│           └── DataBase.sql
```

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven (for dependency management and building)
- Webcam (for QR code scanning features)

### Build & Run

1. **Clone the repository:**
   ```sh
   git clone https://github.com/yourusername/TrafficSystem.git
   cd TrafficSystem/TrafficSystem
   ```

2. **Build the project:**
   ```sh
   mvn clean package
   ```

3. **Run the GUI application:**
   ```sh
   java -cp target/classes App.GUIAPP
   ```

   Or run the CLI:
   ```sh
   java -cp target/classes App.Main
   ```

### Database

- The schema and initial data are in [`src/main/resources/DataBase.sql`](src/main/resources/DataBase.sql).
- Update your database connection settings in [`DatabaseConnection.java`](src/main/java/dao/DatabaseConnection.java) as needed.

## Usage

- **Login:** Enter your police username and password.
- **Main Menu:** Options depend on your rank (search, create reports, view reports, pay fees, etc.).
- **QR Code:** Use the "Search by QR Code" buttons to scan and search using a webcam.
- **Create Report:** Fill in the form and confirm to create a new traffic violation report.

## Technologies Used

- Java Swing (GUI)
- JDBC (Database access)
- ZXing & Webcam Capture (QR code scanning)
- Maven (build tool)

## Authors

- [Your Name or Team Name]

## License

This project is licensed under the MIT License.

## Object-Oriented Programming Concepts Used

This project demonstrates several core Java OOP concepts:

- **Encapsulation:**  
  Classes such as `Vehicle`, `Person`, and `Police` encapsulate their data fields as private and provide public getter/setter methods to control access and modification.

- **Inheritance:**  
  The class `Car` extends `Vehicle`, and `Driver` may extend `Person`, allowing for code reuse and logical hierarchy.

- **Polymorphism:**  
  Methods can accept parameters of a superclass type (e.g., `Vehicle`), allowing different subclasses (like `Car`) to be used interchangeably.

- **Abstraction:**  
  The use of DAO (Data Access Object) classes such as `VehicleDAO`, `PersonDAO`, and `PoliceDAO` abstracts the database operations from the business logic.

These OOP principles help keep the code modular, maintainable, and extensible.

## GUI Walkthrough

The Traffic System features a modern Java Swing-based graphical user interface (GUI) designed for ease of use by police officers. Here’s a step-by-step walkthrough of the main GUI flow:

### 1. Login Screen

- **Fields:**  
  - Username  
  - Password  
- **Action:**  
  Enter your credentials and click the **Login** button.  
  If authentication is successful, you are taken to the main menu. Otherwise, an error message is shown.

### 2. Main Menu

The options displayed depend on your rank (role-based access):

- **Common Buttons:**  
  - **Search Vehicle by Plate Number**  
  - **Search Vehicle by QR Code**  
  - **Search Person by National ID**  
  - **Search Person by QR Code**  
  - **Create New Report**  
  - **View My Reports**  
  - **View All Reports** (for higher ranks)  
  - **Pay Report Fees**  
  - **Logout**  
  - **Exit**

### 3. Searching

- **Search Vehicle by Plate Number:**  
  Prompts for a plate number and displays vehicle details if found.

- **Search Vehicle by QR Code:**  
  Activates the webcam. Hold a vehicle QR code up to the camera to search.

- **Search Person by National ID:**  
  Prompts for a national ID and displays person details if found.

- **Search Person by QR Code:**  
  Activates the webcam. Hold a person QR code up to the camera to search.

### 4. Reports

- **Create New Report:**  
  Opens a form to enter details about a new traffic violation. Submit to save the report.

- **View My Reports:**  
  Displays a list of reports you have created.

- **View All Reports:**  
  (Available to higher ranks) Shows all reports in the system.

- **Pay Report Fees:**  
  Allows payment of outstanding report fees.

### 5. Other Actions

- **Logout:**  
  Logs you out and returns to the login screen.

- **Exit:**  
  Prompts for confirmation and closes the application.

---

**Note:**  
All buttons are styled for clarity and usability. QR code features require a connected webcam. Dialogs and forms use consistent fonts and colors for a professional look.

