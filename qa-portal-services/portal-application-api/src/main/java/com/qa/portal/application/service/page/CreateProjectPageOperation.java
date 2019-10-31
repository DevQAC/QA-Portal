package com.qa.portal.application.service.page;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.application.dto.ProjectPageDto;
import com.qa.portal.application.persistence.entity.ProjectPageEntity;
import com.qa.portal.application.persistence.repository.PortalProjectRepository;
import com.qa.portal.application.persistence.repository.ProjectPageRepository;
import com.qa.portal.application.service.page.mapper.ProjectPageMapper;
import org.springframework.stereotype.Component;

@Component
public class CreateProjectPageOperation {

    private ProjectPageRepository projectPageRepository;

    private PortalProjectRepository portalProjectRepository;

    private ProjectPageMapper projectPageMapper;

    public CreateProjectPageOperation(ProjectPageRepository projectPageRepository,
                                      PortalProjectRepository portalProjectRepository,
                                      ProjectPageMapper projectPageMapper) {
        this.projectPageRepository = projectPageRepository;
        this.portalProjectRepository = portalProjectRepository;
        this.projectPageMapper = projectPageMapper;
    }

    public ProjectPageDto createProjectPage(ProjectPageDto projectPageDto) {
        if (projectPageExists(projectPageDto)) {
            throw new QaPortalBusinessException("Portal Page already exists with the same name");
        }
        return persistProjectPage(projectPageDto);
    }

    private ProjectPageDto persistProjectPage(ProjectPageDto projectPageDto) {
        ProjectPageEntity projectPageEntity = projectPageMapper.mapToNewProjectPageEntity(projectPageDto);
        ProjectPageEntity savedEntity = projectPageRepository.save(projectPageEntity);
        return projectPageMapper.mapToProjectPageDto(savedEntity);
    }

    private boolean projectPageExists(ProjectPageDto projectPageDto) {
        return projectPageRepository.findByName(projectPageDto.getName())
                .map(pp -> true)
                .orElseGet(() -> false);
    }
}
