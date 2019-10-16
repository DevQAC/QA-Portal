package com.qa.portal.application.service;

import com.qa.portal.application.dto.ApplicationProjectsDto;
import com.qa.portal.application.persistence.entity.PortalApplicationEntity;
import com.qa.portal.application.persistence.entity.RoleProjectPageEntity;
import com.qa.portal.application.persistence.repository.PortalApplicationRepository;
import com.qa.portal.application.persistence.repository.RoleRepository;
import com.qa.portal.application.service.mapper.ApplicationProjectsMapper;
import com.qa.portal.common.exception.QaPortalBusinessException;
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

    public GetPortalApplicationByIdOperation(PortalApplicationRepository portalApplicationRepository,
                                             RoleRepository roleRepository,
                                             ApplicationProjectsMapper applicationProjectsMapper) {
        this.portalApplicationRepository = portalApplicationRepository;
        this.roleRepository = roleRepository;
        this.applicationProjectsMapper = applicationProjectsMapper;
    }

    public ApplicationProjectsDto getPortalApplicationById(Integer id) {
        Set<RoleProjectPageEntity> roleProjectPages = getRoleProjectPages(getRolesForApplication(id));
        return applicationProjectsMapper.createApplicationProjectsDto(roleProjectPages).get(0);
    }

    public List<String> getRolesForApplication(Integer id) {
        return roleRepository.findByPortalApplication(getPortalApplication(id)).stream()
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
                .map(r -> r.getRoleProjectPages())
                .orElseGet(() -> Collections.emptyList());
    }

    private PortalApplicationEntity getPortalApplication(Integer id) {
        return portalApplicationRepository.findById(id)
                .orElseThrow(() -> new QaPortalBusinessException("No Portal application found for supplied id"));
    }
}
