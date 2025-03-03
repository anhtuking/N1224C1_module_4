package com.techzen.academy_n1224c1.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatBang {
    int maMatBang;
    String tenMatBang;
    String diaChi;
    String dienTich;
    double giaThue;
    Date ngayBatDauThue;
    int loaiMatBang;
}
