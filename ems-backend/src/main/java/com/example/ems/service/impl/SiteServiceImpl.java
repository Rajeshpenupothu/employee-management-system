package com.example.ems.service.impl;

import com.example.ems.dto.SiteDto;
import com.example.ems.entity.Site;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.mapper.SiteMapper;
import com.example.ems.repository.SiteRepository;
import com.example.ems.service.SiteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteRepository siteRepository;

    @Override
    @Transactional
    public SiteDto createSite(SiteDto siteDto) {

        Site site = SiteMapper.mapToSite(siteDto);
        Site savedSite = siteRepository.save(site);

        return SiteMapper.mapToSiteDto(savedSite);
    }

    @Override
    public SiteDto getSiteById(Long siteId) {

        Site site = siteRepository.findById(siteId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Site does not exist with the given id: " + siteId)
                );

        return SiteMapper.mapToSiteDto(site);
    }

    @Override
    public List<SiteDto> getAllSites() {

        List<Site> sites = siteRepository.findAll();

        return sites.stream()
                .map(SiteMapper::mapToSiteDto)
                .collect(Collectors.toList());
    }

    @Override
    public SiteDto updateSite(Long siteId, SiteDto updatedSite) {

        Site site = siteRepository.findById(siteId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Site does not exist with the given id: " + siteId)
                );

        site.setSiteCode(updatedSite.getSiteCode());
        site.setSiteName(updatedSite.getSiteName());
        site.setLocation(updatedSite.getLocation());
        site.setSupervisorName(updatedSite.getSupervisorName());

        Site savedSite = siteRepository.save(site);

        return SiteMapper.mapToSiteDto(savedSite);
    }

    @Override
    public void deleteSite(Long siteId) {

        Site site = siteRepository.findById(siteId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Site does not exist with the given id: " + siteId)
                );

        siteRepository.deleteById(siteId);
    }
}