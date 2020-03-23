package com.example.library.service;


import com.example.library.service.model.History;
import com.example.library.service.model.Issued;
import com.example.library.service.model.ReaderExpired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public interface HistoryService
{
    History searchOne(@Nonnull Long id);

    List<History> findAll();

    History create(@NotNull History history);

    History update(@NotNull Long historyId, @NotNull History history);

    void delete(@NotNull Long id);

    History findByIssuedId(@NotNull Long id);
}
