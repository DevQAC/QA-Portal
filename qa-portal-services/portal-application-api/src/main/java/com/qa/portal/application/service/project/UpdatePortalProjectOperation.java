package com.qa.portal.application.service.project;

import com.qa.portal.application.dto.PortalProjectDto;
import com.qa.portal.application.persistence.entity.PortalProjectEntity;
import com.qa.portal.application.persistence.repository.PortalProjectRepository;
import com.qa.portal.application.service.project.mapper.PortalProjectMapper;
import org.springframework.stereotype.Component;

@Component
public class UpdatePortalProjectOperation {

    private PortalProjectRepository portalProjectRepository;

    private PortalProjectMapper portalProjectMapper;

    public UpdatePortalProjectOperation(PortalProjectRepository portalProjectRepository,
                                        PortalProjectMapper portalProjectMapper) {
        this.portalProjectRepository = portalProjectRepository;
        this.portalProjectMapper = portalProjectMapper;
    }

    public PortalProjectDto updatePortalProject(PortalProjectDto portalProjectDto) {
        PortalProjectEntity portalProjectEntity = portalProjectMapper.mapToUpdatedPortalProjectEntity(portalProjectDto);
        return portalProjectMapper.mapToPortalProjectDto(portalProjectEntity);
    }
}
