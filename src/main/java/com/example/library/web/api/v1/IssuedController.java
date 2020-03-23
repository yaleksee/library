package com.example.library.web.api.v1;

import com.example.library.exception.ResourceNotFoundException;
import com.example.library.service.IssuedService;
import com.example.library.service.model.Issued;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/issued")
@RequiredArgsConstructor
public class IssuedController {
    public final IssuedService issuedService;

    @GetMapping
    @ApiOperation("get all issueds")
    public List<Issued> getAllIssueds() {
        return issuedService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("get issued by id")
    public Issued getIssuedById(@PathVariable(value = "id") Long issuedId) throws ResourceNotFoundException {
        return issuedService.searchOne(issuedId);
    }

    @PostMapping
    @ApiOperation("create issued")
    public Issued createIssued(@Valid @RequestBody Issued issued) {
        return issuedService.create(issued);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("delete issued")
    public String deleteIssued(@PathVariable(value = "id") Long id) {
        issuedService.delete(id);
        return String.format("Delete %d :successful", id);
    }
}
