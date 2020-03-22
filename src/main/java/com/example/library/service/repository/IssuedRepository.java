package com.example.library.service.repository;

import com.example.library.service.model.Book;
import com.example.library.service.model.Issued;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Repository
public interface IssuedRepository extends JpaRepository<Issued, Long> {

}