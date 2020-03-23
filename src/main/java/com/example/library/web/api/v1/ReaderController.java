package com.example.library.web.api.v1;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.service.ReaderService;
import com.example.library.service.model.Reader;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/reader")
@RequiredArgsConstructor
public class ReaderController {
    public final ReaderService readerService;

    @GetMapping
    @ApiOperation("get all readers")
    public List<Reader> getAllReaders() {
        return readerService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("get reader by id")
    public Reader getReaderById(@PathVariable(value = "id") Long readerId) throws ResourceNotFoundException {
        return readerService.searchOne(readerId);
    }

    @GetMapping("/search")
    @ApiOperation("get reader by first name and last name")
    public Collection<Reader> getReaderByLogin(
            @RequestParam(value = "firstName", required = false) @Valid String firstName,
            @RequestParam(value = "lastName", required = false) @Valid String lastName
    ) throws ResourceNotFoundException {
        return readerService.findReaderByFirstNameAndLastName(firstName, lastName);
    }

    @PostMapping
    @ApiOperation("create reader")
    public Reader createReader(@Valid @RequestBody Reader reader) {
        return readerService.create(reader);
    }

    @PutMapping("/{readerId}")
    @ApiOperation("update reader")
    public ResponseEntity<Reader> updateReader(@PathVariable Long readerId, @Valid @RequestBody Reader readerDetails) throws ResourceNotFoundException {
        Reader updatedReader = readerService.update(readerId, readerDetails);
        return ResponseEntity.ok(updatedReader);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete reader")
    public String deleteReader(@PathVariable(value = "id") Long id) {
        readerService.delete(id);
        return String.format("Delete %d :successful", id);
    }
}
