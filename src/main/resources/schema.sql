DROP TABLE IF EXISTS tbl_tasks;

CREATE TABLE tbl_tasks (id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(100) NOT NULL,
                        description TEXT(250) NOT NULL,
                        priority ENUM('LOW', 'MEDIUM', 'HIGH'),
                        status ENUM('COMPLETED', 'IN_PROGRESS', 'TO_DO'),
                        creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)