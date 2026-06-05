package com.example.ems.mapper;

import com.example.ems.dto.SiteDto;
import com.example.ems.entity.Site;

public class SiteMapper {

    public static SiteDto mapToSiteDto(Site site) {

        SiteDto siteDto = new SiteDto();

        siteDto.setId(site.getId());
        siteDto.setSiteCode(site.getSiteCode());
        siteDto.setSiteName(site.getSiteName());
        siteDto.setLocation(site.getLocation());
        siteDto.setSupervisorName(site.getSupervisorName());

        return siteDto;
    }

    public static Site mapToSite(SiteDto siteDto) {

        Site site = new Site();

        site.setId(siteDto.getId());
        site.setSiteCode(siteDto.getSiteCode());
        site.setSiteName(siteDto.getSiteName());
        site.setLocation(siteDto.getLocation());
        site.setSupervisorName(siteDto.getSupervisorName());

        return site;
    }
}