CREATE TABLE job (
                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                     title VARCHAR(50) NOT NULL,
                     location VARCHAR(50) NOT NULL
);

CREATE TABLE client (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        email VARCHAR(255) NOT NULL UNIQUE,
                        api_key VARCHAR(255)
);