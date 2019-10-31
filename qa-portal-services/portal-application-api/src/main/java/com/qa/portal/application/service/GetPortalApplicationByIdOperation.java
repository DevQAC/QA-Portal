package com.qa.portal.application.service;

import com.qa.portal.application.dto.ApplicationProjectsDto;
import com.qa.portal.application.dto.PortalApplicationDto;
import com.qa.portal.application.persistence.entity.PortalApplicationEntity;
import com.qa.portal.application.persistence.entity.RoleEntity;
import com.qa.portal.application.persistence.entity.RoleProjectPageEntity;
import com.qa.portal.application.persistence.repository.PortalApplicationRepository;
import com.qa.portal.application.persistence.repository.RoleRepository;
import com.qa.portal.application.service.mapper.ApplicationProjectsMapper;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GetPortalApplicationByIdOperation {

    private PortalApplicationRepository portalApplicationRepository;

    private RoleRepository roleRepository;

    private ApplicationProjectsMapper applicationProjectsMapper;

    private BaseMapper baseMapper;

    public GetPortalApplicationByIdOperation(PortalApplicationRepository portalApplicationRepository,
                                             RoleRepository roleRepository,
                                             ApplicationProjectsMapper applicationProjectsMapper,
                                             BaseMapper baseMapper) {
        this.portalApplicationRepository = portalApplicationRepository;
        this.roleRepository = roleRepository;
        this.applicationProjectsMapper = applicationProjectsMapper;
        this.baseMapper= baseMapper;
    }

    public ApplicationProjectsDto getPortalApplicationById(Integer id) {
        Set<RoleProjectPageEntity> roleProjectPages = getRoleProjectPages(getRolesForApplication(id));
        if (roleProjectPages.isEmpty()) {
            return getApplicationWithoutProject(id);
        }
        return applicationProjectsMapper.createApplicationProjectsDto(roleProjectPages).get(0);
    }

    public List<String> getRolesForApplication(Integer id) {
        return roleRepository.findByPortalApplication(getPortalApplicationEntity(id)).stream()
                .map(r -> r.getName())
                .collect(Collectors.toList());
    }

    private Set<RoleProjectPageEntity> getRoleProjectPages(Collection<String> roles) {
        return roles.stream()
                .flatMap(r -> getRoleProjectPages(r).stream())
                .collect(Collectors.toSet());
    }

    private List<RoleProjectPageEntity> getRoleProjectPages(String roleName) {
        return roleRepository.findByName(roleName)
                .map(r -> getRoleProjectPagesInAProject(r))
                .orElseGet(() -> Collections.emptyList());
    }

    private List<RoleProjectPageEntity> getRoleProjectPagesInAProject(RoleEntity roleEntity) {
        return roleEntity.getRoleProjectPages().stream()
                .filter(rpp -> rpp.getProjectPage().getPortalProject() != null)
                .collect(Collectors.toList());
    }

    private ApplicationProjectsDto getApplicationWithoutProject(Integer id) {
        return new ApplicationProjectsDto(getPortalApplicationDto(id), Collections.emptySet());
    }

    private PortalApplicationDto getPortalApplicationDto(Integer id) {
        return baseMapper.mapObject(getPortalApplicationEntity(id), PortalApplicationDto.class);
    }

    private PortalApplicationEntity getPortalApplicationEntity(Integer id) {
        return portalApplicationRepository.findById(id)
                .orElseThrow(() -> new QaPortalBusinessException("No Portal application found for supplied id"));
    }
}
