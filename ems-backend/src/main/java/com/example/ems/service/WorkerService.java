package com.example.ems.service;

import java.util.List;

import com.example.ems.dto.WorkerDto;

public interface WorkerService {

    WorkerDto createWorker(WorkerDto workerDto);

    WorkerDto getWorkerById(Long workerId);

    List<WorkerDto> getAllWorkers();

    WorkerDto updateWorker(Long workerId, WorkerDto workerDto);

    void deleteWorker(Long workerId);
}