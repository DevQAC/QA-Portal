package com.qa.portal.core.service;

import com.qa.portal.core.dto.QaUserDetailsDto;
import com.qa.portal.core.keycloak.KeycloakResourceManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserManagementService {

    private KeycloakResourceManager keycloakResourceManager;

    private CreateUserOperation createUserOperation;

    public UserManagementService(KeycloakResourceManager keycloakResourceManager,
                                 CreateUserOperation createUserOperation) {
        this.keycloakResourceManager = keycloakResourceManager;
        this.createUserOperation = createUserOperation;
    }

    @Transactional
    public QaUserDetailsDto createUserDetails(QaUserDetailsDto userDetails) {
        createUserOperation.createUserDetails(userDetails);
        keycloakResourceManager.createUserAndRole(userDetails);
        return userDetails;
    }

    @Transactional
    public QaUserDetailsDto updateUserDetails(QaUserDetailsDto userDetails) {
        return userDetails;
    }

    @Transactional
    public void deleteUser(String userName) {
    }
}
