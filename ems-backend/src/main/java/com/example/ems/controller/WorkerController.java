package com.example.ems.controller;

import com.example.ems.dto.WorkerDto;
import com.example.ems.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @PostMapping
    public ResponseEntity<WorkerDto> createWorker(@RequestBody WorkerDto workerDto) {
        WorkerDto savedWorker = workerService.createWorker(workerDto);
        return new ResponseEntity<>(savedWorker, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkerDto> getWorkerById(@PathVariable("id") Long workerId) {
        WorkerDto workerDto = workerService.getWorkerById(workerId);
        return ResponseEntity.ok(workerDto);
    }

    @GetMapping
    public ResponseEntity<List<WorkerDto>> getAllWorkers() {
        List<WorkerDto> workers = workerService.getAllWorkers();
        return ResponseEntity.ok(workers);
    }

    @PutMapping("{id}")
    public ResponseEntity<WorkerDto> updateWorker(
            @PathVariable("id") Long workerId,
            @RequestBody WorkerDto updatedWorker) {

        WorkerDto workerDto = workerService.updateWorker(workerId, updatedWorker);

        return ResponseEntity.ok(workerDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteWorker(@PathVariable("id") Long workerId) {

        workerService.deleteWorker(workerId);

        return ResponseEntity.ok("Worker deleted successfully!");
    }
}