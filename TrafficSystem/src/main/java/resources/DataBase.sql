-- Create Vehicle table (parent table)
CREATE TABLE vehicles (
    vehicle_id SERIAL PRIMARY KEY,
    plate_number VARCHAR(20) UNIQUE NOT NULL,
    registration_number VARCHAR(50) UNIQUE NOT NULL,
    type VARCHAR(50) NOT NULL,
    make VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    year INTEGER NOT NULL,
    color VARCHAR(30) NOT NULL,
    fuel_type VARCHAR(20) NOT NULL,
    engine_number VARCHAR(50) UNIQUE NOT NULL,
    chassis_number VARCHAR(50) UNIQUE NOT NULL,
    seats INTEGER NOT NULL,
    vehicle_status VARCHAR(20) NOT NULL,
    registration_date DATE NOT NULL,
    expiry_date DATE NOT NULL,
    insurance_provider VARCHAR(100) NOT NULL,
    insurance_expiry DATE NOT NULL,
    owner_id INTEGER NOT NULL,
    current_location VARCHAR(100),
    notes TEXT
);

-- Create Car table (extends Vehicle)
CREATE TABLE cars (
    vehicle_id INTEGER PRIMARY KEY REFERENCES vehicles(vehicle_id),
    number_of_doors INTEGER NOT NULL,
    body_style VARCHAR(30) NOT NULL,
    transmission VARCHAR(20) NOT NULL,
    drive_type VARCHAR(20) NOT NULL,
    trunk_capacity_liters INTEGER NOT NULL,
    sunroof BOOLEAN NOT NULL,
    infotainment_system VARCHAR(50),
    airbags INTEGER NOT NULL,
    is_luxury BOOLEAN NOT NULL
);

-- Create Motorcycle table (extends Vehicle)
CREATE TABLE motorcycles (
    vehicle_id INTEGER PRIMARY KEY REFERENCES vehicles(vehicle_id),
    engine_capacity_cc INTEGER NOT NULL,
    motorcycle_type VARCHAR(30) NOT NULL,
    gearbox_type VARCHAR(20) NOT NULL,
    cooling_system VARCHAR(20) NOT NULL,
    has_abs BOOLEAN NOT NULL,
    wheel_base_mm INTEGER NOT NULL,
    seat_height_mm INTEGER NOT NULL,
    fuel_tank_capacity DECIMAL(5,2) NOT NULL,
    number_of_strokes INTEGER NOT NULL,
    top_speed_kph INTEGER NOT NULL
);

-- Create Bus table (extends Vehicle)
CREATE TABLE buses (
    vehicle_id INTEGER PRIMARY KEY REFERENCES vehicles(vehicle_id),
    bus_type VARCHAR(30) NOT NULL,
    seating_capacity INTEGER NOT NULL,
    standing_capacity INTEGER NOT NULL,
    is_accessible BOOLEAN NOT NULL,
    assigned_route VARCHAR(50),
    route_type VARCHAR(30),
    operator_company VARCHAR(100),
    inspection_due_date DATE,
    has_cctv BOOLEAN NOT NULL,
    ticketing_system_type VARCHAR(50),
    air_conditioned BOOLEAN NOT NULL
);

-- Create persons table (parent table)
CREATE TABLE persons (
    person_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    national_id VARCHAR(20) UNIQUE NOT NULL,
    date_of_birth DATE NOT NULL,
    address TEXT NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    gender VARCHAR(10) NOT NULL,
    blood_type VARCHAR(5),
    emergency_contact VARCHAR(100),
    emergency_phone VARCHAR(20)
);

-- Create police table (extends persons)
CREATE TABLE police (
    person_id INTEGER PRIMARY KEY REFERENCES persons(person_id),
    badge_number INTEGER UNIQUE NOT NULL,
    rank VARCHAR(50) NOT NULL,
    department VARCHAR(100) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    join_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    specialization VARCHAR(100),
    rank_level INTEGER NOT NULL DEFAULT 4
);

-- Create drivers table (extends persons)
CREATE TABLE drivers (
    person_id INTEGER PRIMARY KEY REFERENCES persons(person_id),
    license_number VARCHAR(20) UNIQUE NOT NULL,
    license_issue_date DATE NOT NULL,
    license_expiry_date DATE NOT NULL,
    license_type VARCHAR(20) NOT NULL,
    points INTEGER DEFAULT 0,
    status VARCHAR(20) NOT NULL,
    restrictions TEXT,
    total_violations INTEGER DEFAULT 0
);

-- Create reports table
CREATE TABLE reports (
    report_id SERIAL PRIMARY KEY,
    police_id INTEGER REFERENCES police(person_id),
    driver_id INTEGER REFERENCES drivers(person_id),
    vehicle_id INTEGER REFERENCES vehicles(vehicle_id),
    report_date TIMESTAMP NOT NULL,
    violation_type VARCHAR(100) NOT NULL,
    location TEXT NOT NULL,
    description TEXT,
    fine DECIMAL(10,2) NOT NULL,
    points_deducted INTEGER NOT NULL,
    status VARCHAR(20) NOT NULL,
    evidence TEXT,
    witness_statement TEXT,
    due_date DATE NOT NULL,
    is_paid BOOLEAN DEFAULT FALSE
);

-- Insert sample data into vehicles table
INSERT INTO vehicles (plate_number, registration_number, type, make, model, year, color, fuel_type, 
                     engine_number, chassis_number, seats, vehicle_status, registration_date, expiry_date,
                     insurance_provider, insurance_expiry, owner_id, current_location, notes)
VALUES
    ('ABC123', 'REG001', 'Car', 'Toyota', 'Camry', 2020, 'Silver', 'Petrol', 'ENG001', 'CHS001', 5, 'Active',
     '2020-01-01', '2025-01-01', 'State Farm', '2024-12-31', 6, 'New York', 'Regular maintenance'),
    ('XYZ789', 'REG002', 'Motorcycle', 'Honda', 'CBR600', 2021, 'Red', 'Petrol', 'ENG002', 'CHS002', 2, 'Active',
     '2021-03-15', '2026-03-15', 'Progressive', '2024-12-31', 7, 'Los Angeles', 'Track ready'),
    ('BUS456', 'REG003', 'Bus', 'Mercedes', 'Sprinter', 2019, 'White', 'Diesel', 'ENG003', 'CHS003', 20, 'Active',
     '2019-06-01', '2024-06-01', 'Allstate', '2024-12-31', 8, 'Chicago', 'School bus');

-- Insert sample data into cars table
INSERT INTO cars (vehicle_id, number_of_doors, body_style, transmission, drive_type, trunk_capacity_liters,
                 sunroof, infotainment_system, airbags, is_luxury)
VALUES
    (1, 4, 'Sedan', 'Automatic', 'FWD', 450, true, 'Toyota Entune', 8, false);

-- Insert sample data into motorcycles table
INSERT INTO motorcycles (vehicle_id, engine_capacity_cc, motorcycle_type, gearbox_type, cooling_system,
                        has_abs, wheel_base_mm, seat_height_mm, fuel_tank_capacity, number_of_strokes,
                        top_speed_kph)
VALUES
    (2, 600, 'Sport', 'Manual', 'Liquid', true, 1400, 820, 18.5, 4, 240);

-- Insert sample data into buses table
INSERT INTO buses (vehicle_id, bus_type, seating_capacity, standing_capacity, is_accessible,
                  assigned_route, route_type, operator_company, inspection_due_date, has_cctv,
                  ticketing_system_type, air_conditioned)
VALUES
    (3, 'School Bus', 20, 10, true, 'Route 101', 'School', 'City Transport Co.', '2024-06-01',
     true, 'Electronic', true);

-- Add more sample data
INSERT INTO vehicles (plate_number, registration_number, type, make, model, year, color, fuel_type,
                     engine_number, chassis_number, seats, vehicle_status, registration_date, expiry_date,
                     insurance_provider, insurance_expiry, owner_id, current_location, notes)
VALUES
    ('DEF456', 'REG004', 'Car', 'BMW', 'M5', 2022, 'Black', 'Petrol', 'ENG004', 'CHS004', 5, 'Active',
     '2022-02-01', '2027-02-01', 'Geico', '2024-12-31', 6, 'Miami', 'High performance'),
    ('MOTO789', 'REG005', 'Motorcycle', 'Ducati', 'Panigale', 2023, 'Yellow', 'Petrol', 'ENG005', 'CHS005', 2, 'Active',
     '2023-01-15', '2028-01-15', 'Liberty Mutual', '2024-12-31', 7, 'San Francisco', 'Track bike');

-- Add corresponding entries for new vehicles
INSERT INTO cars (vehicle_id, number_of_doors, body_style, transmission, drive_type, trunk_capacity_liters,
                 sunroof, infotainment_system, airbags, is_luxury)
VALUES
    (4, 4, 'Sedan', 'Automatic', 'AWD', 500, true, 'BMW iDrive', 10, true);

INSERT INTO motorcycles (vehicle_id, engine_capacity_cc, motorcycle_type, gearbox_type, cooling_system,
                        has_abs, wheel_base_mm, seat_height_mm, fuel_tank_capacity, number_of_strokes,
                        top_speed_kph)
VALUES
    (5, 1100, 'Sport', 'Manual', 'Liquid', true, 1437, 830, 17.0, 4, 299);

-- Insert sample data for persons (for police officers)
INSERT INTO persons (person_id, first_name, last_name, national_id, date_of_birth, address, phone_number, email, gender, blood_type)
VALUES
    (1, 'Ahmed', 'Saleh', 'ID101', '1980-01-15', '123 Police St, City', '555-0101', 'ahmed.saleh@police.com', 'Male', 'O+'),
    (2, 'Peter', 'Parker', 'ID102', '1985-05-20', '456 Officer Ave, Town', '555-0102', 'peter.parker@police.com', 'Male', 'A+'),
    (3, 'Tom', 'Stark', 'ID103', '1990-11-30', '789 Cop Rd, Village', '555-0103', 'tom.stark@police.com', 'Male', 'B+'),
    (4, 'Tony', 'Stark', 'ID104', '1992-07-12', '321 Officer St, City', '555-0104', 'tony.stark@police.com', 'Male', 'AB+'),
    (5, 'Jef', 'Stark', 'ID105', '1995-09-23', '654 Police Ave, Town', '555-0105', 'jef.stark@police.com', 'Male', 'O-');

-- Insert sample data for police with new credentials
INSERT INTO police (person_id, badge_number, rank, department, username, password, join_date, status, specialization, rank_level)
VALUES
    (1, 1001, 'Chief', 'Traffic Division', 'AhmedSaleh', '12345678', '2015-06-01', 'Active', 'Traffic Control', 1),
    (2, 1002, 'Captain', 'Traffic Division', 'Peter', '12345678', '2018-03-15', 'Active', 'Accident Investigation', 2),
    (3, 1003, 'Lieutenant', 'Traffic Division', 'Parker', '12345678', '2019-01-10', 'Active', 'Traffic Control', 3),
    (4, 1004, 'Officer', 'Traffic Division', 'Tom', '12345678', '2020-04-20', 'Active', 'Accident Investigation', 4),
    (5, 1005, 'Officer', 'Traffic Division', 'Tony', '12345678', '2021-02-15', 'Active', 'Traffic Control', 4);

-- Insert sample data for drivers (using different person IDs)
INSERT INTO persons (person_id, first_name, last_name, national_id, date_of_birth, address, phone_number, email, gender, blood_type)
VALUES
    (6, 'John', 'Smith', 'ID001', '1980-01-15', '123 Main St, City', '555-0101', 'john.smith@email.com', 'Male', 'O+'),
    (7, 'Sarah', 'Johnson', 'ID002', '1985-05-20', '456 Oak Ave, Town', '555-0102', 'sarah.j@email.com', 'Female', 'A+'),
    (8, 'Michael', 'Brown', 'ID003', '1975-11-30', '789 Pine Rd, Village', '555-0103', 'm.brown@email.com', 'Male', 'B+');

INSERT INTO drivers (person_id, license_number, license_issue_date, license_expiry_date, license_type, points, status)
VALUES
    (6, 'DL001', '2010-01-01', '2025-01-01', 'Class A', 0, 'Valid'),
    (7, 'DL002', '2011-02-01', '2026-02-01', 'Class B', 0, 'Valid'),
    (8, 'DL003', '2012-03-01', '2027-03-01', 'Class C', 0, 'Valid');

-- Insert sample data for reports
INSERT INTO reports (police_id, driver_id, vehicle_id, report_date, violation_type, location, description, fine, points_deducted, status, due_date)
VALUES
    (1, 6, 1, CURRENT_TIMESTAMP, 'Speeding', 'Main Street', 'Exceeded speed limit by 20 mph', 150.00, 2, 'Pending', CURRENT_DATE + 30);

-- Add foreign key to vehicles table for owner
ALTER TABLE vehicles ADD CONSTRAINT fk_owner
    FOREIGN KEY (owner_id) REFERENCES drivers(person_id);

-- Update vehicles sample data to use existing driver IDs
UPDATE vehicles SET owner_id = 6 WHERE vehicle_id = 1;
UPDATE vehicles SET owner_id = 7 WHERE vehicle_id = 2;
UPDATE vehicles SET owner_id = 8 WHERE vehicle_id = 3;
UPDATE vehicles SET owner_id = 6 WHERE vehicle_id = 4;
UPDATE vehicles SET owner_id = 7 WHERE vehicle_id = 5;
