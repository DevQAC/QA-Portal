package com.qa.portal.admin.services;

import com.qa.portal.admin.dto.QaUserAndRoleDto;
import com.qa.portal.admin.keycloak.KeycloakUserManager;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserManagementService {

    private KeycloakUserManager keycloakUserManager;

    public UserManagementService(KeycloakUserManager keycloakUserManager) {
        this.keycloakUserManager = keycloakUserManager;
    }

    @Transactional
    public QaUserAndRoleDto createUser(QaUserAndRoleDto userDetails) {
        keycloakUserManager.createUserAndRole(userDetails);
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
