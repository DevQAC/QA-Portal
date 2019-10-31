package com.qa.portal.cohort.rest;

import com.qa.portal.cohort.dto.user.UserSkillsDto;
import com.qa.portal.cohort.services.user.UserService;
import com.qa.portal.common.dto.CourseDto;
import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.dto.QaUserDto;
import com.qa.portal.common.dto.TraineeDto;
import com.qa.portal.common.security.QaSecurityContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserService service;

    private QaSecurityContext securityContext;

    public UserController(UserService service, QaSecurityContext securityContext) {
        this.service = service;
        this.securityContext = securityContext;
    }

    @GetMapping("users")
    public ResponseEntity<List<QaUserDto>> getUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("user/{id}")
    public ResponseEntity<QaUserDto> getUser(@PathVariable("id") Integer userId) {
        return ResponseEntity.ok(service.getUser(userId));
    }

    @GetMapping("/user/trainee/{id}")
	public ResponseEntity<TraineeDto> getTraineeById(@PathVariable Integer id) {
		return ResponseEntity.ok(this.service.getTraineeById(id));
	}

    @GetMapping("/user/trainer/cohorts")
    public ResponseEntity<List<QaCohortDto>> getTrainerCohorts() {
        return ResponseEntity.ok(this.service.getTrainerCohorts(securityContext.getUserName()));
    }

    @GetMapping("/user/trainee/cohort")
    public ResponseEntity<QaCohortDto> getTraineeCohort(){
        return ResponseEntity.ok(this.service.getTraineeCohort(securityContext.getUserName()));
    }

    @GetMapping("/user/trainee/skills")
    public ResponseEntity<UserSkillsDto> getTraineeSkills() {
        return ResponseEntity.ok(service.getTraineeSkills(securityContext));
    }

    @GetMapping("/user/trainee/course")
    public ResponseEntity<List<CourseDto>> getCurrentTraineeCourses(){
        return ResponseEntity.ok(this.service.getTraineeCourses(securityContext.getUserName()));
    }

    @GetMapping("/user/trainee/{userName}/course")
    public ResponseEntity<List<CourseDto>> getTraineeCourses(@PathVariable("userName") String userName){
        return ResponseEntity.ok(this.service.getTraineeCourses(userName));
    }
}
