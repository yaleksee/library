package com.example.library.service.impl;


import com.example.library.service.ExpiredService;
import com.example.library.service.HistoryService;
import com.example.library.service.model.History;
import com.example.library.service.model.ReaderExpired;
import com.example.library.service.repository.ExpiredRepository;
import com.example.library.service.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
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

    @Nonnull
    public History write(@Nonnull History history) {
        return historyRepository.save(history);
    }
}
