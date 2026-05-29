CREATE TABLE IF NOT EXISTS summary
(
    id INT NOT NULL AUTO_INCREMENT,
    department VARCHAR(50),
    avg_salary INT,
    max_salary INT,
    PRIMARY KEY(id)
    );