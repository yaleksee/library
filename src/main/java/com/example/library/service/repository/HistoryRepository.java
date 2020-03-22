package com.example.library.service.repository;

import com.example.library.service.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;

public interface HistoryRepository extends JpaRepository<History, Long> {
}