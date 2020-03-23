package com.example.library.service.impl;


import com.example.library.service.ExpiredService;
import com.example.library.service.HistoryService;
import com.example.library.service.IssuedService;
import com.example.library.service.model.Book;
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
    private final IssuedService issuedService;

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

    @NotNull
    public History update(@NotNull Long historyId, @NotNull History history) {
        final History currentHistory = historyRepository.getOne(historyId);
        currentHistory.setTimeReturn(history.getTimeReturn());
        return historyRepository.save(currentHistory);
    }

    @Override
    public void delete(@NotNull Long id) {
        historyRepository.deleteById(id);
    }

    @Override
    public History findByIssuedId(@NotNull Long issuedId) {
        final Issued issued = issuedService.searchOne(issuedId);
        return historyRepository.findByIssued_Id(issued.getId());
    }

    @Nonnull
    public History write(@Nonnull History history) {
        return historyRepository.save(history);
    }
}
