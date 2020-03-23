package com.example.library.service;


import com.example.library.service.model.Book;
import com.example.library.service.model.Issued;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Service
public interface BookService {
    Book searchOne(@Nonnull Long id);

    List<Book> findAll();

    Book create(@NotNull Book book);

    Book update(@NotNull Long bookId, @NotNull Book book);

    @NotNull Collection<Book> findBookByNameAndIsbn(@Nonnull String name, @Nonnull String isbm);

    void delete(@NotNull Long bookId);
}
