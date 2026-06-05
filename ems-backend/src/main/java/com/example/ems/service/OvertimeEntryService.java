package com.example.ems.service;

import java.util.List;

import com.example.ems.dto.OvertimeEntryDto;

public interface OvertimeEntryService {

    OvertimeEntryDto createOvertimeEntry(
            OvertimeEntryDto overtimeEntryDto);

    OvertimeEntryDto getOvertimeEntryById(Long overtimeId);

    List<OvertimeEntryDto> getAllOvertimeEntries();

    void deleteOvertimeEntry(Long overtimeId);
    java.util.Map<String, Object> getOvertimeSummary(
            Long workerId,
            String month);
    java.util.Map<String, Object> settleOvertime(
            Long workerId,
            String month);
}