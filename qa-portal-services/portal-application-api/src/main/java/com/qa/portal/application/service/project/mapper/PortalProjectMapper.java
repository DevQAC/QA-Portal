package com.qa.portal.application.service.project.mapper;

import com.qa.portal.application.dto.PortalProjectDto;
import com.qa.portal.application.dto.ProjectPageDto;
import com.qa.portal.application.persistence.entity.PortalProjectEntity;
import com.qa.portal.application.persistence.entity.ProjectPageEntity;
import com.qa.portal.application.persistence.repository.PortalProjectRepository;
import com.qa.portal.application.service.page.mapper.ProjectPageMapper;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PortalProjectMapper {

    private PortalProjectRepository portalProjectRepository;

    private ProjectPageMapper projectPageMapper;

    private BaseMapper baseMapper;

    public PortalProjectMapper(PortalProjectRepository portalProjectRepository,
                               ProjectPageMapper projectPageMapper,
                               BaseMapper baseMapper) {
        this.portalProjectRepository = portalProjectRepository;
        this.projectPageMapper = projectPageMapper;
        this.baseMapper = baseMapper;
    }

    public PortalProjectDto mapToPortalProjectDto(PortalProjectEntity portalProjectEntity) {
        return baseMapper.mapObject(portalProjectEntity, PortalProjectDto.class);
    }

    public PortalProjectEntity mapToNewPortalProjectEntity(PortalProjectDto portalProjectDto) {
        PortalProjectEntity portalProjectEntity = baseMapper.mapObject(portalProjectDto, PortalProjectEntity.class);
        addNewProjectPages(portalProjectEntity, portalProjectDto);
        return portalProjectEntity;
    }

    public PortalProjectEntity mapToUpdatedPortalProjectEntity(PortalProjectDto portalProjectDto) {
        PortalProjectEntity portalProjectEntity = getExistingPortalProject(portalProjectDto);
        addNewProjectPages(portalProjectEntity, portalProjectDto);
        updateExistingProjectPages(portalProjectEntity, portalProjectDto);
        removeDeletedProjectPages(portalProjectEntity, portalProjectDto);
        return portalProjectEntity;
    }

    private void addNewProjectPages(PortalProjectEntity portalProjectEntity, PortalProjectDto portalProjectDto) {
        portalProjectDto.getProjectPages().stream()
                .filter(pp -> pp.getId() == null)
                .forEach(pp -> portalProjectEntity.addProjectPage(projectPageMapper.mapToNewProjectPageEntity(pp)));
    }

    private void updateExistingProjectPages(PortalProjectEntity portalProjectEntity, PortalProjectDto portalProjectDto) {
        portalProjectEntity.getProjectPages().stream()
                .filter(pp -> getNewProjectPages(portalProjectDto).contains(pp.getId()))
                .forEach(pp -> projectPageMapper.mapToUpdatedProjectPageEntity(getProjectPageDto(portalProjectDto, pp), pp));
    }

    private void removeDeletedProjectPages(PortalProjectEntity portalProjectEntity, PortalProjectDto portalProjectDto) {
        portalProjectEntity.getProjectPages().stream()
                .filter(pp -> !getNewProjectPages(portalProjectDto).contains(pp.getId()))
                .collect(Collectors.toList())
                .iterator()
                .forEachRemaining(pp -> portalProjectEntity.removeProjectPage(pp));
    }

    private ProjectPageDto getProjectPageDto(PortalProjectDto portalProjectDto,
                                             ProjectPageEntity projectPageEntity) {
        return portalProjectDto.getProjectPages().stream()
                .filter(pp -> projectPageEntity.getId().equals(pp.getId()))
                .findFirst()
                .orElseThrow(() -> new QaPortalBusinessException("No Project Page found for supplied id"));
    }

    private List<Integer> getNewProjectPages(PortalProjectDto portalProjectDto) {
        return portalProjectDto.getProjectPages().stream()
                .map(pp -> pp.getId())
                .collect(Collectors.toList());
    }

    private List<Integer> getExistingProjectPages(PortalProjectEntity portalProjectEntity) {
        return portalProjectEntity.getProjectPages().stream()
                .map(pp -> pp.getId())
                .collect(Collectors.toList());
    }

    private PortalProjectEntity getExistingPortalProject(PortalProjectDto portalProjectDto) {
        return portalProjectRepository.findById(portalProjectDto.getId())
                .orElseThrow(() -> new QaPortalBusinessException("No Portal Project found for the supplied id"));
    }
}
