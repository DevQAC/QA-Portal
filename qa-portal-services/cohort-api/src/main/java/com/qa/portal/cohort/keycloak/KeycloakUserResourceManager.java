package com.qa.portal.cohort.keycloak;

import com.qa.portal.cohort.keycloak.mapper.KeycloakUserMapper;
import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.dto.QaUserDetailsDto;
import com.qa.portal.common.email.QaEmailClient;
import com.qa.portal.common.exception.QaMultiStepCommitContext;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.exception.QaPortalMultiStepCommitException;
import com.qa.portal.common.keycloak.KeycloakAdminClient;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.qa.portal.common.keycloak.KeycloakUserConstants.*;

@Component
public class KeycloakUserResourceManager {

    public static final String QA_PORTAL_ACCOUNT_SETUP_EMAIL_SUBJECT = "QA Portal Account Setup";

    public static final String QA_ACCOUNT_SETUP_MESSAGE_BODY_PROPERTY = "qa.account.setup.message.body";

    public static final String QA_ACCOUNT_SETUP_MESSAGE_URL_PROPERTY = "qa.account.setup.message.url";

    private final Logger LOGGER = LoggerFactory.getLogger(KeycloakUserResourceManager.class);

    private KeycloakUserMapper keycloakUserMapper;

    private KeycloakUserValidator keycloakUserValidator;

    private KeycloakUserFactory keycloakUserFactory;

    private KeycloakAdminClient keycloakAdminClient;

    private QaEmailClient qaEmailClient;

    private Environment environment;

    public KeycloakUserResourceManager(KeycloakUserMapper keycloakUserMapper,
                                       KeycloakUserValidator keycloakUserValidator,
                                       KeycloakUserFactory keycloakUserFactory,
                                       KeycloakAdminClient keycloakAdminClient,
                                       QaEmailClient qaEmailClient,
                                       Environment environment) {
        this.keycloakUserMapper = keycloakUserMapper;
        this.keycloakUserValidator = keycloakUserValidator;
        this.keycloakUserFactory = keycloakUserFactory;
        this.keycloakAdminClient = keycloakAdminClient;
        this.qaEmailClient = qaEmailClient;
        this.environment = environment;
    }

    public QaUserDetailsDto getUser(String userName) {
        return keycloakAdminClient.getRealm().users().search(userName)
                .stream()
                .filter(u -> u.getUsername().equals(userName))
                .findFirst()
                .map(u -> keycloakUserMapper.mapToUserDetailsDto(keycloakAdminClient.getRealm().users().get(u.getId())))
                .orElseThrow(() -> new QaPortalBusinessException("No user found for supplied username"));

    }

    public List<QaUserDetailsDto> getAllUsers() {
        return keycloakAdminClient.getRealm().users().list().stream()
                .map(u -> keycloakUserMapper.mapToUserDetailsDto(keycloakAdminClient.getRealm().users().get(u.getId())))
                .collect(Collectors.toList());
    }

    public void createUserAndRoles(QaUserDetailsDto qaUserDetailsDto) {
        try {
            UserRepresentation userRepresentation = createUser(qaUserDetailsDto);
            qaUserDetailsDto.getRoleNames().stream()
                    .forEach(roleName -> assignRoleToUser(userRepresentation, roleName));
        } catch (Exception e) {
            LOGGER.error("Exception creating user is " + e.getMessage(), e);
            throw new QaPortalMultiStepCommitException(new QaMultiStepCommitContext(this.getClass().getName(),
                    qaUserDetailsDto,
                    QaUserDetailsDto.class,
                    2), e.getMessage());
        }
    }

    public UserRepresentation createUser(QaUserDetailsDto userDetails) {
        keycloakUserValidator.validateUser(userDetails, true);
        UserRepresentation userRepresentation = keycloakUserFactory.createKeycloakUser(userDetails.getUser());
        keycloakAdminClient.getRealm().users().create(userRepresentation);
        sendEmail(userRepresentation);
        return getUserRepresentation(userRepresentation).orElseGet(() -> userRepresentation);
    }

    public UserRepresentation updateUser(QaUserDetailsDto userDetails) {
        keycloakUserValidator.validateUser(userDetails, false);
        UserRepresentation userRepresentation = getUserRepresentation(userDetails.getUser().getUserName())
                .orElseThrow(() -> new QaPortalBusinessException("User not found in keycloak for username"));
        List<RoleRepresentation> currentUserRoles = getUserRoles(userRepresentation);
        userRepresentation.setEmail(userDetails.getUser().getEmail());
        userRepresentation.setFirstName(userDetails.getUser().getFirstName());
        userRepresentation.setLastName(userDetails.getUser().getLastName());
        updateUserRoles(userRepresentation, currentUserRoles, userDetails);
        keycloakAdminClient.getRealm().users().get(userRepresentation.getId()).update(userRepresentation);
        return userRepresentation;
    }

    public void deleteUsers(List<QaUserDetailsDto> users) {
        users.stream()
                .filter(u -> u != null)  // Workaround for issue in angular - Ian to fix
                .forEach(u -> deleteUser(u.getUser().getUserName()));
    }

    public void assignRoleToUser(UserRepresentation userRepresentation, String roleName) {
        RoleRepresentation roleRepresentation = getRoleRepresentation(roleName);
        UserResource userResource = keycloakAdminClient.getRealm().users().get(userRepresentation.getId());
        userResource.roles().realmLevel().add(Arrays.asList(roleRepresentation));
    }

    private List<RoleRepresentation> getUserRoles(UserRepresentation userRepresentation) {
        return keycloakAdminClient.getRealm().users().get(userRepresentation.getId()).roles().getAll().getRealmMappings()
                .stream()
                .map(r -> getRoleRepresentation(r.getName()))
                .collect(Collectors.toList());
    }

    private void deleteUser(String userName) {
        String id = keycloakAdminClient.getRealm().users().list().stream()
                .filter(u -> u.getUsername().equals(userName))
                .findFirst()
                .map(u -> u.getId())
                .orElseThrow(() -> new QaPortalBusinessException("No user found for supplied username"));
        Response response = keycloakAdminClient.getRealm().users().delete(id);
        if (response.getStatus() >= HttpStatus.BAD_REQUEST.value()) {
            throw new QaPortalBusinessException("Error deleting user");
        }
    }

    private void updateUserRoles(UserRepresentation userRepresentation,
                                 List<RoleRepresentation> currentUserRoles,
                                 QaUserDetailsDto userDetailsDto) {
        List<RoleRepresentation> currentPortalRoles = currentUserRoles.stream()
                .filter(r -> isPortalRole(r.getName()))
                .collect(Collectors.toList());
        updateRolesForUser(userRepresentation, currentPortalRoles, userDetailsDto.getRoleNames());
    }

    private boolean isPortalRole(String roleName) {
        return !roleName.equals(UMA_AUTH_ROLE) &&
                !roleName.equals(OFFLINE_ACCESS_ROLE) &&
                !roleName.startsWith((COHORT_ROLE_PREFIX));
    }

    private void updateRolesForUser(UserRepresentation userRepresentation,
                                    List<RoleRepresentation> existingPortalRoles,
                                    List<String> newPortalRoles) {
        UserResource userResource = keycloakAdminClient.getRealm().users().get(userRepresentation.getId());
        if (!existingPortalRoles.equals(newPortalRoles)) {
            // Delete old roles
            deleteRolesFromUser(userResource, existingPortalRoles);

            // Assign new roles
            newPortalRoles.stream()
                    .forEach(r -> addRoleToUser(userResource, r));
        }
    }

    private void deleteRolesFromUser(UserResource userResource, List<RoleRepresentation> rolesToDelete) {
        userResource.roles().realmLevel().remove(rolesToDelete);
    }

    private void addRoleToUser(UserResource userResource, String roleName) {
        List<RoleRepresentation> rolesToAdd = convertToRoleRepresentationList(roleName);
        userResource.roles().realmLevel().add(rolesToAdd);
    }

    private List<RoleRepresentation> convertToRoleRepresentationList(String s) {
        return Stream.of(s)
                .map(r -> getRoleRepresentation(r))
                .collect(Collectors.toList());
    }

    private Optional<UserRepresentation> getUserRepresentation(UserRepresentation userRepresentation) {
        return keycloakAdminClient.getRealm().users().list().stream()
                .filter(u -> u.getUsername().equals(userRepresentation.getUsername()))
                .findFirst();
    }

    private RoleRepresentation getRoleRepresentation(String roleName) {
        return keycloakAdminClient.getRealm().roles().list().stream()
                .filter(r -> r.getName().equals(roleName))
                .findFirst()
                .orElseThrow(() -> new QaPortalBusinessException("No role found for supplied role name"));
    }

    private String getEmailBody(UserRepresentation userRepresentation) {
        String emailBody = String.format(environment.getProperty(QA_ACCOUNT_SETUP_MESSAGE_BODY_PROPERTY),
                environment.getProperty(KeycloakUserResourceManager.QA_ACCOUNT_SETUP_MESSAGE_URL_PROPERTY),
                userRepresentation.getUsername(),
                userRepresentation.getCredentials().get(0).getValue());
        return emailBody;
    }

    public void sendEmail(UserRepresentation userRepresentation) {
        qaEmailClient.sendEmail(userRepresentation.getUsername(),
                QA_PORTAL_ACCOUNT_SETUP_EMAIL_SUBJECT,
                getEmailBody(userRepresentation));
    }

    public void updateCohortMembers(QaCohortDto cohortDto) {
        RoleRepresentation cohortRole = getCohortRole(cohortDto.getName());
        LOGGER.info("Cohort role is " + cohortRole);
        List<UserRepresentation> existingCohortMembers = getKeycloakUsersForCohort(cohortDto.getName());
        List<String> existingCohortMemberNames = existingCohortMembers.stream()
                .map(u -> u.getUsername())
                .collect(Collectors.toList());
        existingCohortMembers.stream()
                .filter(u -> !(cohortDto.getTraineeNames().contains(u.getUsername()) ||
                        cohortDto.getTrainerUserName().equals(u.getUsername())))
                .forEach(u -> removeMemberFromCohort(u, cohortRole));

        List<UserRepresentation> newCohortMembers = getNewCohortMembers(cohortDto);
        newCohortMembers.stream()
                .filter(u -> !existingCohortMemberNames.contains(u.getUsername()))
                .forEach(u -> assignRoleToUser(u, cohortRole.getName()));
    }

    private List<UserRepresentation> getKeycloakUsersForCohort(String cohortName) {
        return keycloakAdminClient.getRealm().users().list().stream()
                .filter(u -> userInCohort(u, getCohortRoleName(cohortName)))
                .collect(Collectors.toList());
    }

    private List<UserRepresentation> getNewCohortMembers(QaCohortDto cohortDto) {
        return Stream.concat(getTraineeNames(cohortDto).stream(), Stream.of(cohortDto.getTrainerUserName()))
                .map(t -> getUserRepresentation(t))
                .filter(t -> t.isPresent())
                .map(t -> t.get())
                .collect(Collectors.toList());
    }

    private List<String> getTraineeNames(QaCohortDto cohortDto) {
        return Optional.ofNullable(cohortDto.getTraineeNames())
                .orElseGet(() -> Collections.emptyList());
    }

    private Optional<UserRepresentation> getUserRepresentation(String userName) {
        return keycloakAdminClient.getRealm().users().list().stream()
                .filter(u -> u.getUsername().equals(userName))
                .findFirst();
    }

    private boolean userInCohort(UserRepresentation userRepresentation, String cohortName) {
        return keycloakAdminClient.getRealm().users().get(userRepresentation.getId())
                .roles().realmLevel().listAll().stream()
                .filter(r -> cohortName.equals(r.getName()))
                .findAny()
                .map(r -> true)
                .orElseGet(() -> false);
    }

    private void removeMemberFromCohort(UserRepresentation userRepresentation, RoleRepresentation cohort) {
        keycloakAdminClient.getRealm().users().get(userRepresentation.getId())
                .roles().realmLevel()
                .remove(Stream.of(cohort).collect(Collectors.toList()));
    }

    private RoleRepresentation getCohortRole(String cohortName) {
        return keycloakAdminClient.getRealm().roles().list().stream()
                .filter(r -> r.getName().equals(getCohortRoleName(cohortName)))
                .findFirst()
                .orElseThrow(() -> new QaPortalBusinessException("No Cohort on Keycloak for supplied name"));
    }

    private String getCohortRoleName(String cohortName) {
        return COHORT_ROLE_PREFIX + cohortName.replace(' ', '_');
    }
}
