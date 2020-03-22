package com.example.library.service.impl;


import com.example.library.service.BookService;
import com.example.library.service.model.Book;
import com.example.library.service.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImp implements BookService {
    private final BookRepository bookRepository;

    @Nonnull
    public Book searchOne(@Nonnull Long id) {
        return bookRepository.getOne(id);
    }

    @Nonnull
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @NotNull
    public Book create(@NotNull Book book) {
        return bookRepository.save(book);
    }

    @NotNull
    public Book update(@NotNull Book book) {
        return bookRepository.save(book);
    }

    @Override
    public @NotNull Collection<Book> findBookByNameAndIsbn(@Nonnull String name, @Nonnull String isbn) {
        return bookRepository.findBookByNameAndIsbn(name, isbn);
    }

    @Override
    public void delete(@NotNull Book book) {
        bookRepository.delete(book);
    }
}
