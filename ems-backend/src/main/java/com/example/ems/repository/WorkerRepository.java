package com.example.ems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ems.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    Optional<Worker> findByEmployeeCode(String employeeCode);

    boolean existsByEmployeeCode(String employeeCode);
}