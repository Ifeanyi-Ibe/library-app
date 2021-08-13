package com.library.controllers;

import com.library.Dtos.*;
import com.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("api/v1/library/books")
@RestController
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<?> getBooks() {
       return this.bookService.getBooks();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable("bookId") Long id) {
        return this.bookService.getBook(id);
    }

    @PostMapping("/title")
    public ResponseEntity<?> searchByTitle(@RequestBody SearchByTitleDto bookDto) {
        return this.bookService.searchByTitle(bookDto);
    }

    @PostMapping("/author")
    public ResponseEntity<?> searchByAuthor(@RequestBody SearchByAuthorDto searchByAuthorDto) {
        return this.bookService.searchByAuthor(searchByAuthorDto);
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookDto bookDto) {
        return this.bookService.addBook(bookDto);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable("bookId") Long id, @RequestBody BookDto bookDto) {
        return this.bookService.updateBook(id, bookDto);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBook(@PathVariable("bookId") Long id) {
        this.bookService.deleteBook(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> lendBook(@PathVariable("id") Long bookId, @RequestBody MemberDto memberDto) {
        return this.bookService.lendBook(bookId, memberDto);
    }
}
