package com.example.library.service;


import com.example.library.service.model.Issued;
import com.example.library.service.model.Reader;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public interface ReaderService {
    Reader searchOne(@Nonnull Long id);

    List<Reader> findAll();

    Reader create(@NotNull Reader reader);

    Reader update(@NotNull Reader reader);

    Reader findReaderByFirstNameAndLastName(@Nonnull String firstName, @Nonnull String lastName);

    void delete(@NotNull Reader reader);
}
