package com.qa.portal.core.service.mapper;

import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.core.dto.*;
import com.qa.portal.core.persistence.entity.DepartmentRoleApplicationEntity;
import com.qa.portal.core.persistence.entity.MenuItemEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class ApplicationServiceMapper {

    private static Logger LOGGER = LoggerFactory.getLogger(ApplicationServiceMapper.class);

    private BaseMapper baseMapper;

    @Autowired
    public ApplicationServiceMapper(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    public List<DepartmentApplicationsDto> createDepartmentsApplicationsDto(Set<DepartmentRoleApplicationEntity> deptRoleApps,
                                                                            Collection<String> userRoles) {
        return getApplicationsByDepartment(deptRoleApps, userRoles).entrySet().stream()
                .map((entry) -> new DepartmentApplicationsDto(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingInt((DepartmentApplicationsDto d) -> d.getDepartment().getDisplayOrder()))
                .collect(Collectors.toList());

    }

    private Map<DepartmentDto, Set<ApplicationDto>> getApplicationsByDepartment(Set<DepartmentRoleApplicationEntity> deptRoleApps,
                                                                                Collection<String> userRoles) {
        return deptRoleApps.stream()
                .map(dra -> createDepartmentApplicationDto(dra, userRoles))
                .collect(Collectors.groupingBy(DepartmentApplicationDto::getDepartment,
                        Collectors.mapping(DepartmentApplicationDto::getApplicationDto, Collectors.toSet())));
    }

    public DepartmentApplicationDto createDepartmentApplicationDto(DepartmentRoleApplicationEntity draEntity,
                                                                   Collection<String> userRoles) {
        DepartmentDto departmentDto = baseMapper.mapObject(draEntity.getDepartmentRole().getDepartment(), DepartmentDto.class);
        ApplicationDto applicationDto = baseMapper.mapObject(draEntity.getApplication(), ApplicationDto.class);
        applicationDto.getMenuItems().stream().forEach(mi -> LOGGER.info(mi.getName() + ", " + mi.getUrl()));
        return new DepartmentApplicationDto(departmentDto, applicationDto);
    }
}
