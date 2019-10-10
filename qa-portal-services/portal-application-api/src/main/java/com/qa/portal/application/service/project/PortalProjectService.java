package com.qa.portal.application.service.project;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.application.dto.PortalProjectDto;
import com.qa.portal.application.persistence.repository.PortalProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PortalProjectService {

    private PortalProjectRepository portalProjectRepository;

    private BaseMapper baseMapper;

    public PortalProjectService(PortalProjectRepository portalProjectRepository,
                                BaseMapper baseMapper) {
        this.portalProjectRepository = portalProjectRepository;
        this.baseMapper = baseMapper;
    }

    @Transactional
    public List<PortalProjectDto> getPortalProjects() {
        return portalProjectRepository.findAll().stream()
                .map(p -> baseMapper.mapObject(p, PortalProjectDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public PortalProjectDto getPortalProject(Long id) {
        return portalProjectRepository.findById(id)
                .map(p -> baseMapper.mapObject(p, PortalProjectDto.class))
                .orElseThrow(() -> new QaPortalBusinessException("Portal Project not found"));
    }
}
