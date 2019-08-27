package com.qa.portal.core.service;

import com.qa.portal.core.dto.DepartmentApplicationsDto;
import com.qa.portal.core.persistence.entity.DepartmentRoleApplicationEntity;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class ReferenceDataService {

    @Transactional
    public List<DepartmentApplicationsDto> getApplicationsByDepartment(Collection<String> userRoles) {
        Set<DepartmentRoleApplicationEntity> dras = getDeptRoleApps(userRoles);
        return applicationServiceMapper.createDepartmentsApplicationsDto(dras, userRoles);
    }
}
