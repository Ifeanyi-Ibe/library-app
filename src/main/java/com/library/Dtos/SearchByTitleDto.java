package com.library.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchByTitleDto {
    private Long id;
    private String title;
}
