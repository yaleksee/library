package com.example.library.service.impl;


import com.example.library.service.BookService;
import com.example.library.service.ReaderService;
import com.example.library.service.model.Book;
import com.example.library.service.model.Reader;
import com.example.library.service.repository.BookRepository;
import com.example.library.service.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReaderServiceImp implements ReaderService {
    private final ReaderRepository readerRepository;

    @Nonnull
    public Reader searchOne(@Nonnull Long id) {
        return readerRepository.getOne(id);
    }

    @Nonnull
    public List<Reader> findAll() {
        return readerRepository.findAll();
    }

    @NotNull
    public Reader create(@NotNull Reader reader) {
        return readerRepository.save(reader);
    }

    @NotNull
    public Reader update(@NotNull Reader reader) {
        return readerRepository.save(reader);
    }

    @Override
    @NotNull
    public Reader findReaderByFirstNameAndLastName(@Nonnull String firstName, @Nonnull String lastName) {
        return readerRepository.findReaderByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public void delete(@NotNull Reader reader) {
        readerRepository.delete(reader);
    }
}
