package com.qa.portal.core.service.mapper;

import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.core.dto.ApplicationDto;
import com.qa.portal.core.dto.DepartmentApplicationDto;
import com.qa.portal.core.dto.DepartmentApplicationsDto;
import com.qa.portal.core.dto.DepartmentDto;
import com.qa.portal.core.persistence.entity.DepartmentRoleApplicationEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class ApplicationServiceMapper {

    private static Logger LOGGER = LoggerFactory.getLogger(ApplicationServiceMapper.class);

    @Autowired
    private BaseMapper baseMapper;

    public List<DepartmentApplicationsDto> createDepartmentsApplicationsDto(Set<DepartmentRoleApplicationEntity> deptRoleApps) {
        return getApplicationsByDepartment(deptRoleApps).entrySet().stream()
                .map((entry) -> new DepartmentApplicationsDto(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingInt((DepartmentApplicationsDto d) -> d.getDepartment().getDisplayOrder()))
                .collect(Collectors.toList());

    }

    private Map<DepartmentDto, Set<ApplicationDto>> getApplicationsByDepartment(Set<DepartmentRoleApplicationEntity> deptRoleApps) {
        return deptRoleApps.stream()
                .map(dra -> createDepartmentApplicationDto(dra))
                .collect(Collectors.groupingBy(DepartmentApplicationDto::getDepartment,
                        Collectors.mapping(DepartmentApplicationDto::getApplicationDto, Collectors.toSet())));
    }

    public DepartmentApplicationDto createDepartmentApplicationDto(DepartmentRoleApplicationEntity draEntity) {
        DepartmentDto departmentDto = baseMapper.mapObject(draEntity.getDepartmentRole().getDepartment(), DepartmentDto.class);
        ApplicationDto applicationDto = baseMapper.mapObject(draEntity.getApplication(), ApplicationDto.class);
        return new DepartmentApplicationDto(departmentDto, applicationDto);
    }
}
