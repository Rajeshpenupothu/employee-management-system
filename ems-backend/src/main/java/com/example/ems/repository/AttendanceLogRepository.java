package com.example.ems.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ems.entity.AttendanceLog;

import java.time.LocalDate;
import java.util.Optional;

public interface AttendanceLogRepository
        extends JpaRepository<AttendanceLog, Long> {

    List<AttendanceLog> findByWorkerId(Long workerId);

    List<AttendanceLog> findBySiteId(Long siteId);
    Optional<AttendanceLog> findByWorkerIdAndAttendanceDate(
            Long workerId,
            LocalDate attendanceDate);
    Page<AttendanceLog> findByWorkerIdAndAttendanceDateBetween(
            Long workerId,
            LocalDate from,
            LocalDate to,
            Pageable pageable);
}