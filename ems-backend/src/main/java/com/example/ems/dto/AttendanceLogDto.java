package com.example.ems.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceLogDto {

    private Long id;

    private Long workerId;

    private Long siteId;

    private LocalDate attendanceDate;

    private LocalDateTime clockInTime;

    private LocalDateTime clockOutTime;
    
    private Boolean anomalyFlag;

    public AttendanceLogDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public LocalDateTime getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(LocalDateTime clockInTime) {
        this.clockInTime = clockInTime;
    }

    public LocalDateTime getClockOutTime() {
        return clockOutTime;
    }

    public void setClockOutTime(LocalDateTime clockOutTime) {
        this.clockOutTime = clockOutTime;
    }
    public Boolean getAnomalyFlag() {
        return anomalyFlag;
    }

    public void setAnomalyFlag(Boolean anomalyFlag) {
        this.anomalyFlag = anomalyFlag;
    }
}