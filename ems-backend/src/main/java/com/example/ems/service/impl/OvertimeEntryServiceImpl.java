package com.example.ems.service.impl;

import com.example.ems.dto.OvertimeEntryDto;
import com.example.ems.entity.OvertimeEntry;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.mapper.OvertimeEntryMapper;
import com.example.ems.repository.OvertimeEntryRepository;
import com.example.ems.service.OvertimeEntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.ems.entity.SettlementStatus;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OvertimeEntryServiceImpl implements OvertimeEntryService {

    @Autowired
    private OvertimeEntryRepository overtimeEntryRepository;

    @Override
    @Transactional
    public OvertimeEntryDto createOvertimeEntry(
            OvertimeEntryDto overtimeEntryDto) {

        java.time.YearMonth yearMonth =
                java.time.YearMonth.from(
                        overtimeEntryDto.getWorkDate());

        java.time.LocalDate startDate =
                yearMonth.atDay(1);

        java.time.LocalDate endDate =
                yearMonth.atEndOfMonth();

        java.util.List<OvertimeEntry> existingEntries =
                overtimeEntryRepository
                        .findByWorkerIdAndWorkDateBetween(
                                overtimeEntryDto.getWorkerId(),
                                startDate,
                                endDate);

        double currentMonthlyHours =
                existingEntries.stream()
                        .mapToDouble(
                                entry -> entry.getOvertimeHours())
                        .sum();

        double requestedHours =
                overtimeEntryDto.getOvertimeHours();

        double remainingHours =
                60 - currentMonthlyHours;

        if (remainingHours <= 0) {

            overtimeEntryDto.setOvertimeHours(0.0);
            overtimeEntryDto.setOvertimeAmount(
                    java.math.BigDecimal.ZERO);

        } else if (requestedHours > remainingHours) {

            double ratio =
                    remainingHours / requestedHours;

            overtimeEntryDto.setOvertimeHours(
                    remainingHours);

            overtimeEntryDto.setOvertimeAmount(
                    overtimeEntryDto.getOvertimeAmount()
                            .multiply(
                                    java.math.BigDecimal.valueOf(
                                            ratio)));
        }

        OvertimeEntry overtimeEntry =
                OvertimeEntryMapper.mapToOvertimeEntry(
                        overtimeEntryDto);

        OvertimeEntry savedOvertimeEntry =
                overtimeEntryRepository.save(
                        overtimeEntry);

        return OvertimeEntryMapper.mapToOvertimeEntryDto(
                savedOvertimeEntry);
    }

    @Override
    public OvertimeEntryDto getOvertimeEntryById(Long overtimeId) {

        OvertimeEntry overtimeEntry =
                overtimeEntryRepository.findById(overtimeId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Overtime Entry not found with id: "
                                                + overtimeId));

        return OvertimeEntryMapper.mapToOvertimeEntryDto(
                overtimeEntry);
    }

    @Override
    public List<OvertimeEntryDto> getAllOvertimeEntries() {

        List<OvertimeEntry> overtimeEntries =
                overtimeEntryRepository.findAll();

        return overtimeEntries.stream()
                .map(OvertimeEntryMapper::mapToOvertimeEntryDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOvertimeEntry(Long overtimeId) {

        OvertimeEntry overtimeEntry =
                overtimeEntryRepository.findById(overtimeId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Overtime Entry not found with id: "
                                                + overtimeId));

        overtimeEntryRepository.deleteById(overtimeId);
    }
    @Override
    public Map<String, Object> getOvertimeSummary(
            Long workerId,
            String month) {

        YearMonth yearMonth = YearMonth.parse(month);

        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        var entries =
                overtimeEntryRepository
                        .findByWorkerIdAndWorkDateBetween(
                                workerId,
                                startDate,
                                endDate);

        double totalHours = entries.stream()
                .mapToDouble(
                        entry -> entry.getOvertimeHours().doubleValue())
                .sum();

        double totalAmount = entries.stream()
                .mapToDouble(
                        entry -> entry.getOvertimeAmount().doubleValue())
                .sum();

        Map<String, Object> summary = new HashMap<>();

        summary.put("workerId", workerId);
        summary.put("month", month);
        summary.put("totalOvertimeHours", totalHours);
        summary.put("totalOvertimeAmount", totalAmount);
        summary.put("entries", entries);

        return summary;
    }
    @Override
    @Transactional
    public Map<String, Object> settleOvertime(
            Long workerId,
            String month) {

        YearMonth yearMonth = YearMonth.parse(month);

        if (yearMonth.equals(YearMonth.now())) {
            throw new RuntimeException(
                    "Current month cannot be settled");
        }

        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        List<OvertimeEntry> entries =
                overtimeEntryRepository
                        .findByWorkerIdAndStatusAndWorkDateBetween(
                                workerId,
                                SettlementStatus.PENDING,
                                startDate,
                                endDate);

        double totalAmount = 0;

        for (OvertimeEntry entry : entries) {

            entry.setStatus(SettlementStatus.SETTLED);

            totalAmount +=
                    entry.getOvertimeAmount().doubleValue();
        }

        overtimeEntryRepository.saveAll(entries);

        Map<String, Object> result = new HashMap<>();

        result.put("workerId", workerId);
        result.put("month", month);
        result.put("settledEntries", entries.size());
        result.put("totalAmount", totalAmount);
        result.put("status", "SETTLED");

        return result;
    }
}