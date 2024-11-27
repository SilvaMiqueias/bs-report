package com.example.report.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private BigDecimal price;
    private String title;
    private String description;
    private Integer qtd;
    private Integer page;
    private String language;
    private String code;
    private byte[] image;
    private LocalDateTime releaseDate;
    private List<AuthorDTO> authors;
    private List<CategoryDTO> categories;

}
