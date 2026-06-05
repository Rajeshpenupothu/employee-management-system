package com.example.ems.service.impl;

import com.example.ems.dto.AttendanceLogDto;
import com.example.ems.entity.AttendanceLog;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.mapper.AttendanceLogMapper;
import com.example.ems.repository.AttendanceLogRepository;
import com.example.ems.service.AttendanceLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.ems.service.RedisService;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ems.entity.Worker;
import com.example.ems.entity.OvertimeEntry;
import com.example.ems.entity.SettlementStatus;
import com.example.ems.repository.WorkerRepository;
import com.example.ems.repository.OvertimeEntryRepository;

import com.example.ems.entity.Site;
import com.example.ems.repository.SiteRepository;

import java.math.BigDecimal;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceLogServiceImpl implements AttendanceLogService {

    @Autowired
    private AttendanceLogRepository attendanceLogRepository;
    
    @Autowired
    private RedisService redisService;
    
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private OvertimeEntryRepository overtimeEntryRepository;

    @Override
    public AttendanceLogDto createAttendanceLog(
            AttendanceLogDto attendanceLogDto) {

        AttendanceLog attendanceLog =
                AttendanceLogMapper.mapToAttendanceLog(attendanceLogDto);

        AttendanceLog savedAttendanceLog =
                attendanceLogRepository.save(attendanceLog);


        return AttendanceLogMapper.mapToAttendanceLogDto(
                savedAttendanceLog);
    }

    @Override
    public AttendanceLogDto getAttendanceLogById(Long attendanceId) {

        AttendanceLog attendanceLog =
                attendanceLogRepository.findById(attendanceId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Attendance Log does not exist with id: "
                                                + attendanceId));

        return AttendanceLogMapper.mapToAttendanceLogDto(attendanceLog);
    }

    @Override
    public Page<AttendanceLogDto> getAllAttendanceLogs(
            Pageable pageable) {

        return attendanceLogRepository
                .findAll(pageable)
                .map(AttendanceLogMapper::mapToAttendanceLogDto);
    }

    @Override
    public AttendanceLogDto updateAttendanceLog(
            Long attendanceId,
            AttendanceLogDto updatedAttendanceLog) {

        AttendanceLog attendanceLog =
                attendanceLogRepository.findById(attendanceId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Attendance Log does not exist with id: "
                                                + attendanceId));

        attendanceLog.setWorkerId(
                updatedAttendanceLog.getWorkerId());

        attendanceLog.setSiteId(
                updatedAttendanceLog.getSiteId());

        attendanceLog.setAttendanceDate(
                updatedAttendanceLog.getAttendanceDate());

        attendanceLog.setClockInTime(
                updatedAttendanceLog.getClockInTime());

        attendanceLog.setClockOutTime(
                updatedAttendanceLog.getClockOutTime());

        AttendanceLog savedAttendanceLog =
                attendanceLogRepository.save(attendanceLog);

        return AttendanceLogMapper.mapToAttendanceLogDto(
                savedAttendanceLog);
    }

    @Override
    public void deleteAttendanceLog(Long attendanceId) {

        AttendanceLog attendanceLog =
                attendanceLogRepository.findById(attendanceId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Attendance Log does not exist with id: "
                                                + attendanceId));

        attendanceLogRepository.deleteById(attendanceId);
    }
    @Override
    @Transactional
    public AttendanceLogDto clockIn(Long workerId, Long siteId) {

        Worker worker =
                workerRepository.findById(workerId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Worker not found"));

        if (!Boolean.TRUE.equals(worker.getActive())) {

            throw new RuntimeException(
                    "Worker is inactive");
        }

        Site site =
                siteRepository.findById(siteId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Site not found"));

        attendanceLogRepository
                .findByWorkerIdAndAttendanceDate(
                        workerId,
                        java.time.LocalDate.now())
                .ifPresent(log -> {
                    throw new RuntimeException(
                            "Worker already clocked in today");
                });

        AttendanceLog attendanceLog =
                new AttendanceLog();

        attendanceLog.setWorkerId(workerId);
        attendanceLog.setSiteId(siteId);
        attendanceLog.setAttendanceDate(java.time.LocalDate.now());
        attendanceLog.setClockInTime(java.time.LocalDateTime.now());

        AttendanceLog savedAttendanceLog =
                attendanceLogRepository.save(attendanceLog);

        return AttendanceLogMapper.mapToAttendanceLogDto(savedAttendanceLog);
    }

    @Override
    @Transactional
    public AttendanceLogDto clockOut(Long workerId) {

        AttendanceLog attendanceLog =
                attendanceLogRepository
                        .findByWorkerIdAndAttendanceDate(
                                workerId,
                                java.time.LocalDate.now())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "No attendance found for worker today"));

        attendanceLog.setClockOutTime(
                java.time.LocalDateTime.now());
        
        java.time.Duration duration =
                java.time.Duration.between(
                        attendanceLog.getClockInTime(),
                        attendanceLog.getClockOutTime());

        if (duration.toHours() > 16) {

            attendanceLog.setAnomalyFlag(true);
        }
        
        double totalHours = duration.toMinutes() / 60.0;

        if (totalHours > 8) {

        	double overtimeHours = totalHours - 8;

        	java.time.YearMonth yearMonth =
        	        java.time.YearMonth.from(
        	                attendanceLog.getAttendanceDate());

        	java.time.LocalDate startDate =
        	        yearMonth.atDay(1);

        	java.time.LocalDate endDate =
        	        yearMonth.atEndOfMonth();

        	java.util.List<OvertimeEntry> existingEntries =
        	        overtimeEntryRepository
        	                .findByWorkerIdAndWorkDateBetween(
        	                        workerId,
        	                        startDate,
        	                        endDate);

        	double currentMonthlyHours =
        	        existingEntries.stream()
        	                .mapToDouble(
        	                        OvertimeEntry::getOvertimeHours)
        	                .sum();

        	double remainingHours =
        	        60 - currentMonthlyHours;

        	if (remainingHours <= 0) {

        	    overtimeHours = 0;

        	} else if (overtimeHours > remainingHours) {

        	    overtimeHours = remainingHours;
        	}

        	if (overtimeHours <= 0) {

        	    AttendanceLog updatedAttendanceLog =
        	            attendanceLogRepository.save(
        	                    attendanceLog);

        	    redisService.removeActiveWorker(
        	            workerId);

        	    return AttendanceLogMapper.mapToAttendanceLogDto(
        	            updatedAttendanceLog);
        	}

        	Worker worker =
        	        workerRepository.findById(workerId)
        	                .orElseThrow(() ->
        	                        new ResourceNotFoundException(
        	                                "Worker not found"));

            double hourlyRate =
                    worker.getDailyWage().doubleValue() / 8.0;

            double overtimeAmount = 0;

            if (overtimeHours <= 2) {

                overtimeAmount =
                        overtimeHours * hourlyRate * 1.5;

            } else {

                overtimeAmount =
                        (2 * hourlyRate * 1.5)
                                +
                                ((overtimeHours - 2)
                                        * hourlyRate * 2);
            }

            OvertimeEntry overtimeEntry =
                    new OvertimeEntry();

            overtimeEntry.setWorkerId(workerId);

            overtimeEntry.setWorkDate(
                    attendanceLog.getAttendanceDate());

            overtimeEntry.setOvertimeHours(
                    overtimeHours);

            overtimeEntry.setOvertimeAmount(
                    BigDecimal.valueOf(
                            overtimeAmount));

            overtimeEntry.setStatus(
                    SettlementStatus.PENDING);

            overtimeEntryRepository.save(
                    overtimeEntry);
        }

        AttendanceLog updatedAttendanceLog =
                attendanceLogRepository.save(attendanceLog);

        redisService.removeActiveWorker(workerId);

        return AttendanceLogMapper.mapToAttendanceLogDto(
                updatedAttendanceLog);
    }
    @Override
    public java.util.Set<String> getActiveWorkers() {

        return redisService.getActiveWorkers();
    }
    @Override
    public Page<AttendanceLogDto> getAttendanceHistory(
            Long workerId,
            LocalDate from,
            LocalDate to,
            Pageable pageable) {

        return attendanceLogRepository
                .findByWorkerIdAndAttendanceDateBetween(
                        workerId,
                        from,
                        to,
                        pageable)
                .map(AttendanceLogMapper::mapToAttendanceLogDto);
    }
}