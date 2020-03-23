package com.example.library.service.impl;


import com.example.library.service.HistoryService;
import com.example.library.service.IssuedService;
import com.example.library.service.model.History;
import com.example.library.service.model.Issued;
import com.example.library.service.repository.HistoryRepository;
import com.example.library.service.repository.IssuedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IssuedServiceImp implements IssuedService {
    private final IssuedRepository issuedRepository;

    @Nonnull
    public Issued searchOne(@Nonnull Long id) {
        return issuedRepository.getOne(id);
    }

    @Nonnull
    public List<Issued> findAll() {
        return issuedRepository.findAll();
    }

    @Override
    public Issued create(@NotNull Issued issued) {
        return issuedRepository.save(issued);
    }

    @Override
    public void delete(@NotNull Long id) {
        issuedRepository.deleteById(id);
    }

    @Nonnull
    public Issued giveOutBook(@Nonnull Issued issued) {
        return issuedRepository.save(issued);
    }

    @Nonnull
    public void takeBookBack(@Nonnull Issued issued) {
        issuedRepository.delete(issued);
    }
}
