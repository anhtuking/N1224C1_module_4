package com.techzen.academy_n1224c1.model;

import com.techzen.academy_n1224c1.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(columnDefinition = "varchar(50)")
    String name;

    LocalDate dob;
    @Column(columnDefinition = "ENUM ('MALE', 'FEMALE')")
    @Enumerated(EnumType.STRING)
    Gender gender;
    String phone;
    double salary;

    @OneToOne(fetch = FetchType.EAGER)
    Department department;
}