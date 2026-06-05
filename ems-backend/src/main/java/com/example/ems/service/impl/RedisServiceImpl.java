package com.example.ems.service.impl;

import com.example.ems.service.RedisService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void addActiveWorker(
            Long workerId,
            String workerData) {

        try {

            redisTemplate.opsForSet()
                    .add(
                            "active_workers",
                            workerId + ":" + workerData);

            redisTemplate.opsForValue()
                    .set(
                            "active_worker:" + workerId,
                            workerData,
                            java.time.Duration.ofHours(16));

        } catch (Exception e) {

            System.out.println(
                    "Redis unavailable, skipping cache add");
        }
    }

    @Override
    public void removeActiveWorker(
            Long workerId) {

        try {

            java.util.Set<String> activeWorkers =
                    redisTemplate.opsForSet()
                            .members("active_workers");

            if (activeWorkers != null) {

                activeWorkers.stream()
                        .filter(worker ->
                                worker.startsWith(
                                        workerId + ":"))
                        .forEach(worker ->
                                redisTemplate.opsForSet()
                                        .remove(
                                                "active_workers",
                                                worker));
            }

            redisTemplate.delete(
                    "active_worker:" + workerId);

        } catch (Exception e) {

            System.out.println(
                    "Redis unavailable, skipping cache removal");
        }
    }

    @Override
    public java.util.Set<String> getActiveWorkers() {

        try {

            java.util.Set<String> activeWorkers =
                    redisTemplate.opsForSet()
                            .members("active_workers");

            return activeWorkers != null
                    ? activeWorkers
                    : new java.util.HashSet<>();

        } catch (Exception e) {

            System.out.println(
                    "Redis unavailable, returning empty set");

            return new java.util.HashSet<>();
        }
    }
}