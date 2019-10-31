package com.qa.portal.application.service.page.mapper;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.application.dto.ProjectPageDto;
import com.qa.portal.application.persistence.entity.ProjectPageEntity;
import com.qa.portal.application.persistence.entity.RoleEntity;
import com.qa.portal.application.persistence.entity.RoleProjectPageEntity;
import com.qa.portal.application.persistence.repository.PortalProjectRepository;
import com.qa.portal.application.persistence.repository.ProjectPageRepository;
import com.qa.portal.application.persistence.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProjectPageMapper {

    private final Logger LOGGER = LoggerFactory.getLogger(ProjectPageMapper.class);

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
        if (projectPageEntity.getDisplayOnMenu() == null) {
            projectPageEntity.setDisplayOnMenu(Boolean.TRUE);
        }
        projectPageEntity.setRoleProjectPageEntities(new ArrayList<>());
        projectPageDto.getRoles().stream()
                .map(r -> createNewRoleProjectPageEntity(r, projectPageEntity))
                .forEach(rppe -> projectPageEntity.addRoleProjectPageEntity(rppe));
        return projectPageEntity;
    }

    public ProjectPageEntity mapToUpdatedProjectPageEntity(ProjectPageDto projectPageDto, ProjectPageEntity projectPageEntity) {
        projectPageEntity.setName(projectPageDto.getName());
        projectPageEntity.setDisplayOnMenu(projectPageDto.getDisplayOnMenu());
        projectPageEntity.setIcon(projectPageDto.getIcon());
        projectPageEntity.setTooltip(projectPageDto.getTooltip());
        projectPageEntity.setUrl(projectPageDto.getUrl());
        deleteRemovedRolesFromProjectPage(projectPageEntity, projectPageDto);
        addNewRoleToProjectPage(projectPageEntity, projectPageDto);
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
                .collect(Collectors.toList())
                .iterator()
                .forEachRemaining(rppe -> projectPageEntity.removeRoleProjectPageEntity(rppe));
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
