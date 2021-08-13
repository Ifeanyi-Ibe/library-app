package com.library.Dtos;

import com.library.model.Author;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String isbn;
    private String title;
    private int publicationYear;
   private int availableCopies;
    private List<Author> authors;
}
