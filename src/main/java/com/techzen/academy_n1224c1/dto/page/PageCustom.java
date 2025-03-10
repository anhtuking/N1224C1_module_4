package com.techzen.academy_n1224c1.dto.page;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageCustom<T> {
    int number;
    int size;
    int totalElements;
    int totalPages;

    public PageCustom(Page<T> page) {
        this.number = page.getNumber();
        this.size = page.getSize();
        this.totalElements = page.getNumberOfElements();
        this.totalPages = page.getTotalPages();
    }
}
