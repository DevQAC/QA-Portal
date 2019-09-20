package com.qa.portal.user.services;

import com.qa.portal.common.dto.QaUserDetailsDto;
import com.qa.portal.user.keycloak.KeycloakUserResourceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.qa.portal.common.keycloak.KeycloakUserConstants.TRAINEE_USER_ROLE;
import static com.qa.portal.common.keycloak.KeycloakUserConstants.TRAINER_USER_ROLE;

@Service
public class UserManagementService {

    private KeycloakUserResourceManager keycloakUserResourceManager;

    private CreateUserOperation createUserOperation;

    private UpdateUserOperation updateUserOperation;

    private DeleteUserOperation deleteUserOperation;

    public UserManagementService(KeycloakUserResourceManager keycloakUserResourceManager,
                                 CreateUserOperation createUserOperation,
                                 UpdateUserOperation updateUserOperation,
                                 DeleteUserOperation deleteUserOperation) {
        this.keycloakUserResourceManager = keycloakUserResourceManager;
        this.createUserOperation = createUserOperation;
        this.updateUserOperation = updateUserOperation;
        this.deleteUserOperation = deleteUserOperation;
    }

    public List<QaUserDetailsDto> getAllUsersFromKeycloak() {
        return keycloakUserResourceManager.getAllUsers();
    }

    public List<QaUserDetailsDto> getTrainees() {
        return keycloakUserResourceManager.getAllUsers().stream()
                .filter(u -> u.getRoleName().equals(TRAINEE_USER_ROLE))
                .collect(Collectors.toList());
    }

    public List<QaUserDetailsDto> getTrainers() {
        return keycloakUserResourceManager.getAllUsers().stream()
                .filter(u -> u.getRoleName().equals(TRAINER_USER_ROLE))
                .collect(Collectors.toList());
    }

    public QaUserDetailsDto createUserDetails(QaUserDetailsDto userDetails) {
        createUserOperation.createUserDetails(userDetails);
        keycloakUserResourceManager.createUserAndRole(userDetails);
        return userDetails;
    }

    public List<QaUserDetailsDto> updateUsers(List<QaUserDetailsDto> users) {
        users.stream()
                .forEach(u -> updateUserDetails(u));
        return users;
    }

    public QaUserDetailsDto updateUserDetails(QaUserDetailsDto userDetails) {
        updateUserOperation.updateUserDetails(userDetails);
        keycloakUserResourceManager.updateUser(userDetails);
        return userDetails;
    }

    public void deleteUsers(List<QaUserDetailsDto> users) {
        deleteUserOperation.deleteUsers(users);
        keycloakUserResourceManager.deleteUsers(users);
    }
}
