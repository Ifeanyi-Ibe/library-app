package com.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String isbn;

    @Column(name = "publication_year")
    private int publicationYear;

    @Column(name = "available_copies")
    private int availableCopies;

    @JsonIgnore
    @ElementCollection(targetClass=Author.class)
    @ManyToMany
    private List<Author> authors = new ArrayList<>();

}
