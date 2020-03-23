package com.example.library.service;


import com.example.library.service.model.History;
import com.example.library.service.model.Issued;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public interface IssuedService
{
    Issued searchOne(@Nonnull Long id);

    List<Issued> findAll();

    Issued create(@NotNull Issued issued);

    void delete(@NotNull Long id);
}
