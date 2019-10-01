package com.qa.portal.cohort.services.user;

import com.qa.portal.cohort.keycloak.KeycloakUserResourceManager;
import com.qa.portal.common.dto.QaUserDetailsDto;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.qa.portal.common.keycloak.KeycloakUserConstants.TRAINEE_USER_ROLE;
import static com.qa.portal.common.keycloak.KeycloakUserConstants.TRAINER_USER_ROLE;

@Service
public class UserManagementService {

    private static final Long COHORT_DURATION = 84L;  // TODO - Assumption is 12 weeks - can calculate from courses when this is added

    private KeycloakUserResourceManager keycloakUserResourceManager;

    private QaCohortRepository cohortRepository;

    private GetTrainerCohortsOperation getTrainerCohortsOperation;

    private CreateUserOperation createUserOperation;

    private UpdateUserOperation updateUserOperation;

    private DeleteUserOperation deleteUserOperation;

    public UserManagementService(KeycloakUserResourceManager keycloakUserResourceManager,
                                 CreateUserOperation createUserOperation,
                                 UpdateUserOperation updateUserOperation,
                                 DeleteUserOperation deleteUserOperation,
                                 GetTrainerCohortsOperation getTrainerCohortsOperation,
                                 QaCohortRepository cohortRepository) {
        this.keycloakUserResourceManager = keycloakUserResourceManager;
        this.createUserOperation = createUserOperation;
        this.updateUserOperation = updateUserOperation;
        this.deleteUserOperation = deleteUserOperation;
        this.getTrainerCohortsOperation = getTrainerCohortsOperation;
        this.cohortRepository = cohortRepository;
    }

    public QaUserDetailsDto getUserFromKeycloak(String userName) {
        return keycloakUserResourceManager.getUser(userName);
    }

    public List<QaUserDetailsDto> getAllUsersFromKeycloak() {
        return keycloakUserResourceManager.getAllUsers();
    }

    public List<QaUserDetailsDto> getTraineesWithoutCohort() {
        return getTrainees().stream()
                .filter(t -> StringUtils.isEmpty(t.getCohortNames()))
                .collect(Collectors.toList());
    }

    public List<QaUserDetailsDto> getAvailableTrainersForCohort(String cohortName) {
        return getTrainers().stream()
                .filter(t -> isAvailableForCohort(cohortName, t.getUser().getUserName()))
                .collect(Collectors.toList());
    }

    public List<QaUserDetailsDto> getTrainees() {
        return keycloakUserResourceManager.getAllUsers().stream()
                .filter(u -> u.getRoleNames().contains(TRAINEE_USER_ROLE))
                .collect(Collectors.toList());
    }

    public List<QaUserDetailsDto> getTrainers() {
        return keycloakUserResourceManager.getAllUsers().stream()
                .filter(u -> u.getRoleNames().contains(TRAINER_USER_ROLE))
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

    private boolean isAvailableForCohort(String cohortName, String trainerName) {
        Optional<QaCohortEntity> cohortEntity = cohortRepository.findByName(cohortName);
        return cohortEntity.map(e -> trainerAvailableForCohort(e, trainerName)).orElseGet(() -> false);
    }

    private boolean trainerAvailableForCohort(QaCohortEntity cohortEntity, String trainerName) {
        LocalDate cohortStart = cohortEntity.getStartDate().toLocalDate();
        LocalDate cohortEnd = cohortStart.plusDays(COHORT_DURATION);
        return getTrainerCohortsOperation.getCohortsForTrainer(trainerName).stream()
                .filter(c -> cohortStart.isBefore(c.getStartDate()) && cohortEnd.isAfter(c.getStartDate()))
                .findAny()
                .map(c -> false)
                .orElseGet(() -> true);
    }
}
