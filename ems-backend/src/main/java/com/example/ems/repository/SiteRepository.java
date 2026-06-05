package com.example.ems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ems.entity.Site;

public interface SiteRepository extends JpaRepository<Site, Long> {

    Optional<Site> findBySiteCode(String siteCode);

    boolean existsBySiteCode(String siteCode);
}