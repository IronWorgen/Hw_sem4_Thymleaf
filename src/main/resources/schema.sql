CREATE TABLE IF NOT EXISTS tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstNum INT NOT NULL,
    lastNum INT NOT NULL,
    operation VARCHAR(1) not null,
    result DOUBLE not null,
    timeOfCreation TIME
);