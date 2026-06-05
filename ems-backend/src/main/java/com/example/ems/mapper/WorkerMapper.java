package com.example.ems.mapper;

import com.example.ems.dto.WorkerDto;
import com.example.ems.entity.Worker;

public class WorkerMapper {

    public static WorkerDto mapToWorkerDto(Worker worker) {

        WorkerDto workerDto = new WorkerDto();

        workerDto.setId(worker.getId());
        workerDto.setEmployeeCode(worker.getEmployeeCode());
        workerDto.setFullName(worker.getFullName());
        workerDto.setPhoneNumber(worker.getPhoneNumber());
        workerDto.setDesignation(worker.getDesignation());
        workerDto.setDailyWage(worker.getDailyWage());
        workerDto.setActive(worker.getActive());
        workerDto.setSiteId(worker.getSiteId());

        return workerDto;
    }

    public static Worker mapToWorker(WorkerDto workerDto) {

        Worker worker = new Worker();

        worker.setId(workerDto.getId());
        worker.setEmployeeCode(workerDto.getEmployeeCode());
        worker.setFullName(workerDto.getFullName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setDesignation(workerDto.getDesignation());
        worker.setDailyWage(workerDto.getDailyWage());
        worker.setActive(workerDto.getActive());
        worker.setSiteId(workerDto.getSiteId());

        return worker;
    }
}