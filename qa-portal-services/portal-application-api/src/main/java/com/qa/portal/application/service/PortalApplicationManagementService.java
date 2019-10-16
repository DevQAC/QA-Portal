package com.qa.portal.application.service;

import com.qa.portal.application.dto.ApplicationProjectDto;
import com.qa.portal.application.dto.ApplicationProjectsDto;
import com.qa.portal.application.dto.PortalApplicationDto;
import com.qa.portal.application.persistence.entity.RoleProjectPageEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PortalApplicationManagementService {

    private CreatePortalApplicationOperation createPortalApplicationOperation;

    private UpdatePortalApplicationOperation updatePortalApplicationOperation;

    private GetAllPortalApplicationOperation getAllPortalApplicationOperation;

    private GetPortalApplicationByIdOperation getPortalApplicationByIdOperation;

    public PortalApplicationManagementService(CreatePortalApplicationOperation createPortalApplicationOperation,
                                              UpdatePortalApplicationOperation updatePortalApplicationOperation,
                                              GetAllPortalApplicationOperation getAllPortalApplicationOperation,
                                              GetPortalApplicationByIdOperation getPortalApplicationByIdOperation) {
        this.createPortalApplicationOperation = createPortalApplicationOperation;
        this.updatePortalApplicationOperation = updatePortalApplicationOperation;
        this.getAllPortalApplicationOperation = getAllPortalApplicationOperation;
        this.getPortalApplicationByIdOperation = getPortalApplicationByIdOperation;
    }

    @Transactional
    public List<PortalApplicationDto> getAllPortalApplications() {
        return getAllPortalApplicationOperation.getAllPortalApplications();
    }

    @Transactional
    public ApplicationProjectsDto getPortalApplicationById(Integer id) {
        return getPortalApplicationByIdOperation.getPortalApplicationById(id);
    }

    @Transactional
    public PortalApplicationDto createPortalApplication(PortalApplicationDto portalApplicationDto) {
        return createPortalApplicationOperation.createPortalApplication(portalApplicationDto);
    }

    @Transactional
    public PortalApplicationDto updatePortalApplication(PortalApplicationDto portalApplicationDto) {
        return updatePortalApplicationOperation.updatePortalApplication(portalApplicationDto);
    }
}
