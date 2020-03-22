package com.example.library.service.repository;

import com.example.library.service.model.History;
import com.example.library.service.model.Issued;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.awt.print.Book;

public interface HistoryRepository extends JpaRepository<History, Long> {
    History findByIssued(@NotNull Issued issued);
}