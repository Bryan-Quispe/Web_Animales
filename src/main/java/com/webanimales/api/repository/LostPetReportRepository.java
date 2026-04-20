package com.webanimales.api.repository;

import com.webanimales.api.entity.LostPetReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LostPetReportRepository extends JpaRepository<LostPetReport, Long> {
}
