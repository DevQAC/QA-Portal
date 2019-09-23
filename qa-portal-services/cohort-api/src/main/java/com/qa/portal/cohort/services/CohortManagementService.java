package com.qa.portal.cohort.services;

import com.qa.portal.cohort.keycloak.KeycloakCohortResourceManager;
import com.qa.portal.common.dto.QaCohortDto;
import org.springframework.stereotype.Service;

@Service
public class CohortManagementService {

    private KeycloakCohortResourceManager keycloakCohortResourceManager;

    public QaCohortDto createCohort(QaCohortDto cohortDetails) {
        keycloakCohortResourceManager.createCohort(cohortDetails.getName());
        return cohortDetails;
    }

    public QaCohortDto updateCohort(QaCohortDto cohortDetails) {
        return null;
    }
}
