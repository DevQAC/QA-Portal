package com.qa.portal.application.service.role;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.application.dto.RoleDto;
import com.qa.portal.application.persistence.entity.RoleEntity;
import com.qa.portal.application.persistence.repository.PortalApplicationRepository;
import com.qa.portal.application.persistence.repository.RoleRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UpdateRoleOperation {

    private RoleRepository roleRepository;

    private PortalApplicationRepository portalApplicationRepository;

    private BaseMapper baseMapper;

    public UpdateRoleOperation(RoleRepository roleRepository,
                               PortalApplicationRepository portalApplicationRepository,
                               BaseMapper baseMapper) {
        this.roleRepository = roleRepository;
        this.portalApplicationRepository = portalApplicationRepository;
        this.baseMapper = baseMapper;
    }

    @Transactional
    public RoleDto updateRole(RoleDto roleDto) {
        return getRole(roleDto)
                .map(r -> persistRole(r, roleDto))
                .orElseThrow(() -> new QaPortalBusinessException("Role not found for supplied name"));
    }

    private RoleDto persistRole(RoleEntity roleEntity, RoleDto roleDto) {
        portalApplicationRepository.findByName(roleDto.getPortalApplication().getName())
                .ifPresent(pa -> roleEntity.setPortalApplication(pa));
        return baseMapper.mapObject(roleEntity, RoleDto.class);
    }

    private Optional<RoleEntity> getRole(RoleDto roleDto) {
        return roleRepository.findByName(roleDto.getName());
    }
}
