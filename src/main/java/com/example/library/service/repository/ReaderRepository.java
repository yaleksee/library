package com.example.library.service.repository;

import com.example.library.service.model.Book;
import com.example.library.service.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
    @Nullable
    Reader findReaderByFirstNameAndLastName(@Nonnull String firstName, @Nonnull String lastName);
}