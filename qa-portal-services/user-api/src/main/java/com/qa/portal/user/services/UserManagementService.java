package com.qa.portal.user.services;

import com.qa.portal.common.dto.QaUserDetailsDto;
import com.qa.portal.user.keycloak.KeycloakUserResourceManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementService {

    private KeycloakUserResourceManager keycloakUserResourceManager;

    public UserManagementService(KeycloakUserResourceManager keycloakUserResourceManager) {
        this.keycloakUserResourceManager = keycloakUserResourceManager;
    }

    public List<QaUserDetailsDto> getAllUsers() {
        return keycloakUserResourceManager.getAllUsers();
    }

    public QaUserDetailsDto createUserDetails(QaUserDetailsDto userDetails) {
        keycloakUserResourceManager.createUserAndRole(userDetails);
        return userDetails;
    }

    public QaUserDetailsDto updateUserDetails(QaUserDetailsDto userDetails) {
        keycloakUserResourceManager.updateUser(userDetails);
        return userDetails;
    }

    public void deleteUser(String userName) {
        keycloakUserResourceManager.deleteUser(userName);
    }
}
