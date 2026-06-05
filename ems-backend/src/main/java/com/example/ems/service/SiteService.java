package com.example.ems.service;

import java.util.List;

import com.example.ems.dto.SiteDto;

public interface SiteService {

    SiteDto createSite(SiteDto siteDto);

    SiteDto getSiteById(Long siteId);

    List<SiteDto> getAllSites();

    SiteDto updateSite(Long siteId, SiteDto siteDto);

    void deleteSite(Long siteId);
}