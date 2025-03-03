CREATE DATABASE rental;
USE rental;

CREATE TABLE LoaiMatBang
(
    id      INT PRIMARY KEY AUTO_INCREMENT,
    tenLoai VARCHAR(255) NOT NULL
);

CREATE TABLE MatBang
(
    maMatBang     VARCHAR(255) PRIMARY KEY,
    tenMatBang    VARCHAR(255) NOT NULL,
    diaChi        VARCHAR(255) NOT NULL,
    dienTich      VARCHAR(255) NOT NULL,
    loaiMatBangId INT,
    giaThue       DOUBLE       NOT NULL,
    ngayBatDau    DATE         NOT NULL,
    FOREIGN KEY (loaiMatBangId) REFERENCES LoaiMatBang (id)
);

INSERT INTO LoaiMatBang (tenLoai)
VALUES ('Văn phòng'),
       ('Cửa hàng'),
       ('Nhà ở');

INSERT INTO MatBang (maMatBang, tenMatBang, diaChi, dienTich, loaiMatBangId, giaThue, ngayBatDau)
VALUES ('MB001', 'Văn phòng A', '123 Đường ABC', '500m2', 1, 5000000, '2023-10-01'),
       ('MB002', 'Cửa hàng B', '456 Đường XYZ', '300m2', 2, 3000000, '2023-09-15'),
       ('MB003', 'Nhà ở C', '789 Đường DEF', '1000m2', 3, 8000000, '2023-11-01');
