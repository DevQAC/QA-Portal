package com.qa.portal.application.service;

import com.qa.portal.application.dto.PortalApplicationDto;
import com.qa.portal.application.persistence.repository.PortalApplicationRepository;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class GetPortalApplicationByIdOperation {

    private PortalApplicationRepository portalApplicationRepository;

    private BaseMapper baseMapper;

    public GetPortalApplicationByIdOperation(PortalApplicationRepository portalApplicationRepository,
                                             BaseMapper baseMapper) {
        this.portalApplicationRepository = portalApplicationRepository;
        this.baseMapper = baseMapper;
    }

    public PortalApplicationDto getPortalApplicationById(Integer id) {
        return portalApplicationRepository.findById(id)
                .map(pa -> baseMapper.mapObject(pa, PortalApplicationDto.class))
                .orElseThrow(() -> new QaPortalBusinessException("No Portal application found for the supplied id"));
    }
}
