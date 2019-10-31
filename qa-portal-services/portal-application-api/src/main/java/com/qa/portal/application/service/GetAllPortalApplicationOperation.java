package com.qa.portal.application.service;

import com.qa.portal.application.dto.PortalApplicationDto;
import com.qa.portal.application.persistence.repository.PortalApplicationRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetAllPortalApplicationOperation {

    private PortalApplicationRepository portalApplicationRepository;

    private BaseMapper baseMapper;

    public GetAllPortalApplicationOperation(PortalApplicationRepository portalApplicationRepository,
                                            BaseMapper baseMapper) {
        this.portalApplicationRepository = portalApplicationRepository;
        this.baseMapper = baseMapper;
    }

    public List<PortalApplicationDto> getAllPortalApplications() {
        return portalApplicationRepository.findAll().stream()
                .map(pa -> baseMapper.mapObject(pa, PortalApplicationDto.class))
                .collect(Collectors.toList());
    }
}
