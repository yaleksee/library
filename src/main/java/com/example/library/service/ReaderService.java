package com.example.library.service;


import com.example.library.service.model.Reader;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

@Service
public interface ReaderService {
    Reader findReaderByFirstNameAndLastName(@Nonnull String firstName, @Nonnull String lastName);
}
