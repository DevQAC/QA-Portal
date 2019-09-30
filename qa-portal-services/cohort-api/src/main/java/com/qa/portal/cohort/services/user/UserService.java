package com.qa.portal.cohort.services.user;

import com.qa.portal.cohort.dto.user.UserSkillsDto;
import com.qa.portal.common.dto.CourseDto;
import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.dto.QaUserDto;
import com.qa.portal.common.dto.TraineeDto;
import com.qa.portal.common.security.QaSecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private GetTraineeCoursesOperation getTraineeCoursesOperation;

    private GetTraineeSkillsOperation getTraineeSkillsOperation;

    private GetTraineeCohortOperation getTraineeCohortOperation;

    private GetTrainerCohortsOperation getTrainerCohortsOperation;

    private GetUsersOperation getUsersOperation;

    private GetTraineeOperation getTraineeOperation;

    public UserService(GetTraineeOperation getTraineeOperation,
                       GetTraineeSkillsOperation getTraineeSkillsOperation,
                       GetTraineeCohortOperation getTraineeCohortOperation,
                       GetTrainerCohortsOperation getTrainerCohortsOperation,
                       GetTraineeCoursesOperation getTraineeCoursesOperation,
                       GetUsersOperation getUsersOperation) {
        this.getTraineeOperation = getTraineeOperation;
        this.getTraineeSkillsOperation = getTraineeSkillsOperation;
        this.getTraineeCohortOperation = getTraineeCohortOperation;
        this.getTrainerCohortsOperation = getTrainerCohortsOperation;
        this.getTraineeCoursesOperation = getTraineeCoursesOperation;
        this.getUsersOperation = getUsersOperation;
    }

    @Transactional
    public QaUserDto getUser(Integer id) {
        return getUsersOperation.getUser(id);
    }

    @Transactional
    public UserSkillsDto getTraineeSkills(QaSecurityContext qaSecurityContext) {
        return getTraineeSkillsOperation.getSkillsForTrainee(qaSecurityContext);
    }

    @Transactional
    public QaCohortDto getTraineeCohort(String traineeUserName) {
        return getTraineeCohortOperation.getCohortForTrainee(traineeUserName);
    }

    @Transactional
    public List<QaCohortDto> getTrainerCohorts(String trainerUserName) {
        return getTrainerCohortsOperation.getCohortsForTrainer(trainerUserName);
    }

    @Transactional
    public List<QaUserDto> getAllUsers() {
        return getUsersOperation.getAllUsers();
    }

    @Transactional
    public List<CourseDto> getTraineeCourses(String username) {
        return this.getTraineeCoursesOperation.getCoursesForTrainee(username);
    }

    @Transactional
    public TraineeDto getTraineeById(Integer id) {
        return getTraineeOperation.getTraineeById(id);
    }
}
