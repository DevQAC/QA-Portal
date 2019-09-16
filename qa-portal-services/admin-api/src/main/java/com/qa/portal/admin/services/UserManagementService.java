package com.qa.portal.admin.services;

import com.qa.portal.admin.dto.QaUserAndRoleDto;
import com.qa.portal.admin.keycloak.KeycloakResourceManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserManagementService {

    private KeycloakResourceManager keycloakResourceManager;

    public UserManagementService(KeycloakResourceManager keycloakResourceManager) {
        this.keycloakResourceManager = keycloakResourceManager;
    }

    @Transactional
    public QaUserAndRoleDto createUser(QaUserAndRoleDto userDetails) {
        keycloakResourceManager.createUserAndRole(userDetails);
        return userDetails;
    }

    @Transactional
    public QaUserAndRoleDto updateUser(QaUserAndRoleDto userDetails) {
        return userDetails;
    }

    @Transactional
    public void deleteUser(String userName) {
    }
}
