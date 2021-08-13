package com.library.service;

import com.library.Dtos.AuthorDto;
import org.springframework.http.ResponseEntity;

public interface AuthorService {
    public ResponseEntity<?> getAuthors();
    public ResponseEntity<?> getAuthor(Long id);
    public ResponseEntity<?> addAuthor(AuthorDto authorDto);
    public ResponseEntity<?> update(Long id, AuthorDto authorDto);
    public ResponseEntity<?> remove(Long id);
}
