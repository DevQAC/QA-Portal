package com.qa.portal.core.service.project;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.core.dto.PortalProjectDto;
import com.qa.portal.core.persistence.entity.PortalProjectEntity;
import com.qa.portal.core.persistence.repository.PortalProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PortalProjectManagementService {

    private PortalProjectRepository portalProjectRepository;

    private BaseMapper baseMapper;

    public PortalProjectManagementService(PortalProjectRepository portalProjectRepository,
                                        BaseMapper baseMapper) {
        this.portalProjectRepository = portalProjectRepository;
        this.baseMapper = baseMapper;
    }

    @Transactional
    public PortalProjectDto createPortalProject(PortalProjectDto portalProjectDto) {
        if (portalProjectExists(portalProjectDto)) {
            throw new QaPortalBusinessException("Portal Project already exists for supplied name");
        }
        return persistPortalProject(portalProjectDto);
    }

    private PortalProjectDto persistPortalProject(PortalProjectDto portalProjectDto) {
        PortalProjectEntity portalProjectEntity = baseMapper.mapObject(portalProjectDto, PortalProjectEntity.class);
        PortalProjectEntity savedEntity = portalProjectRepository.save(portalProjectEntity);
        return baseMapper.mapObject(savedEntity, PortalProjectDto.class);
    }

    private boolean portalProjectExists(PortalProjectDto portalProjectDto) {
        return portalProjectRepository.findByName(portalProjectDto.getName())
                .map(pp -> true)
                .orElseGet(() -> false);
    }
}
