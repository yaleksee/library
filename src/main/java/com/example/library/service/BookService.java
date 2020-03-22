package com.example.library.service;


import com.example.library.service.model.Book;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

@Service
public interface BookService
{
    Book findBookByName(@Nonnull String name);
}
