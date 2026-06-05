package com.example.ems.controller;

import com.example.ems.dto.OvertimeEntryDto;
import com.example.ems.service.OvertimeEntryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/overtime")
public class OvertimeEntryController {

    @Autowired
    private OvertimeEntryService overtimeEntryService;

    @PostMapping
    public ResponseEntity<OvertimeEntryDto> createOvertimeEntry(
            @RequestBody OvertimeEntryDto overtimeEntryDto) {

        OvertimeEntryDto savedOvertimeEntry =
                overtimeEntryService.createOvertimeEntry(overtimeEntryDto);

        return new ResponseEntity<>(
                savedOvertimeEntry,
                HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<OvertimeEntryDto> getOvertimeEntryById(
            @PathVariable("id") Long overtimeId) {

        return ResponseEntity.ok(
                overtimeEntryService.getOvertimeEntryById(overtimeId));
    }

    @GetMapping
    public ResponseEntity<List<OvertimeEntryDto>> getAllOvertimeEntries() {

        return ResponseEntity.ok(
                overtimeEntryService.getAllOvertimeEntries());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOvertimeEntry(
            @PathVariable("id") Long overtimeId) {

        overtimeEntryService.deleteOvertimeEntry(overtimeId);

        return ResponseEntity.ok(
                "Overtime Entry deleted successfully!");
    }
    @GetMapping("/summary/{workerId}")
    public ResponseEntity<Map<String, Object>> getOvertimeSummary(
            @PathVariable Long workerId,
            @RequestParam String month) {

        return ResponseEntity.ok(
                overtimeEntryService.getOvertimeSummary(
                        workerId,
                        month));
    }
    @PostMapping("/settle/{workerId}")
    public ResponseEntity<Map<String, Object>> settleOvertime(
            @PathVariable Long workerId,
            @RequestParam String month) {

        return ResponseEntity.ok(
                overtimeEntryService.settleOvertime(
                        workerId,
                        month));
    }
}