use employee_management;

CREATE TABLE department
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

INSERT INTO department (id, name)
VALUES (1, 'Management'),
       (2, 'Accounting'),
       (3, 'Sale-Marketing'),
       (4, 'Production');

CREATE TABLE employee
(
    id            INT AUTO_INCREMENT      NOT NULL,
    name          VARCHAR(50)             NULL,
    dob           date                    NULL,
    gender        ENUM ('MALE', 'FEMALE') NULL,
    phone         VARCHAR(255)            NULL,
    salary        DOUBLE                  NOT NULL,
    department_id INT                     NULL,
    CONSTRAINT pk_employee PRIMARY KEY (id)
);

ALTER TABLE employee
    ADD CONSTRAINT FK_EMPLOYEE_ON_DEPARTMENT FOREIGN KEY (department_id) REFERENCES department (id);

INSERT INTO employee (id, name, dob, gender, salary, phone, department_id)
VALUES (1, 'Dương Anh Tú', '2005-01-15', 'MALE', 15000000.00, '0975123542', 1),
       (2, 'Nguyễn Vân Kiều', '2003-05-20', 'FEMALE', 14500000.00, '0967869868', 2),
       (3, 'Đôi Văn Môi', '1992-03-10', 'MALE', 15500000.00, '0988881110', 3),
       (4, 'Tèo Văn Búng', '2000-07-05', 'FEMALE', 14800000.00, '0986555333', 4),
       (5, 'Nguyễn Thị Hoa', '1995-09-25', 'MALE', 15200000.00, '0973388668', 4);

SELECT e.* FROM employee e
LEFT JOIN department d ON e.department_id = d.id
WHERE e.name LIKE '%e%'
  AND e.dob >= '1998-01-01'
  AND e.dob <= '2005-01-01'
  AND e.gender = 'FEMALE'
  AND e.phone LIKE '%68%'
  AND d.id = 2;