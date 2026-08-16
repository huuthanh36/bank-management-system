
-- Tạo database
CREATE DATABASE bank_management
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- Chọn database
USE bank_management;

-- Tạo table users
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Tạo table roles
INSERT INTO roles(name)
VALUES
('ROLE_ADMIN'),
('ROLE_CUSTOMER');

-- Insert data table roles
INSERT INTO roles(name)
VALUES
('ROLE_ADMIN'),
('ROLE_CUSTOMER');

-- Tạo table user-roles
CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- Tạo table customers
CREATE TABLE customers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(255),
    identity_number VARCHAR(20) UNIQUE,
    date_of_birth DATE,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_customer_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Tạo bảng accounts
CREATE TABLE accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    account_number VARCHAR(20) NOT NULL UNIQUE,
    balance DECIMAL(19,2) NOT NULL DEFAULT 0.00,
    account_type VARCHAR(20) NOT NULL DEFAULT 'SAVINGS',
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    version BIGINT NOT NULL DEFAULT 0,
    
    CONSTRAINT fk_account_customer FOREIGN KEY (customer_id) REFERENCES customers(id)
);

-- Tạo bảng transactions
CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    transaction_code VARCHAR(50) NOT NULL UNIQUE,
    from_account_id BIGINT,
    to_account_id BIGINT,
    amount DECIMAL(19,2) NOT NULL,
    transaction_type VARCHAR(30) NOT NULL,
    status VARCHAR(20) NOT NULL,
    description VARCHAR(255),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_transaction_from_account FOREIGN KEY (from_account_id) REFERENCES accounts(id),

    CONSTRAINT fk_transaction_to_account FOREIGN KEY (to_account_id) REFERENCES accounts(id)
);


-- Tạo bảng refresh_tokens
CREATE TABLE refresh_tokens (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    token VARCHAR(500) NOT NULL UNIQUE,
    expiry_date DATETIME NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_refresh_token_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Tạo bảng audit_logs
CREATE TABLE audit_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    action VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_audit_user FOREIGN KEY (user_id) REFERENCES users(id)
);