package com.qa.portal.application.service.page;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.application.dto.ProjectPageDto;
import com.qa.portal.application.persistence.entity.ProjectPageEntity;
import com.qa.portal.application.persistence.repository.PortalProjectRepository;
import com.qa.portal.application.persistence.repository.ProjectPageRepository;
import com.qa.portal.application.service.page.mapper.ProjectPageMapper;
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
        ProjectPageEntity currentProjectPageEntity = getProjectPage(projectPageDto)
                .orElseThrow(() -> new QaPortalBusinessException("No Project page found for supplied id"));
        ProjectPageEntity projectPageEntity = projectPageMapper.mapToUpdatedProjectPageEntity(projectPageDto, currentProjectPageEntity);
        ProjectPageEntity savedEntity = projectPageRepository.save(projectPageEntity);
        return projectPageMapper.mapToProjectPageDto(savedEntity);
    }

    private Optional<ProjectPageEntity> getProjectPage(ProjectPageDto projectPageDto) {
        return projectPageRepository.findById(projectPageDto.getId());
    }
}
