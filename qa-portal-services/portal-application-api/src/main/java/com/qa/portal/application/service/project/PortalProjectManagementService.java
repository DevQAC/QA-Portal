package com.qa.portal.application.service.project;

import com.qa.portal.application.dto.PortalProjectDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PortalProjectManagementService {

    private CreatePortalProjectOperation createPortalProjectOperation;

    private UpdatePortalProjectOperation updatePortalProjectOperation;

    public PortalProjectManagementService(CreatePortalProjectOperation createPortalProjectOperation,
                                          UpdatePortalProjectOperation updatePortalProjectOperation) {
        this.createPortalProjectOperation = createPortalProjectOperation;
        this.updatePortalProjectOperation = updatePortalProjectOperation;
    }

    @Transactional
    public PortalProjectDto createPortalProject(PortalProjectDto portalProjectDto) {
        return createPortalProjectOperation.createPortalProject(portalProjectDto);
    }

    @Transactional
    public PortalProjectDto updatePortalProject(PortalProjectDto portalProjectDto) {
        PortalProjectDto updatedPortalProject = updatePortalProjectOperation.updatePortalProject(portalProjectDto);
        return updatedPortalProject;
    }
}
