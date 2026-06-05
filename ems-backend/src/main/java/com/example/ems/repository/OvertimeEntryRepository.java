package com.example.ems.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ems.entity.OvertimeEntry;
import com.example.ems.entity.SettlementStatus;

public interface OvertimeEntryRepository
        extends JpaRepository<OvertimeEntry, Long> {

    List<OvertimeEntry> findByWorkerId(Long workerId);

    List<OvertimeEntry> findByWorkerIdAndWorkDateBetween(
            Long workerId,
            LocalDate startDate,
            LocalDate endDate);

    List<OvertimeEntry> findByWorkerIdAndStatusAndWorkDateBetween(
            Long workerId,
            SettlementStatus status,
            LocalDate startDate,
            LocalDate endDate);
}