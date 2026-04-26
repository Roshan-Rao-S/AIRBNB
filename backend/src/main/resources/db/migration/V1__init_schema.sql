CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    verified BIT(1) NOT NULL DEFAULT b'0'
);

CREATE TABLE IF NOT EXISTS properties (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    location VARCHAR(255),
    price DOUBLE,
    owner_email VARCHAR(255),
    image_url VARCHAR(1000),
    rating DOUBLE DEFAULT 0,
    review_count INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS bookings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(255),
    property_id BIGINT,
    check_in DATE,
    check_out DATE,
    guests INT,
    status VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS reviews (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    property_id BIGINT,
    user_email VARCHAR(255),
    rating INT,
    comment TEXT
);

CREATE TABLE IF NOT EXISTS availability (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    property_id BIGINT,
    blocked_from DATE,
    blocked_to DATE
);

CREATE TABLE IF NOT EXISTS messages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sender_email VARCHAR(255),
    receiver_email VARCHAR(255),
    message TEXT,
    `timestamp` DATETIME
);
