package com.qa.portal.application.service.project;

import com.qa.portal.application.dto.PortalProjectDto;
import com.qa.portal.application.persistence.entity.PortalProjectEntity;
import com.qa.portal.application.persistence.repository.PortalProjectRepository;
import com.qa.portal.application.service.project.mapper.PortalProjectMapper;
import org.springframework.stereotype.Component;

@Component
public class CreatePortalProjectOperation {

    private PortalProjectRepository portalProjectRepository;

    private PortalProjectMapper portalProjectMapper;

    public CreatePortalProjectOperation(PortalProjectRepository portalProjectRepository,
                                        PortalProjectMapper portalProjectMapper) {
        this.portalProjectRepository = portalProjectRepository;
        this.portalProjectMapper = portalProjectMapper;
    }

    public PortalProjectDto createPortalProject(PortalProjectDto portalProjectDto) {
        PortalProjectEntity portalProjectEntity = portalProjectMapper.mapToNewPortalProjectEntity(portalProjectDto);
        PortalProjectEntity savedEntity = portalProjectRepository.save(portalProjectEntity);
        return portalProjectMapper.mapToPortalProjectDto(savedEntity);
    }
}
