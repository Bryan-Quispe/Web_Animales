package com.webanimales.api.service;

import com.webanimales.api.dto.LostPetReportRequest;
import com.webanimales.api.dto.LostPetReportResponse;
import com.webanimales.api.entity.GenericModel3D;
import com.webanimales.api.entity.LostPetReport;
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

        assertThrows(IllegalArgumentException.class, () -> lostPetReportService.create(request));
    }
}
