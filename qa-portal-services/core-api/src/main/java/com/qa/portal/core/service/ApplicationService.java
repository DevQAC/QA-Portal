package com.qa.portal.core.service;

import com.qa.portal.core.dto.DepartmentApplicationsDto;
import com.qa.portal.core.persistence.entity.DepartmentRoleApplicationEntity;
import com.qa.portal.core.persistence.repository.DepartmentRoleRepository;
import com.qa.portal.core.service.mapper.ApplicationServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.qa.portal.core.CoreConstants.SUPER_USER_ROLE;

@Service
public class ApplicationService {

    //TODO - Maybe Cache this info for performance
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationService.class);

    private ApplicationServiceMapper applicationServiceMapper;

    private DepartmentRoleRepository departmentRoleRepository;

    @Autowired
    public ApplicationService(ApplicationServiceMapper applicationServiceMapper, DepartmentRoleRepository departmentRoleRepository) {
        this.applicationServiceMapper = applicationServiceMapper;
        this.departmentRoleRepository = departmentRoleRepository;
    }

    @Transactional
    public List<DepartmentApplicationsDto> getApplicationsByDepartment(Collection<String> userRoles) {
        Set<DepartmentRoleApplicationEntity> dras = getDeptRoleApps(userRoles);
        return applicationServiceMapper.createDepartmentsApplicationsDto(dras, userRoles);
    }

    private Set<DepartmentRoleApplicationEntity> getDeptRoleApps(Collection<String> userRoles) {
        return departmentRoleRepository.findAll().stream()
                .filter(dr -> userRoles.contains(dr.getDepartmentRoleName()) || userRoles.contains(SUPER_USER_ROLE))
                .flatMap(dr -> dr.getDeptRoleApplications().stream())
                .collect(Collectors.toSet());
    }

    private void filterMenuItemsByRole() {

    }
}
