-- Database schema for Online Voting System
-- Assignment 02

-- Create database
CREATE DATABASE IF NOT EXISTS voting_db;
USE voting_db;

-- Create users table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    dob DATE NOT NULL,
    mobile VARCHAR(15) NOT NULL,
    status BOOLEAN DEFAULT TRUE,
    role VARCHAR(20) DEFAULT 'voter',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create candidates table
CREATE TABLE IF NOT EXISTS candidates (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    party VARCHAR(100) NOT NULL,
    votes INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample admin user
INSERT INTO users (first_name, last_name, email, password, dob, mobile, status, role) 
VALUES ('Admin', 'User', 'admin@voting.com', 'admin123', '1990-01-01', '9999999999', TRUE, 'admin');

-- Insert sample candidates
INSERT INTO candidates (name, party, votes) VALUES 
('Narendra Modi', 'BJP', 150),
('Rahul Gandhi', 'Congress', 120),
('Arvind Kejriwal', 'AAP', 80),
('Mamata Banerjee', 'TMC', 95);

-- Create votes table for tracking user votes
CREATE TABLE IF NOT EXISTS votes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    candidate_id INT NOT NULL,
    voted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (candidate_id) REFERENCES candidates(id),
    UNIQUE KEY unique_user_vote (user_id)
);

-- Show tables
SHOW TABLES;

-- Display sample data
SELECT 'Users Table:' as Info;
SELECT * FROM users;

SELECT 'Candidates Table:' as Info;
SELECT * FROM candidates ORDER BY votes DESC;

