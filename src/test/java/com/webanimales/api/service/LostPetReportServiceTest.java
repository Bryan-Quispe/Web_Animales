package com.webanimales.api.service;

import com.webanimales.api.dto.LostPetReportRequest;
import com.webanimales.api.dto.LostPetReportResponse;
import com.webanimales.api.entity.GenericModel3D;
import com.webanimales.api.entity.LostPetReport;
import com.webanimales.api.exception.NotFoundException;
import com.webanimales.api.repository.GenericModel3DRepository;
import com.webanimales.api.repository.LostPetReportRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LostPetReportServiceTest {

    @Mock
    private LostPetReportRepository lostPetReportRepository;

    @Mock
    private GenericModel3DRepository genericModel3DRepository;

    @InjectMocks
    private LostPetReportService lostPetReportService;

    @Test
    void createShouldLinkGenericModelById() {
        LostPetReportRequest request = new LostPetReportRequest();
        request.setPetName("Luna");
        request.setSpecificDescription("White spot on chest");
        request.setStatus("LOST");
        request.setLatitude(-12.0464);
        request.setLongitude(-77.0428);
        request.setGenericModelId(7L);

        GenericModel3D model = new GenericModel3D();
        model.setId(7L);

        when(genericModel3DRepository.findById(7L)).thenReturn(Optional.of(model));
        when(lostPetReportRepository.save(any(LostPetReport.class))).thenAnswer(invocation -> {
            LostPetReport report = invocation.getArgument(0);
            report.setId(10L);
            return report;
        });

        LostPetReportResponse response = lostPetReportService.create(request);

        assertEquals(10L, response.getId());
        assertEquals(7L, response.getGenericModelId());
        assertEquals("Luna", response.getPetName());
    }

    @Test
    void createShouldFailWhenGenericModelDoesNotExist() {
        LostPetReportRequest request = new LostPetReportRequest();
        request.setGenericModelId(999L);

        when(genericModel3DRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> lostPetReportService.create(request));
    }

    @Test
    void updateShouldReplaceFieldsAndGenericModel() {
        LostPetReportRequest request = new LostPetReportRequest();
        request.setPetName("Rocky");
        request.setSpecificDescription("Blue collar");
        request.setStatus("FOUND");
        request.setLatitude(-12.05);
        request.setLongitude(-77.03);
        request.setGenericModelId(2L);

        GenericModel3D currentModel = new GenericModel3D();
        currentModel.setId(1L);
        GenericModel3D newModel = new GenericModel3D();
        newModel.setId(2L);

        LostPetReport existing = new LostPetReport();
        existing.setId(12L);
        existing.setPetName("Old");
        existing.setSpecificDescription("Old desc");
        existing.setStatus("LOST");
        existing.setLatitude(-1.0);
        existing.setLongitude(-1.0);
        existing.setGenericModel(currentModel);

        when(lostPetReportRepository.findById(12L)).thenReturn(Optional.of(existing));
        when(genericModel3DRepository.findById(2L)).thenReturn(Optional.of(newModel));
        when(lostPetReportRepository.save(any(LostPetReport.class))).thenAnswer(invocation -> invocation.getArgument(0));

        LostPetReportResponse response = lostPetReportService.update(12L, request);

        assertEquals(12L, response.getId());
        assertEquals("Rocky", response.getPetName());
        assertEquals("Blue collar", response.getSpecificDescription());
        assertEquals("FOUND", response.getStatus());
        assertEquals(2L, response.getGenericModelId());
    }
}
//hola