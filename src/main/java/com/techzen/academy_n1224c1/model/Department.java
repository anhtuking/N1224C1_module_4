package com.techzen.academy_n1224c1.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(columnDefinition = "varchar(50)")
    String name;
}