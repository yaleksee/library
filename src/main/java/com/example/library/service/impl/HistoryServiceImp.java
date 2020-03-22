package com.example.library.service.impl;


import com.example.library.service.ExpiredService;
import com.example.library.service.HistoryService;
import com.example.library.service.model.History;
import com.example.library.service.model.Issued;
import com.example.library.service.model.ReaderExpired;
import com.example.library.service.repository.ExpiredRepository;
import com.example.library.service.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HistoryServiceImp implements HistoryService {
    private final HistoryRepository historyRepository;

    @Nonnull
    public History searchOne(@Nonnull Long id) {
        return historyRepository.getOne(id);
    }

    @Nonnull
    public List<History> findAll() {
        return historyRepository.findAll();
    }

    @Override
    public History create(@NotNull History history) {
        return historyRepository.save(history);
    }

    @Override
    public History update(@NotNull History history) {
        return historyRepository.save(history);
    }

    @Override
    public void delete(@NotNull History history) {
        historyRepository.delete(history);
    }

    @Override
    public History findByIssued(@NotNull Issued issued) {
        return historyRepository.findByIssued(issued);
    }

    @Nonnull
    public History write(@Nonnull History history) {
        return historyRepository.save(history);
    }
}
