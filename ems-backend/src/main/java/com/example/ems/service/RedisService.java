package com.example.ems.service;

public interface RedisService {

    void addActiveWorker(
            Long workerId,
            String workerData);

    void removeActiveWorker(
            Long workerId);
    java.util.Set<String> getActiveWorkers();
}