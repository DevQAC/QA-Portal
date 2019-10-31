package com.qa.portal.application.service.project;

import com.qa.portal.application.dto.ProjectPageDto;
import com.qa.portal.application.persistence.entity.PortalProjectEntity;
import com.qa.portal.application.persistence.entity.ProjectPageEntity;
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
    public PortalProjectDto getPortalProject(Integer id) {
        PortalProjectEntity portalProjectEntity = getPortalProjectEntity(id);
        PortalProjectDto portalProjectDto = baseMapper.mapObject(portalProjectEntity, PortalProjectDto.class);
        portalProjectDto.getProjectPages().stream()
                .forEach(pp -> setProjectPageRoles(pp, portalProjectEntity));
        return portalProjectDto;
    }

    private void setProjectPageRoles(ProjectPageDto projectPageDto, PortalProjectEntity portalProjectEntity) {
        List<String> pageRoles = getProjectPageEntity(projectPageDto, portalProjectEntity).getRoleProjectPageEntities().stream()
                .map(rpp -> rpp.getRole().getName())
                .collect(Collectors.toList());
        projectPageDto.setRoles(pageRoles);
    }

    private ProjectPageEntity getProjectPageEntity(ProjectPageDto projectPageDto, PortalProjectEntity portalProjectEntity) {
        return portalProjectEntity.getProjectPages().stream()
                .filter(pp -> pp.getId().equals(projectPageDto.getId()))
                .findFirst()
                .orElseThrow(() -> new QaPortalBusinessException("Error getting pages for Portal application"));
    }

    private PortalProjectEntity getPortalProjectEntity(Integer id) {
        return portalProjectRepository.findById(id)
                .orElseThrow(() -> new QaPortalBusinessException("No Portal Project found for supplied id"));
    }
}
