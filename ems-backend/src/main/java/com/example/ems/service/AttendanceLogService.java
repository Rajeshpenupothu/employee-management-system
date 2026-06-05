package com.example.ems.service;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

import com.example.ems.dto.AttendanceLogDto;

public interface AttendanceLogService {

    AttendanceLogDto createAttendanceLog(
            AttendanceLogDto attendanceLogDto);

    AttendanceLogDto getAttendanceLogById(Long attendanceId);

    Page<AttendanceLogDto> getAllAttendanceLogs(
            Pageable pageable);
    Page<AttendanceLogDto> getAttendanceHistory(
            Long workerId,
            LocalDate from,
            LocalDate to,
            Pageable pageable);

    AttendanceLogDto updateAttendanceLog(
            Long attendanceId,
            AttendanceLogDto attendanceLogDto);

    void deleteAttendanceLog(Long attendanceId);
    
    AttendanceLogDto clockIn(Long workerId, Long siteId);

    AttendanceLogDto clockOut(Long workerId);
    
    java.util.Set<String> getActiveWorkers();
}