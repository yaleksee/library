package com.example.library.web.api.v1;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.service.HistoryService;
import com.example.library.service.model.History;
import com.example.library.service.model.Reader;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {
    public final HistoryService historyService;

    @GetMapping
    @ApiOperation("get all historys")
    public List<History> getAllHistorys() {
        return historyService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("get history by id")
    public History getHistoryById(@PathVariable(value = "id") Long historyId) throws ResourceNotFoundException {
        return historyService.searchOne(historyId);
    }

    @GetMapping("/issued/{id}")
    @ApiOperation("get history by issuedId")
    public History findByIssuedId(@PathVariable(value = "id") Long issuedId) throws ResourceNotFoundException {
        return historyService.findByIssuedId(issuedId);
    }

    @PostMapping
    @ApiOperation("create history")
    public History createHistory(@Valid @RequestBody History history) {
        return historyService.create(history);
    }

    @PutMapping("/{historyId}")
    @ApiOperation("update history")
    public ResponseEntity<History> updateReader(@PathVariable Long historyId, @Valid @RequestBody History historyDetails) throws ResourceNotFoundException {
        History updatedHistory = historyService.update(historyId, historyDetails);
        return ResponseEntity.ok(updatedHistory);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete history")
    public String deleteHistory(@PathVariable(value = "id") Long id) {
        historyService.delete(id);
        return String.format("Delete %d :successful", id);
    }
}
