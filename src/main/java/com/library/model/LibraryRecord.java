package com.library.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "library_record")
public class LibraryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String borrower;

    @Column
    private String book;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookStatus status;

    @CreatedDate
    @Column(name = "date_borrowed")
    @JsonIgnore
    private Instant dateBorrowed = Instant.now();
}
