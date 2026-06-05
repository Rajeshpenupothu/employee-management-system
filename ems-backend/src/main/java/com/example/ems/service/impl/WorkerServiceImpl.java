package com.example.ems.service.impl;

import com.example.ems.dto.WorkerDto;
import com.example.ems.entity.Worker;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.mapper.WorkerMapper;
import com.example.ems.repository.WorkerRepository;
import com.example.ems.service.WorkerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    @Transactional
    public WorkerDto createWorker(WorkerDto workerDto) {

        Worker worker = WorkerMapper.mapToWorker(workerDto);
        Worker savedWorker = workerRepository.save(worker);

        return WorkerMapper.mapToWorkerDto(savedWorker);
    }

    @Override
    public WorkerDto getWorkerById(Long workerId) {

        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Worker does not exist with the given id: " + workerId)
                );

        return WorkerMapper.mapToWorkerDto(worker);
    }

    @Override
    public List<WorkerDto> getAllWorkers() {

        List<Worker> workers = workerRepository.findAll();

        return workers.stream()
                .map(WorkerMapper::mapToWorkerDto)
                .collect(Collectors.toList());
    }

    @Override
    public WorkerDto updateWorker(Long workerId, WorkerDto updatedWorker) {

        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Worker does not exist with the given id: " + workerId)
                );

        worker.setEmployeeCode(updatedWorker.getEmployeeCode());
        worker.setFullName(updatedWorker.getFullName());
        worker.setPhoneNumber(updatedWorker.getPhoneNumber());
        worker.setDesignation(updatedWorker.getDesignation());
        worker.setDailyWage(updatedWorker.getDailyWage());
        worker.setActive(updatedWorker.getActive());
        worker.setSiteId(updatedWorker.getSiteId());

        Worker savedWorker = workerRepository.save(worker);

        return WorkerMapper.mapToWorkerDto(savedWorker);
    }

    @Override
    public void deleteWorker(Long workerId) {

        Worker worker = workerRepository.findById(workerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Worker does not exist with the given id: " + workerId)
                );

        workerRepository.deleteById(workerId);
    }
}