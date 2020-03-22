package com.example.library.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.io.Reader;

public interface ReaderRepository extends JpaRepository<Reader, Long> {

}