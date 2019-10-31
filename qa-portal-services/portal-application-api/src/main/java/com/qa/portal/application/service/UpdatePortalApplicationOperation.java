package com.qa.portal.application.service;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.application.dto.PortalApplicationDto;
import com.qa.portal.application.persistence.entity.PortalApplicationEntity;
import com.qa.portal.application.persistence.repository.PortalApplicationRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdatePortalApplicationOperation {

    private PortalApplicationRepository portalApplicationRepository;

    private BaseMapper baseMapper;

    public UpdatePortalApplicationOperation(PortalApplicationRepository portalApplicationRepository,
                                            BaseMapper baseMapper) {
        this.portalApplicationRepository = portalApplicationRepository;
        this.baseMapper = baseMapper;
    }

    public PortalApplicationDto updatePortalApplication(PortalApplicationDto portalApplicationDto) {
        return getPortalApplication(portalApplicationDto)
                .map(p -> updatePortalApplication(p, portalApplicationDto))
                .orElseThrow(() -> new QaPortalBusinessException("Portal Application not found"));
    }

    private PortalApplicationDto updatePortalApplication(PortalApplicationEntity portalApplicationEntity,
                                                        PortalApplicationDto portalApplicationDto) {
        portalApplicationEntity.setBaseUrl(portalApplicationDto.getBaseUrl());
        portalApplicationEntity.setName(portalApplicationDto.getName());
        portalApplicationEntity.setDescription(portalApplicationDto.getDescription());
        portalApplicationEntity.setDisplayOrder(portalApplicationDto.getDisplayOrder());
        return baseMapper.mapObject(portalApplicationEntity, PortalApplicationDto.class);
    }

    private Optional<PortalApplicationEntity> getPortalApplication(PortalApplicationDto portalApplicationDto) {
        return portalApplicationRepository.findById(portalApplicationDto.getId());
    }
}
