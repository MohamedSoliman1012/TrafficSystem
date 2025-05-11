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

-- Insert sample data into vehicles table
INSERT INTO vehicles (plate_number, registration_number, type, make, model, year, color, fuel_type, 
                     engine_number, chassis_number, seats, vehicle_status, registration_date, expiry_date,
                     insurance_provider, insurance_expiry, owner_id, current_location, notes)
VALUES
    ('ABC123', 'REG001', 'Car', 'Toyota', 'Camry', 2020, 'Silver', 'Petrol', 'ENG001', 'CHS001', 5, 'Active',
     '2020-01-01', '2025-01-01', 'State Farm', '2024-12-31', 1, 'New York', 'Regular maintenance'),
    ('XYZ789', 'REG002', 'Motorcycle', 'Honda', 'CBR600', 2021, 'Red', 'Petrol', 'ENG002', 'CHS002', 2, 'Active',
     '2021-03-15', '2026-03-15', 'Progressive', '2024-12-31', 2, 'Los Angeles', 'Track ready'),
    ('BUS456', 'REG003', 'Bus', 'Mercedes', 'Sprinter', 2019, 'White', 'Diesel', 'ENG003', 'CHS003', 20, 'Active',
     '2019-06-01', '2024-06-01', 'Allstate', '2024-12-31', 3, 'Chicago', 'School bus');

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
     '2022-02-01', '2027-02-01', 'Geico', '2024-12-31', 4, 'Miami', 'High performance'),
    ('MOTO789', 'REG005', 'Motorcycle', 'Ducati', 'Panigale', 2023, 'Yellow', 'Petrol', 'ENG005', 'CHS005', 2, 'Active',
     '2023-01-15', '2028-01-15', 'Liberty Mutual', '2024-12-31', 5, 'San Francisco', 'Track bike');

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
