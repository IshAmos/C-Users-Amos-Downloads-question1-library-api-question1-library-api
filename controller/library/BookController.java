package com.example.question1libraryapi.controller.library;

import com.example.question1libraryapi.model.library.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    // Constructor - initialize 3 sample books
    public BookController() {
        books.add(new Book(1L, "Clean Code", "Robert Martin", "978-0132350884", 2008));
        books.add(new Book(2L, "Effective Java", "Joshua Bloch", "978-0134685991", 2018));
        books.add(new Book(3L, "Spring in Action", "Craig Walls", "978-1617294945", 2018));
    }

    // GET all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // GET book by ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {

        for (Book book : books) {
            if (book.getId().equals(id)) {
                return new ResponseEntity<>(book, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Search by title
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchByTitle(@RequestParam String title) {

        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Add new book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book newBook) {

        books.add(newBook);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    // Delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {

        for (Book book : books) {
            if (book.getId().equals(id)) {
                books.remove(book);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
