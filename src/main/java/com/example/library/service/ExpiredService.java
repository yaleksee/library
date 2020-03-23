package com.example.library.service;


import com.example.library.service.model.Book;
import com.example.library.service.model.ReaderExpired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public interface ExpiredService
{
    ReaderExpired searchOne(@Nonnull Long id);

    List<ReaderExpired> findAll();

    ReaderExpired create(@NotNull ReaderExpired readerExpired);

    void delete(@NotNull Long id);
}
