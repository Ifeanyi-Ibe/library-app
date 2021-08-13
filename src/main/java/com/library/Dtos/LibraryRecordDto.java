package com.library.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryRecordDto {
    private Long id;
    private String borrower;
    private String book;
    private String status;
    private Instant date;
}
