package com.techzen.academy_n1224c1.personnal_management_upgrade;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Department {
    Integer id;
    String name;
}
