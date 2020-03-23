package com.example.library.web.api.v1;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.service.BookService;
import com.example.library.service.model.Book;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {
    public final BookService bookService;

    @GetMapping
    @ApiOperation("get all books")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("get book by id")
    public Book getBookById(@PathVariable(value = "id") Long bookId) throws ResourceNotFoundException {
        return bookService.searchOne(bookId);
    }

    @GetMapping("/search")
    @ApiOperation("get book by name and isbn")
    public Collection<Book> getBookByLogin(
            @RequestParam(value = "name", required = false) @Valid String name,
            @RequestParam(value = "isbn", required = false) @Valid String isbn
    ) throws ResourceNotFoundException {
        return bookService.findBookByNameAndIsbn(name, isbn);
    }

    @PostMapping
    @ApiOperation("create book")
    public Book createBook(@Valid @RequestBody Book book) {
        return bookService.create(book);
    }

    @PutMapping("/{bookId}")
    @ApiOperation("update book")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @Valid @RequestBody Book bookDetails) throws ResourceNotFoundException {
        Book updatedBook = bookService.update(bookId, bookDetails);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete book")
    public String deleteBook(@PathVariable(value = "id") Long id) {
        bookService.delete(id);
        return String.format("Delete %d :successful", id);
    }
}
