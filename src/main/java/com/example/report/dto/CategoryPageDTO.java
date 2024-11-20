package com.example.report.dto;

import com.example.report.model.Category;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPageDTO {
    private List<Category> content;
    private long totalElements;
    private int totalPages;
    private int pageNumber;
    private int pageSize;
}
