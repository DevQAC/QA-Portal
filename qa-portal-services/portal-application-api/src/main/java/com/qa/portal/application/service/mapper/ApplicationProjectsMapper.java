package com.qa.portal.application.service.mapper;

import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.application.dto.*;
import com.qa.portal.application.persistence.entity.RoleProjectPageEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

@Component
public class ApplicationProjectsMapper {

    private static Logger LOGGER = LoggerFactory.getLogger(ApplicationProjectsMapper.class);

    private BaseMapper baseMapper;

    @Autowired
    public ApplicationProjectsMapper(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    public List<ApplicationProjectsDto> createApplicationProjectsDto(Set<RoleProjectPageEntity> roleProjectPageEntities) {
        return getProjectsByApplication(roleProjectPageEntities).entrySet().stream()
                .map((entry) -> new ApplicationProjectsDto(entry.getKey(), entry.getValue()))
                .sorted(comparingInt((ApplicationProjectsDto d) -> d.getPortalApplication().getDisplayOrder()))
                .collect(toList());
    }

    private Map<PortalApplicationDto, Set<PortalProjectDto>> getProjectsByApplication(Set<RoleProjectPageEntity> roleProjectPageEntities) {
        return roleProjectPageEntities.stream()
                .map(rpp -> createApplicationProjectDto(rpp, roleProjectPageEntities))
                .sorted(comparingInt(ap -> ap.getPortalProjectDto().getId()))
                .collect(groupingBy(ApplicationProjectDto::getPortalApplication,
                        mapping(ApplicationProjectDto::getPortalProjectDto, toSet())));
    }

    private ApplicationProjectDto createApplicationProjectDto(RoleProjectPageEntity roleProjectPageEntity,
                                                              Set<RoleProjectPageEntity> roleProjectPageEntities) {
        PortalApplicationDto portalApplicationDto = baseMapper.mapObject(roleProjectPageEntity.getRole().getPortalApplication(), PortalApplicationDto.class);
        PortalProjectDto portalProjectDto = baseMapper.mapObject(roleProjectPageEntity.getProjectPage().getPortalProject(), PortalProjectDto.class);
        portalProjectDto.setProjectPages(getProjectPagesForPortalProject(portalProjectDto, roleProjectPageEntities));
        return new ApplicationProjectDto(portalApplicationDto, portalProjectDto);
    }

    private List<ProjectPageDto> getProjectPagesForPortalProject(PortalProjectDto portalProjectDto,
                                                                 Set<RoleProjectPageEntity> roleProjectPageEntities) {
        return roleProjectPageEntities.stream()
                .filter(rppe -> rppe.getProjectPage().getPortalProject().getName().equals(portalProjectDto.getName()))
                .map(rppe -> baseMapper.mapObject(rppe.getProjectPage(), ProjectPageDto.class))
                .sorted(comparingInt(ProjectPageDto::getId))
                .collect(toList());
    }
}
