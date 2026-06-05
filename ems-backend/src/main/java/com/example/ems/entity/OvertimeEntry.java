package com.example.ems.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.example.ems.entity.SettlementStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "overtime_entries")
public class OvertimeEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long workerId;

    private LocalDate workDate;

    private Double overtimeHours;

    private BigDecimal overtimeAmount;
    @Enumerated(EnumType.STRING)
    private SettlementStatus status = SettlementStatus.PENDING;

    public OvertimeEntry() {
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

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate workDate) {
        this.workDate = workDate;
    }

    public Double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(Double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public BigDecimal getOvertimeAmount() {
        return overtimeAmount;
    }

    public void setOvertimeAmount(BigDecimal overtimeAmount) {
        this.overtimeAmount = overtimeAmount;
    }
    public SettlementStatus getStatus() {
        return status;
    }

    public void setStatus(SettlementStatus status) {
        this.status = status;
    }
}