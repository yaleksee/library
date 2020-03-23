package com.example.library.web.api.v1;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.service.ExpiredService;
import com.example.library.service.model.ReaderExpired;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/expired")
@RequiredArgsConstructor
public class ExpiredController {
    public final ExpiredService expiredService;

    @GetMapping
    @ApiOperation("get all expireds")
    public List<ReaderExpired> getAllExpireds() {
        return expiredService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("get expired by id")
    public ReaderExpired getExpiredById(@PathVariable(value = "id") Long expiredId) throws ResourceNotFoundException {
        return expiredService.searchOne(expiredId);
    }

    @PostMapping
    @ApiOperation("create expired")
    public ReaderExpired createExpired(@Valid @RequestBody ReaderExpired expired) {
        return expiredService.create(expired);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete expired")
    public String deleteExpired(@PathVariable(value = "id") Long id) {
        expiredService.delete(id);
        return String.format("Delete %d :successful", id);
    }
}
