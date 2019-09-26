package com.qa.portal.application.service.page.mapper;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.application.dto.ProjectPageDto;
import com.qa.portal.application.persistence.entity.ProjectPageEntity;
import com.qa.portal.application.persistence.entity.RoleEntity;
import com.qa.portal.application.persistence.entity.RoleProjectPageEntity;
import com.qa.portal.application.persistence.repository.PortalProjectRepository;
import com.qa.portal.application.persistence.repository.ProjectPageRepository;
import com.qa.portal.application.persistence.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectPageMapper {

    private ProjectPageRepository projectPageRepository;

    private PortalProjectRepository portalProjectRepository;

    private RoleRepository roleRepository;

    private BaseMapper baseMapper;

    public ProjectPageMapper(PortalProjectRepository portalProjectRepository,
                             ProjectPageRepository projectPageRepository,
                             RoleRepository roleRepository,
                             BaseMapper baseMapper) {
        this.portalProjectRepository = portalProjectRepository;
        this.projectPageRepository = projectPageRepository;
        this.roleRepository = roleRepository;
        this.baseMapper = baseMapper;
    }

    public ProjectPageDto mapToProjectPageDto(ProjectPageEntity projectPageEntity) {
        ProjectPageDto projectPageDto = baseMapper.mapObject(projectPageEntity, ProjectPageDto.class);
        projectPageDto.setPortalProjectName(projectPageEntity.getPortalProject().getName());
        projectPageDto.setRoles(projectPageEntity.getRoleProjectPageEntities()
                .stream()
                .map(rppe -> rppe.getRole().getName())
                .collect(Collectors.toList()));
        return projectPageDto;
    }

    public ProjectPageEntity mapToNewProjectPageEntity(ProjectPageDto projectPageDto) {
        ProjectPageEntity projectPageEntity = baseMapper.mapObject(projectPageDto, ProjectPageEntity.class);
        portalProjectRepository.findByName(projectPageDto.getPortalProjectName())
                .ifPresent(p -> projectPageEntity.setPortalProject(p));
        projectPageEntity.setRoleProjectPageEntities(new ArrayList<>());
        projectPageDto.getRoles().stream()
                .map(r -> createNewRoleProjectPageEntity(r, projectPageEntity))
                .forEach(rppe -> projectPageEntity.addRoleProjectPageEntity(rppe));
        return projectPageEntity;
    }

    public ProjectPageEntity mapToUpdatedProjectPageEntity(ProjectPageDto projectPageDto) {
        ProjectPageEntity projectPageEntity = projectPageRepository.findByName(projectPageDto.getName())
                                                        .orElseThrow(() -> new QaPortalBusinessException("No Project page found for supplied name"));
        portalProjectRepository.findByName(projectPageDto.getPortalProjectName())
                .ifPresent(p -> projectPageEntity.setPortalProject(p));
        projectPageEntity.setDisplayOnMenu(projectPageDto.getDisplayOnMenu());
        projectPageEntity.setIcon(projectPageDto.getIcon());
        projectPageEntity.setTooltip(projectPageDto.getTooltip());
        projectPageEntity.setUrl(projectPageDto.getUrl());
        addNewRoleToProjectPage(projectPageEntity, projectPageDto);
        deleteRemovedRolesFromProjectPage(projectPageEntity, projectPageDto);
        return projectPageEntity;
    }

    private void addNewRoleToProjectPage(ProjectPageEntity projectPageEntity,
                                         ProjectPageDto projectPageDto) {
        List<String> existingRoles = getPreviousProjectPageRoles(projectPageEntity);
        projectPageDto.getRoles().stream()
                .filter(r -> !existingRoles.contains(r))
                .map(r -> createNewRoleProjectPageEntity(r, projectPageEntity))
                .forEach(rppe -> projectPageEntity.addRoleProjectPageEntity(rppe));
    }

    private void deleteRemovedRolesFromProjectPage(ProjectPageEntity projectPageEntity,
                                                   ProjectPageDto projectPageDto) {
        List<String> newRoles = projectPageDto.getRoles();
        projectPageEntity.getRoleProjectPageEntities().stream()
                .filter(rppe -> !newRoles.contains(rppe.getRole().getName()))
                .forEach(rppe -> projectPageEntity.removeRoleProjectPageEntity(rppe));
    }

    private RoleProjectPageEntity createNewRoleProjectPageEntity(String role, ProjectPageEntity projectPageEntity) {
        return roleRepository.findByName(role)
                .map(r -> createNewRoleProjectPageEntity(r, projectPageEntity))
                .orElseThrow(() -> new QaPortalBusinessException("Role not found for supplied name"));
    }

    private RoleProjectPageEntity createNewRoleProjectPageEntity(RoleEntity roleEntity, ProjectPageEntity projectPageEntity) {
        RoleProjectPageEntity roleProjectPageEntity = new RoleProjectPageEntity();
        roleProjectPageEntity.setProjectPage(projectPageEntity);
        roleProjectPageEntity.setRole(roleEntity);
        return roleProjectPageEntity;
    }

    private List<String> getPreviousProjectPageRoles(ProjectPageEntity projectPageEntity) {
        return projectPageEntity.getRoleProjectPageEntities().stream()
                .map(rppe -> rppe.getRole().getName())
                .collect(Collectors.toList());
    }
}
