package com.qa.portal.cohort.services;

import com.qa.portal.cohort.keycloak.KeycloakCohortResourceManager;
import com.qa.portal.common.dto.QaCohortDto;
import org.springframework.stereotype.Service;

@Service
public class CohortManagementService {

    private KeycloakCohortResourceManager keycloakCohortResourceManager;

    private CreateCohortOperation createCohortOperation;

    private UpdateCohortOperation updateCohortOperation;

    public CohortManagementService(KeycloakCohortResourceManager keycloakCohortResourceManager,
                                   CreateCohortOperation createCohortOperation,
                                   UpdateCohortOperation updateCohortOperation) {
        this.keycloakCohortResourceManager = keycloakCohortResourceManager;
        this.createCohortOperation = createCohortOperation;
        this.updateCohortOperation = updateCohortOperation;
    }

    public QaCohortDto createCohort(QaCohortDto cohortDetails) {
        createCohortOperation.createCohort(cohortDetails);
        keycloakCohortResourceManager.createCohort(cohortDetails.getName().replace(' ', '_'));
        return cohortDetails;
    }

    public QaCohortDto updateCohort(QaCohortDto cohortDetails) {
        return updateCohortOperation.updateCohort(cohortDetails);
    }
}
