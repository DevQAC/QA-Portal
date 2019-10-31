package com.qa.portal.application.service;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.application.dto.PortalApplicationDto;
import com.qa.portal.application.persistence.entity.PortalApplicationEntity;
import com.qa.portal.application.persistence.repository.PortalApplicationRepository;
import org.springframework.stereotype.Component;

@Component
public class CreatePortalApplicationOperation {

    private PortalApplicationRepository portalApplicationRepository;

    private BaseMapper baseMapper;

    public CreatePortalApplicationOperation(PortalApplicationRepository portalApplicationRepository,
                                            BaseMapper baseMapper) {
        this.portalApplicationRepository = portalApplicationRepository;
        this.baseMapper = baseMapper;
    }

    public PortalApplicationDto createPortalApplication(PortalApplicationDto portalApplicationDto) {
        if (portalApplicationExists(portalApplicationDto)) {
            throw new QaPortalBusinessException("Portal application with this name already exists");
        }

        PortalApplicationEntity portalApplicationEntity = baseMapper.mapObject(portalApplicationDto, PortalApplicationEntity.class);
        PortalApplicationEntity savedApplication = portalApplicationRepository.save(portalApplicationEntity);
        return baseMapper.mapObject(savedApplication, PortalApplicationDto.class);
    }

    private boolean portalApplicationExists(PortalApplicationDto portalApplicationDto) {
        return portalApplicationRepository.findByName(portalApplicationDto.getName())
                .map(p -> true)
                .orElseGet(() -> false);
    }
}
