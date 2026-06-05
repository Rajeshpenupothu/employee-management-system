package com.example.ems.mapper;

import com.example.ems.dto.AttendanceLogDto;
import com.example.ems.entity.AttendanceLog;

public class AttendanceLogMapper {

    public static AttendanceLogDto mapToAttendanceLogDto(
            AttendanceLog attendanceLog) {

        AttendanceLogDto dto = new AttendanceLogDto();

        dto.setId(attendanceLog.getId());
        dto.setWorkerId(attendanceLog.getWorkerId());
        dto.setSiteId(attendanceLog.getSiteId());
        dto.setAttendanceDate(attendanceLog.getAttendanceDate());
        dto.setClockInTime(attendanceLog.getClockInTime());
        dto.setClockOutTime(attendanceLog.getClockOutTime());
        dto.setAnomalyFlag(attendanceLog.getAnomalyFlag());

        return dto;
    }

    public static AttendanceLog mapToAttendanceLog(
            AttendanceLogDto dto) {

        AttendanceLog attendanceLog = new AttendanceLog();

        attendanceLog.setId(dto.getId());
        attendanceLog.setWorkerId(dto.getWorkerId());
        attendanceLog.setSiteId(dto.getSiteId());
        attendanceLog.setAttendanceDate(dto.getAttendanceDate());
        attendanceLog.setClockInTime(dto.getClockInTime());
        attendanceLog.setClockOutTime(dto.getClockOutTime());
        attendanceLog.setAnomalyFlag(dto.getAnomalyFlag());

        return attendanceLog;
    }
}