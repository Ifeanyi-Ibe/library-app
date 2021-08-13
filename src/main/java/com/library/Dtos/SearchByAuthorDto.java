package com.library.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchByAuthorDto {
    private Long id;
    private String name;
}
