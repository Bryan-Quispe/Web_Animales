package com.webanimales.api.controller;

import com.webanimales.api.dto.LostPetReportRequest;
import com.webanimales.api.dto.LostPetReportResponse;
import com.webanimales.api.service.LostPetReportService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lost-pet-reports")
public class LostPetReportController {

    private final LostPetReportService lostPetReportService;

    public LostPetReportController(LostPetReportService lostPetReportService) {
        this.lostPetReportService = lostPetReportService;
    }

    @GetMapping
    public List<LostPetReportResponse> getAll() {
        return lostPetReportService.findAll();
    }

    @GetMapping("/{id}")
    public LostPetReportResponse getById(@PathVariable Long id) {
        return lostPetReportService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LostPetReportResponse create(@RequestBody LostPetReportRequest request) {
        return lostPetReportService.create(request);
    }

    @PutMapping("/{id}")
    public LostPetReportResponse update(@PathVariable Long id, @RequestBody LostPetReportRequest request) {
        return lostPetReportService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        lostPetReportService.delete(id);
    }
}
