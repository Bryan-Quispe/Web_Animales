package com.webanimales.api.service;

import com.webanimales.api.dto.LostPetReportRequest;
import com.webanimales.api.dto.LostPetReportResponse;
import com.webanimales.api.entity.GenericModel3D;
import com.webanimales.api.entity.LostPetReport;
import com.webanimales.api.exception.NotFoundException;
import com.webanimales.api.repository.GenericModel3DRepository;
import com.webanimales.api.repository.LostPetReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LostPetReportService {

    private final LostPetReportRepository lostPetReportRepository;
    private final GenericModel3DRepository genericModel3DRepository;

    public LostPetReportService(LostPetReportRepository lostPetReportRepository,
                                GenericModel3DRepository genericModel3DRepository) {
        this.lostPetReportRepository = lostPetReportRepository;
        this.genericModel3DRepository = genericModel3DRepository;
    }

    public List<LostPetReportResponse> findAll() {
        return lostPetReportRepository.findAll().stream().map(this::toResponse).toList();
    }

    public LostPetReportResponse findById(Long id) {
        LostPetReport report = lostPetReportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("LostPetReport not found: " + id));
        return toResponse(report);
    }

    public LostPetReportResponse create(LostPetReportRequest request) {
        LostPetReport report = new LostPetReport();
        applyRequest(report, request);
        return toResponse(lostPetReportRepository.save(report));
    }

    public LostPetReportResponse update(Long id, LostPetReportRequest request) {
        LostPetReport existing = lostPetReportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("LostPetReport not found: " + id));
        applyRequest(existing, request);
        return toResponse(lostPetReportRepository.save(existing));
    }

    public void delete(Long id) {
        LostPetReport existing = lostPetReportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("LostPetReport not found: " + id));
        lostPetReportRepository.delete(existing);
    }

    private void applyRequest(LostPetReport report, LostPetReportRequest request) {
        GenericModel3D genericModel = genericModel3DRepository.findById(request.getGenericModelId())
                .orElseThrow(() -> new NotFoundException(
                        "GenericModel3D not found: " + request.getGenericModelId()));

        report.setPetName(request.getPetName());
        report.setSpecificDescription(request.getSpecificDescription());
        report.setStatus(request.getStatus());
        report.setLatitude(request.getLatitude());
        report.setLongitude(request.getLongitude());
        report.setGenericModel(genericModel);
    }

    private LostPetReportResponse toResponse(LostPetReport report) {
        LostPetReportResponse response = new LostPetReportResponse();
        response.setId(report.getId());
        response.setPetName(report.getPetName());
        response.setSpecificDescription(report.getSpecificDescription());
        response.setStatus(report.getStatus());
        response.setLatitude(report.getLatitude());
        response.setLongitude(report.getLongitude());
        response.setGenericModelId(report.getGenericModel().getId());
        return response;
    }
}
