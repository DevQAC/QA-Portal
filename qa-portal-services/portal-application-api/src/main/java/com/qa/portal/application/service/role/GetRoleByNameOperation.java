package com.qa.portal.application.service.role;

import com.qa.portal.application.dto.RoleDto;
import com.qa.portal.application.persistence.repository.RoleRepository;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class GetRoleByNameOperation {

    private RoleRepository roleRepository;

    private BaseMapper baseMapper;

    public GetRoleByNameOperation(RoleRepository roleRepository,
                                  BaseMapper baseMapper) {
        this.roleRepository = roleRepository;
        this.baseMapper = baseMapper;
    }

    public RoleDto getRoleByName(String roleName) {
        return roleRepository.findByName(roleName)
                .map(r -> baseMapper.mapObject(r, RoleDto.class))
                .orElseThrow(() -> new QaPortalBusinessException("No role found for the supplied name"));
    }
 }
