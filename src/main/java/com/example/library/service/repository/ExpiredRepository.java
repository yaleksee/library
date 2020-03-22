package com.example.library.service.repository;

import com.example.library.service.model.ReaderExpired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;

public interface ExpiredRepository extends JpaRepository<ReaderExpired, Long>
{

}