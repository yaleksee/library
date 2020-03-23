package com.example.library.service.impl;


import com.example.library.service.ExpiredService;
import com.example.library.service.model.ReaderExpired;
import com.example.library.service.repository.ExpiredRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExpiredServiceImp implements ExpiredService {
    private final ExpiredRepository expiredRepository;

    @Nonnull
    public ReaderExpired searchOne(@Nonnull Long id) {
        return expiredRepository.getOne(id);
    }

    @Nonnull
    public List<ReaderExpired> findAll() {
        return expiredRepository.findAll();
    }

    @Override
    public ReaderExpired create(@NotNull ReaderExpired readerExpired) {
        return expiredRepository.save(readerExpired);
    }

    @Override
    public void delete(@NotNull Long id) {
        expiredRepository.deleteById(id);
    }

    @NotNull
    public ReaderExpired save(@NotNull ReaderExpired readerExpired) {
        return expiredRepository.save(readerExpired);
    }
}
