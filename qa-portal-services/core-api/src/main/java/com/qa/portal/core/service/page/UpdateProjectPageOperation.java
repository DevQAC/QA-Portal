package com.qa.portal.core.service.page;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.core.dto.ProjectPageDto;
import com.qa.portal.core.persistence.entity.ProjectPageEntity;
import com.qa.portal.core.persistence.repository.PortalProjectRepository;
import com.qa.portal.core.persistence.repository.ProjectPageRepository;
import com.qa.portal.core.service.page.mapper.ProjectPageMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UpdateProjectPageOperation {

    private ProjectPageRepository projectPageRepository;

    private PortalProjectRepository portalProjectRepository;

    private ProjectPageMapper projectPageMapper;

    public UpdateProjectPageOperation(ProjectPageRepository projectPageRepository,
                                      PortalProjectRepository portalProjectRepository,
                                      ProjectPageMapper projectPageMapper) {
        this.projectPageRepository = projectPageRepository;
        this.portalProjectRepository = portalProjectRepository;
        this.projectPageMapper = projectPageMapper;
    }

    public ProjectPageDto updateProjectPage(ProjectPageDto projectPageDto) {
        return getProjectPage(projectPageDto)
                .map(pp -> persistUpdatedProjectPage(projectPageDto))
                .orElseThrow(() -> new QaPortalBusinessException("Portal Page not found for supplied name"));
    }

    private ProjectPageDto persistUpdatedProjectPage(ProjectPageDto projectPageDto) {
        ProjectPageEntity projectPageEntity = projectPageMapper.mapToUpdatedProjectPageEntity(projectPageDto);
        ProjectPageEntity savedEntity = projectPageRepository.save(projectPageEntity);
        return projectPageMapper.mapToProjectPageDto(savedEntity);
    }

    private Optional<ProjectPageEntity> getProjectPage(ProjectPageDto projectPageDto) {
        return projectPageRepository.findByName(projectPageDto.getName());
    }
}
