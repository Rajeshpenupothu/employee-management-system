package com.example.ems.controller;

import com.example.ems.dto.SiteDto;
import com.example.ems.service.SiteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/sites")
public class SiteController {

    @Autowired
    private SiteService siteService;

    @PostMapping
    public ResponseEntity<SiteDto> createSite(@RequestBody SiteDto siteDto) {

        SiteDto savedSite = siteService.createSite(siteDto);

        return new ResponseEntity<>(savedSite, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<SiteDto> getSiteById(@PathVariable("id") Long siteId) {

        SiteDto siteDto = siteService.getSiteById(siteId);

        return ResponseEntity.ok(siteDto);
    }

    @GetMapping
    public ResponseEntity<List<SiteDto>> getAllSites() {

        List<SiteDto> sites = siteService.getAllSites();

        return ResponseEntity.ok(sites);
    }

    @PutMapping("{id}")
    public ResponseEntity<SiteDto> updateSite(
            @PathVariable("id") Long siteId,
            @RequestBody SiteDto updatedSite) {

        SiteDto siteDto = siteService.updateSite(siteId, updatedSite);

        return ResponseEntity.ok(siteDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSite(
            @PathVariable("id") Long siteId) {

        siteService.deleteSite(siteId);

        return ResponseEntity.ok("Site deleted successfully!");
    }
}