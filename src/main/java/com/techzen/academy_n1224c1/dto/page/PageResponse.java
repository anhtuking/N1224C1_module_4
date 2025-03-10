package com.techzen.academy_n1224c1.dto.page;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse<T>{
    List<T> content;
    PageCustom<T> page;

    public PageResponse(Page<T> page) {
        this.content = page.getContent();
        this.page = new PageCustom<>(page);
    }
}
