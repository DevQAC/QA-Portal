package com.qa.portal.application.service.role;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.application.dto.RoleDto;
import com.qa.portal.application.persistence.entity.RoleEntity;
import com.qa.portal.application.persistence.repository.PortalApplicationRepository;
import com.qa.portal.application.persistence.repository.RoleRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateRoleOperation {

    private RoleRepository roleRepository;

    private PortalApplicationRepository portalApplicationRepository;

    private BaseMapper baseMapper;

    public CreateRoleOperation(RoleRepository roleRepository,
                               PortalApplicationRepository portalApplicationRepository,
                               BaseMapper baseMapper) {
        this.roleRepository = roleRepository;
        this.portalApplicationRepository = portalApplicationRepository;
        this.baseMapper = baseMapper;
    }

    @Transactional
    public RoleDto createRole(RoleDto roleDto) {
        if (roleExists(roleDto)) {
            throw new QaPortalBusinessException("Role exists for supplied name");
        }

        RoleEntity roleEntity = baseMapper.mapObject(roleDto, RoleEntity.class);
        portalApplicationRepository.findByName(roleDto.getPortalApplication().getName())
                .ifPresent(pa -> roleEntity.setPortalApplication(pa));
        RoleEntity savedRole = roleRepository.save(roleEntity);
        return baseMapper.mapObject(savedRole, RoleDto.class);
    }

    private boolean roleExists(RoleDto roleDto) {
        return roleRepository.findByName(roleDto.getName())
                .map(r -> true)
                .orElseGet(() -> false);
    }
}
