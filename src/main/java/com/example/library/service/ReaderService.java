package com.example.library.service;


import com.example.library.service.model.Reader;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Service
public interface ReaderService {
    Reader searchOne(@Nonnull Long id);

    List<Reader> findAll();

    Reader create(@NotNull Reader reader);

    Reader update(@NotNull Long readerId, @NotNull Reader reader);

    Collection<Reader> findReaderByFirstNameAndLastName(@Nonnull String firstName, @Nonnull String lastName);

    void delete(@NotNull Long id);
}
