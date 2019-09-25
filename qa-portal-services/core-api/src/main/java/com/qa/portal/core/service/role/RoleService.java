package com.qa.portal.core.service.role;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.core.dto.RoleDto;
import com.qa.portal.core.persistence.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    private BaseMapper baseMapper;

    public RoleService(RoleRepository roleRepository,
                       BaseMapper baseMapper) {
        this.roleRepository = roleRepository;
        this.baseMapper = baseMapper;
    }

    @Transactional
    public RoleDto getRole(Integer id) {
        return roleRepository.findById(id)
                .map(r -> baseMapper.mapObject(r, RoleDto.class))
                .orElseThrow(() -> new QaPortalBusinessException("No role found for the supplied id"));
    }
}
