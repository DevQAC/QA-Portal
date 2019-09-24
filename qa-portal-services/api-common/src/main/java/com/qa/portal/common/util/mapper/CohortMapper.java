package com.qa.portal.common.util.mapper;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CohortMapper {

    private final Logger LOGGER = LoggerFactory.getLogger(CohortMapper.class);

    private QaCohortRepository cohortRepository;

    private CohortCourseRepository cohortCourseRepository;

    private LocationRepository locationRepository;

    private CourseRepository courseRepository;

    private QaTrainerRepository trainerRepository;

    private QaTraineeRepository traineeRepository;

    private BaseMapper baseMapper;

    public CohortMapper(QaCohortRepository cohortRepository,
                        QaTrainerRepository trainerRepository,
                        QaTraineeRepository traineeRepository,
                        CourseRepository courseRepository,
                        CohortCourseRepository cohortCourseRepository,
                        LocationRepository locationRepository,
                        BaseMapper baseMapper) {
        this.cohortRepository = cohortRepository;
        this.trainerRepository = trainerRepository;
        this.traineeRepository = traineeRepository;
        this.courseRepository = courseRepository;
        this.cohortCourseRepository = cohortCourseRepository;
        this.locationRepository = locationRepository;
        this.baseMapper = baseMapper;
    }

    public QaCohortDto mapToQaCohortDto(QaCohortEntity qaCohortEntity) {
        QaCohortDto cohortDto = baseMapper.mapObject(qaCohortEntity, QaCohortDto.class);
        cohortDto.setTrainerUserName(qaCohortEntity.getTrainer().getUserName());
        cohortDto.setTraineeNames(new ArrayList<>());
        qaCohortEntity.getTrainees().stream()
                .forEach(te -> cohortDto.getTraineeNames().add(te.getUserName()));
        return cohortDto;
    }

    public QaCohortEntity mapToNewQaCohortEntity(QaCohortDto qaCohortDto) {
        cohortRepository.findByName(qaCohortDto.getName())
                .ifPresent(c -> {
                    throw new QaPortalBusinessException("Cohort already exists");
                });
        QaCohortEntity cohortEntity = baseMapper.mapObject(qaCohortDto, QaCohortEntity.class);
        trainerRepository.findByUserName(qaCohortDto.getTrainerUserName())
                .ifPresent(t -> cohortEntity.setTrainer(t));
        cohortEntity.setStartDate(Date.valueOf(qaCohortDto.getStartDate()));
        cohortEntity.setCohortCourses(new ArrayList<>());
        addNewCohortCourses(qaCohortDto, cohortEntity);
        cohortEntity.setTrainees(new HashSet<>());
        addNewTrainees(qaCohortDto, cohortEntity);
        return cohortEntity;
    }

    public QaCohortEntity mapToExistingQaCohortEntity(QaCohortDto qaCohortDto) {
        QaCohortEntity cohortEntity = cohortRepository.findById(qaCohortDto.getId())
                .orElseThrow(() -> new QaPortalBusinessException("Cohort not found"));
        trainerRepository.findByUserName(qaCohortDto.getTrainerUserName())
                .ifPresent(t -> cohortEntity.setTrainer(t));
        cohortEntity.setStartDate(Date.valueOf(qaCohortDto.getStartDate()));
        updateCohortCourses(qaCohortDto, cohortEntity);
        updateCohortTrainees(qaCohortDto, cohortEntity);
        return cohortEntity;
    }

    private void updateCohortCourses(QaCohortDto cohortDto, QaCohortEntity cohortEntity) {
        deleteRemovedCohortCourses(cohortDto, cohortEntity);
        addNewCohortCourses(cohortDto, cohortEntity);
    }

    private void deleteRemovedCohortCourses(QaCohortDto cohortDto, QaCohortEntity cohortEntity) {
        List<String> courseNames = getNewCourseNames(cohortDto);
        cohortEntity.getCohortCourses().stream()
                .filter(cc -> !courseNames.contains(cc.getCourse().getCourseName()))
                .collect(Collectors.toList())
                .iterator().forEachRemaining(cc -> deleteCohortCourse(cc, cohortEntity));
    }

    private void addNewCohortCourses(QaCohortDto cohortDto, QaCohortEntity cohortEntity) {
        List<String> existingCourseNames = getExistingCourseNames(cohortEntity);
        cohortDto.getCohortCourses().stream()
                .filter(cc -> !existingCourseNames.contains(cc.getCourse().getCourseName()))
                .forEach(ccDto -> createNewCohortCourseEntity(ccDto, cohortEntity));
    }

    private List<String> getNewCourseNames(QaCohortDto cohortDto) {
        return cohortDto.getCohortCourses().stream()
                .map(cc -> cc.getCourse().getCourseName())
                .collect(Collectors.toList());
    }

    private List<String> getExistingCourseNames(QaCohortEntity cohortEntity) {
        return cohortEntity.getCohortCourses().stream()
                .map(cc -> cc.getCourse().getCourseName())
                .collect(Collectors.toList());
    }

    private void createNewCohortCourseEntity(CohortCourseDto cohortCourseDto, QaCohortEntity cohortEntity) {
        CohortCourseEntity cohortCourseEntity = new CohortCourseEntity();
        cohortCourseEntity.setStartDate(Date.valueOf(cohortCourseDto.getStartDate()));
        cohortCourseEntity.setEndDate(Date.valueOf(cohortCourseDto.getEndDate()));
        cohortCourseEntity.setCohort(cohortEntity);
        cohortCourseEntity.setTrainer(cohortEntity.getTrainer());
        courseRepository.findByCourseName(cohortCourseDto.getCourse().getCourseName()).ifPresent(course -> cohortCourseEntity.setCourse(course));
        locationRepository.findById(cohortCourseDto.getLocation().getId()).ifPresent(location -> cohortCourseEntity.setLocation(location));
        cohortEntity.addCohortCourse(cohortCourseEntity);
    }

    private void deleteCohortCourse(CohortCourseEntity cohortCourseEntity, QaCohortEntity cohortEntity) {
        cohortEntity.removeCohortCourse(cohortCourseEntity);
    }

    private void updateCohortTrainees(QaCohortDto cohortDto, QaCohortEntity cohortEntity) {
        deleteRemovedTrainees(cohortDto, cohortEntity);
        addNewTrainees(cohortDto, cohortEntity);
    }

    private void addNewTrainees(QaCohortDto cohortDto, QaCohortEntity cohortEntity) {
        List<String> existingTraineeNames = getExistingTraineeNames(cohortEntity);
        cohortDto.getTraineeNames().stream()
                .filter(t -> !existingTraineeNames.contains(t))
                .forEach(t -> assignTrainee(t, cohortEntity));
    }

    private void deleteRemovedTrainees(QaCohortDto cohortDto, QaCohortEntity cohortEntity) {
        List<String> newTraineeNames = getNewTraineeNames(cohortDto);
        cohortEntity.getTrainees().stream()
                .filter(te -> !newTraineeNames.contains(te.getUserName()))
                .collect(Collectors.toList())
                .iterator()
                .forEachRemaining(te -> cohortEntity.removeTrainee(te));
    }

    private List<String> getNewTraineeNames(QaCohortDto cohortDto) {
        return cohortDto.getTraineeNames().stream()
                .collect(Collectors.toList());
    }

    private List<String> getExistingTraineeNames(QaCohortEntity cohortEntity) {
        return cohortEntity.getTrainees().stream()
                .map(t -> t.getUserName())
                .collect(Collectors.toList());
    }

    private void assignTrainee(String traineeName, QaCohortEntity cohortEntity) {
        traineeRepository.findByUserName(traineeName)
                .ifPresent(te -> cohortEntity.addTrainee(te));
    }
}
