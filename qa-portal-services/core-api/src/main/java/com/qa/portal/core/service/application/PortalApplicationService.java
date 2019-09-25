package com.qa.portal.core.service.application;

import com.qa.portal.core.dto.ApplicationProjectsDto;
import com.qa.portal.core.persistence.entity.RoleProjectPageEntity;
import com.qa.portal.core.persistence.repository.RoleProjectPageRepository;
import com.qa.portal.core.persistence.repository.RoleRepository;
import com.qa.portal.core.service.application.mapper.ApplicationServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PortalApplicationService {

    //TODO - Maybe Cache this info for performance
    private static final Logger LOGGER = LoggerFactory.getLogger(PortalApplicationService.class);

    private static final String ANY_ROLE = "any";

    private ApplicationServiceMapper applicationServiceMapper;

    private RoleRepository roleRepository;

    private RoleProjectPageRepository roleProjectPageRepository;

    public PortalApplicationService(ApplicationServiceMapper applicationServiceMapper,
                                    RoleRepository roleRepository,
                                    RoleProjectPageRepository roleProjectPageRepository) {
        this.applicationServiceMapper = applicationServiceMapper;
        this.roleRepository = roleRepository;
        this.roleProjectPageRepository = roleProjectPageRepository;
    }

    @Transactional
    public List<ApplicationProjectsDto> getPortalApplications(Collection<String> userRoles) {
        userRoles.add(ANY_ROLE);
        Set<RoleProjectPageEntity> roleProjectPages = getRoleProjectPages(userRoles);
        return applicationServiceMapper.createApplicationProjectsDto(roleProjectPages);
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
}
