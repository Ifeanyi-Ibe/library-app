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
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @JsonIgnore
   @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();

    @Override
    public String toString() {
        return this.getName();
    }
}
