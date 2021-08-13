package com.library.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddAuthorDto {
    private Long id;
    private Long authorId;
}
