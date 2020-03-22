package com.example.library.service.repository;

import com.example.library.service.model.Book;
import com.example.library.service.model.Reader;
import com.example.library.service.model.ReaderExpired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface ExpiredRepository extends JpaRepository<ReaderExpired, Long> {
}