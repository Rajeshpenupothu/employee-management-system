package com.example.ems.controller;

import com.example.ems.dto.AttendanceLogDto;
import com.example.ems.service.AttendanceLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/attendance")
public class AttendanceLogController {

    @Autowired
    private AttendanceLogService attendanceLogService;

    @PostMapping
    public ResponseEntity<AttendanceLogDto> createAttendanceLog(
            @RequestBody AttendanceLogDto attendanceLogDto) {

        AttendanceLogDto savedAttendanceLog =
                attendanceLogService.createAttendanceLog(attendanceLogDto);

        return new ResponseEntity<>(
                savedAttendanceLog,
                HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<AttendanceLogDto> getAttendanceLogById(
            @PathVariable("id") Long attendanceId) {

        AttendanceLogDto attendanceLogDto =
                attendanceLogService.getAttendanceLogById(attendanceId);

        return ResponseEntity.ok(attendanceLogDto);
    }

    @GetMapping
    public ResponseEntity<Page<AttendanceLogDto>> getAllAttendanceLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        Page<AttendanceLogDto> attendanceLogs =
                attendanceLogService.getAllAttendanceLogs(
                        pageable);

        return ResponseEntity.ok(attendanceLogs);
    }

    @PutMapping("{id}")
    public ResponseEntity<AttendanceLogDto> updateAttendanceLog(
            @PathVariable("id") Long attendanceId,
            @RequestBody AttendanceLogDto updatedAttendanceLog) {

        AttendanceLogDto attendanceLogDto =
                attendanceLogService.updateAttendanceLog(
                        attendanceId,
                        updatedAttendanceLog);

        return ResponseEntity.ok(attendanceLogDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAttendanceLog(
            @PathVariable("id") Long attendanceId) {

        attendanceLogService.deleteAttendanceLog(attendanceId);

        return ResponseEntity.ok(
                "Attendance Log deleted successfully!");
    }
    @PostMapping("/clock-in")
    public ResponseEntity<AttendanceLogDto> clockIn(
            @RequestParam Long workerId,
            @RequestParam Long siteId) {

        AttendanceLogDto attendanceLogDto =
                attendanceLogService.clockIn(workerId, siteId);

        return ResponseEntity.ok(attendanceLogDto);
    }

    @PostMapping("/clock-out")
    public ResponseEntity<AttendanceLogDto> clockOut(
            @RequestParam Long workerId) {

        AttendanceLogDto attendanceLogDto =
                attendanceLogService.clockOut(workerId);

        return ResponseEntity.ok(attendanceLogDto);
    }
    @GetMapping("/active")
    public ResponseEntity<java.util.Set<String>> getActiveWorkers() {

        return ResponseEntity.ok(
                attendanceLogService.getActiveWorkers());
    }
    @GetMapping("/log")
    public ResponseEntity<Page<AttendanceLogDto>> getAttendanceHistory(
            @RequestParam Long workerId,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(
                attendanceLogService.getAttendanceHistory(
                        workerId,
                        from,
                        to,
                        pageable));
    }
}