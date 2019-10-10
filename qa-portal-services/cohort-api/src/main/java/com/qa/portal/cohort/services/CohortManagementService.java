package com.qa.portal.cohort.services;

import com.qa.portal.cohort.keycloak.KeycloakCohortResourceManager;
import com.qa.portal.cohort.keycloak.KeycloakUserResourceManager;
import com.qa.portal.common.dto.QaCohortDto;
import org.springframework.stereotype.Service;

@Service
public class CohortManagementService {

    private KeycloakCohortResourceManager keycloakCohortResourceManager;

    private KeycloakUserResourceManager keycloakUserResourceManager;

    private CreateCohortOperation createCohortOperation;

    private UpdateCohortOperation updateCohortOperation;

    public CohortManagementService(KeycloakCohortResourceManager keycloakCohortResourceManager,
                                   KeycloakUserResourceManager keycloakUserResourceManager,
                                   CreateCohortOperation createCohortOperation,
                                   UpdateCohortOperation updateCohortOperation) {
        this.keycloakCohortResourceManager = keycloakCohortResourceManager;
        this.keycloakUserResourceManager = keycloakUserResourceManager;
        this.createCohortOperation = createCohortOperation;
        this.updateCohortOperation = updateCohortOperation;
    }

    public QaCohortDto createCohort(QaCohortDto cohortDetails) {
        createCohortOperation.createCohort(cohortDetails);
        keycloakCohortResourceManager.createCohort(cohortDetails.getName().replace(' ', '_'));
        keycloakUserResourceManager.updateCohortMembers(cohortDetails);
        return cohortDetails;
    }

    public QaCohortDto updateCohort(QaCohortDto cohortDetails) {
        updateCohortOperation.updateCohort(cohortDetails);
        keycloakUserResourceManager.updateCohortMembers(cohortDetails);
        return cohortDetails;
    }
}
