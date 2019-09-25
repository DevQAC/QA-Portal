package com.qa.portal.core.service.application;

import com.qa.portal.core.dto.PortalApplicationDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PortalApplicationManagementService {

    private CreatePortalApplicationOperation createPortalApplicationOperation;

    private UpdatePortalApplicationOperation updatePortalApplicationOperation;

    public PortalApplicationManagementService(CreatePortalApplicationOperation createPortalApplicationOperation,
                                              UpdatePortalApplicationOperation updatePortalApplicationOperation) {
        this.createPortalApplicationOperation = createPortalApplicationOperation;
        this.updatePortalApplicationOperation = updatePortalApplicationOperation;
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
