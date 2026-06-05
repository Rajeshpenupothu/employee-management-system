package com.example.ems.mapper;

import com.example.ems.dto.OvertimeEntryDto;
import com.example.ems.entity.OvertimeEntry;

public class OvertimeEntryMapper {

    public static OvertimeEntryDto mapToOvertimeEntryDto(
            OvertimeEntry overtimeEntry) {

        OvertimeEntryDto dto = new OvertimeEntryDto();

        dto.setId(overtimeEntry.getId());
        dto.setWorkerId(overtimeEntry.getWorkerId());
        dto.setWorkDate(overtimeEntry.getWorkDate());
        dto.setOvertimeHours(overtimeEntry.getOvertimeHours());
        dto.setOvertimeAmount(overtimeEntry.getOvertimeAmount());

        return dto;
    }

    public static OvertimeEntry mapToOvertimeEntry(
            OvertimeEntryDto dto) {

        OvertimeEntry overtimeEntry = new OvertimeEntry();

        overtimeEntry.setId(dto.getId());
        overtimeEntry.setWorkerId(dto.getWorkerId());
        overtimeEntry.setWorkDate(dto.getWorkDate());
        overtimeEntry.setOvertimeHours(dto.getOvertimeHours());
        overtimeEntry.setOvertimeAmount(dto.getOvertimeAmount());

        return overtimeEntry;
    }
}