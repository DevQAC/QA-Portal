package com.qa.portal.application.service.page;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.application.dto.ProjectPageDto;
import com.qa.portal.application.persistence.repository.ProjectPageRepository;
import com.qa.portal.application.service.page.mapper.ProjectPageMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectPageService {

    private ProjectPageRepository projectPageRepository;

    private ProjectPageMapper projectPageMapper;

    public ProjectPageService(ProjectPageRepository projectPageRepository,
                              ProjectPageMapper projectPageMapper) {
        this.projectPageRepository = projectPageRepository;
        this.projectPageMapper = projectPageMapper;
    }

    @Transactional
    public List<ProjectPageDto> getAllProjectPages() {
        return projectPageRepository.findAll().stream()
                .map(pp -> projectPageMapper.mapToProjectPageDto(pp))
                .collect(Collectors.toList());
    }

    @Transactional
    public ProjectPageDto getProjectPageForId(Integer id) {
        return projectPageRepository.findById(id)
                .map(pp -> projectPageMapper.mapToProjectPageDto(pp))
                .orElseThrow(() -> new QaPortalBusinessException("No Project Page found for supplied id"));
    }
}
