package com.library.serviceImpl;

import com.library.Dtos.*;
import com.library.enums.BookStatus;
import com.library.mappers.MapStructMapper;
import com.library.model.Author;
import com.library.model.Book;
import com.library.model.LibraryRecord;
import com.library.repositories.AuthorRepository;
import com.library.repositories.BookRepository;
import com.library.repositories.LibraryRecordRepository;
import com.library.repositories.MemberRepository;
import com.library.service.BookService;
import com.library.service.LibraryRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final LibraryRecordService libraryRecordService;
    @Autowired
    private final LibraryRecordRepository libraryRecordRepository;
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final AuthorRepository authorRepository;
    private final MapStructMapper mapper;

    @Override
    public ResponseEntity<?> getBooks() {
        List<BookDto> books = mapper.booksToBookDtos(bookRepository.findAll());
        if (books.isEmpty()) {
            return ResponseEntity.status(404).body("No books were found.");
        }
        return ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<?> getBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            return ResponseEntity.status(404).body("Book with id: " + id + " does not exist.");
        }
        return ResponseEntity.ok(book.get());
    }

    @Override
    public ResponseEntity<?> addBook(BookDto bookDto) {
        Optional<Book> book = bookRepository.findByIsbn(bookDto.getIsbn());
        if (book.isPresent()) {
            return ResponseEntity.status(422).body("Book already exists");
        }
        List<Author> authors = bookDto.getAuthors();
        authors.forEach(authorRepository::save);
        bookRepository.save(mapper.bookDtoToBook(bookDto));
        return ResponseEntity.ok("Book added successfully.");
    }

    @Override
    public ResponseEntity<?> updateBook(Long id, BookDto bookDto) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            return ResponseEntity.status(404).body("Book with id: " + id + " does not exist.");
        }
        mapper.updateBookFromDto(bookDto, book.get());
        bookRepository.save(book.get());
        return ResponseEntity.ok(book.get());
    }

    @Override
    public void deleteBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(bookRepository::delete);
    }

    @Override
    public ResponseEntity<?> lendBook(Long bookId, MemberDto memberDto) {
        Long memberId = memberRepository.findByEmail(memberDto.getEmail()).get().getId();
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()) {
            return ResponseEntity.status(404).body("Book with id: " + bookId + " does not exist.");
        }
        if (book.get().getAvailableCopies() < 1) {
            return ResponseEntity.status(404).body("Book with id: " + bookId + " is not available at the moment.");
        }
        book.get().setAvailableCopies(book.get().getAvailableCopies() - 1);
        libraryRecordRepository.save(mapper.recordToRecordDto(libraryRecordService.addRecord(memberId, book.get())));
        return ResponseEntity.ok(book.get().getTitle() + " has been successfully borrowed by " + " " + memberDto.getFirstName());
    }

    @Override
    public ResponseEntity<?> searchByTitle(SearchByTitleDto searchByTitleDto) {
        String title = searchByTitleDto.getTitle();
        List<BookDto> books = bookRepository.findAll().stream()
                .filter(book -> book.getTitle().contains(title))
                .map(mapper::bookToBookDto)
                .collect(Collectors.toList());

        if (books.isEmpty()) {
            return ResponseEntity.status(404).body("No match was found");
        }
        return ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<?> searchByAuthor(SearchByAuthorDto searchByAuthorDto) {
        String author = searchByAuthorDto.getName();
        List<Book> books = bookRepository.findAll();

        List<Book> matches = new ArrayList<>();
        for (Book book : books) {
            List<Author> authors = book.getAuthors();
            for (Author author1 : authors) {
                if (author1.getName().equalsIgnoreCase(author)) {
                    matches.add(book);
                }
            }
            }

            if (matches.isEmpty()) {
                return ResponseEntity.status(404).body("No match was found");
            }
            return ResponseEntity.ok(matches);
        }

    }

