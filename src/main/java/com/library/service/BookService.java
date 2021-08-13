package com.library.service;

import com.library.Dtos.*;
import com.library.model.Author;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {
    public ResponseEntity<?> getBooks();
    public ResponseEntity<?> getBook(Long id);
    public ResponseEntity<?> addBook(BookDto bookDto);
    public ResponseEntity<?> updateBook(Long id, BookDto bookDto);
    public void deleteBook(Long id);
    public ResponseEntity<?> lendBook(Long bookId, MemberDto memberDto);
    public ResponseEntity<?> searchByTitle(SearchByTitleDto searchByTitleDto);
    public ResponseEntity<?> searchByAuthor(SearchByAuthorDto searchByAuthorDto);
    //public ResponseEntity<?> addAuthor(Long id, AddAuthorDto dto);
}
