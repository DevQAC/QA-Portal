package com.qa.portal.core.service;

import com.qa.portal.core.dto.QaUserDetailsDto;
import com.qa.portal.core.keycloak.KeycloakResourceManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserManagementService {

    private KeycloakResourceManager keycloakResourceManager;

    public UserManagementService(KeycloakResourceManager keycloakResourceManager) {
        this.keycloakResourceManager = keycloakResourceManager;
    }

    @Transactional
    public QaUserDetailsDto createUserDetails(QaUserDetailsDto userDetails) {
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
