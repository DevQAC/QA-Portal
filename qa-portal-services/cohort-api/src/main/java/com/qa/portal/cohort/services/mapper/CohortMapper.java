package com.qa.portal.cohort.services.mapper;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.dto.QaCohortDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.*;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CohortMapper {

    private final Logger LOGGER = LoggerFactory.getLogger(CohortMapper.class);

    private QaCohortRepository cohortRepository;

    private CourseRepository courseRepository;

    private QaTrainerRepository trainerRepository;

    private QaTraineeRepository traineeRepository;

    private  CohortCourseMapper cohortCourseMapper;

    private BaseMapper baseMapper;

    public CohortMapper(QaCohortRepository cohortRepository,
                        QaTrainerRepository trainerRepository,
                        QaTraineeRepository traineeRepository,
                        CourseRepository courseRepository,
                        CohortCourseMapper cohortCourseMapper,
                        BaseMapper baseMapper) {
        this.cohortRepository = cohortRepository;
        this.trainerRepository = trainerRepository;
        this.traineeRepository = traineeRepository;
        this.courseRepository = courseRepository;
        this.cohortCourseMapper = cohortCourseMapper;
        this.baseMapper = baseMapper;
    }

    public QaCohortDto mapToQaCohortDto(QaCohortEntity qaCohortEntity) {
        QaCohortDto cohortDto = baseMapper.mapObject(qaCohortEntity, QaCohortDto.class);
        Optional.ofNullable(qaCohortEntity.getTrainer())
                .ifPresent(t -> cohortDto.setTrainerUserName(t.getUserName()));
        cohortDto.setTraineeNames(new ArrayList<>());
        qaCohortEntity.getTrainees().stream()
                .forEach(te -> cohortDto.getTraineeNames().add(te.getUserName()));
        setCohortCourseDtos(cohortDto, qaCohortEntity);
        return cohortDto;
    }

    public QaCohortEntity mapToNewQaCohortEntity(QaCohortDto qaCohortDto) {
        if (cohortExists(qaCohortDto)) {
            throw new QaPortalBusinessException("Cohort already exists with supplied cohort name");
        }
        QaCohortEntity cohortEntity = baseMapper.mapObject(qaCohortDto, QaCohortEntity.class);
        trainerRepository.findByUserName(qaCohortDto.getTrainerUserName())
                .ifPresent(t -> cohortEntity.setTrainer(t));
        cohortEntity.setStartDate(Date.valueOf(qaCohortDto.getStartDate()));
        cohortEntity.setCohortCourses(new ArrayList<>());
        Optional.ofNullable(qaCohortDto.getCohortCourses())
                .ifPresent(l -> addNewCohortCourses(qaCohortDto, cohortEntity));
        cohortEntity.setTrainees(new HashSet<>());
        Optional.ofNullable(qaCohortDto.getTraineeNames())
                .ifPresent(l -> addNewTrainees(qaCohortDto, cohortEntity));
        return cohortEntity;
    }

    public QaCohortEntity mapToExistingQaCohortEntity(QaCohortDto qaCohortDto) {
        QaCohortEntity cohortEntity = cohortRepository.findById(qaCohortDto.getId())
                .orElseThrow(() -> new QaPortalBusinessException("Cohort not found"));
        trainerRepository.findByUserName(qaCohortDto.getTrainerUserName())
                .ifPresent(t -> cohortEntity.setTrainer(t));
        cohortEntity.setName(qaCohortDto.getName());
        cohortEntity.setStartDate(Date.valueOf(qaCohortDto.getStartDate()));
        updateCohortCourses(qaCohortDto, cohortEntity);
        updateCohortTrainees(qaCohortDto, cohortEntity);
        return cohortEntity;
    }

    private void setCohortCourseDtos(QaCohortDto cohortDto, QaCohortEntity cohortEntity) {
        List<CohortCourseDto> cohortCourses =  cohortEntity.getCohortCourses().stream()
                .map(cc -> cohortCourseMapper.mapToCohortCourseDto(cc))
                .collect(Collectors.toList());
        cohortDto.setCohortCourses(cohortCourses);
    }

    private void updateCohortCourses(QaCohortDto cohortDto, QaCohortEntity cohortEntity) {
        updateExistingCohortCourses(cohortDto, cohortEntity);
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
                .forEach(ccDto -> cohortCourseMapper.mapToNewCohortCourseEntity(ccDto, cohortEntity));
    }

    private void updateExistingCohortCourses(QaCohortDto cohortDto, QaCohortEntity cohortEntity) {
        List<String> existingCohortCourses = getExistingCourseNames(cohortEntity);
        cohortDto.getCohortCourses().stream()
                .filter(cc -> existingCohortCourses.contains(cc.getCourse().getCourseName()))
                .forEach(cc -> cohortCourseMapper.mapToUpdatedCohortCourseEntity(cc, getCohortCourseEntity(cc, cohortEntity)));
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

    private CohortCourseEntity getCohortCourseEntity(CohortCourseDto cohortCourseDto, QaCohortEntity cohortEntity) {
        return cohortEntity.getCohortCourses().stream()
                .filter(cc -> cc.getId().equals(cohortCourseDto.getId()))
                .findFirst()
                .orElseThrow(() -> new QaPortalBusinessException("No Cohort course found for supplied id"));
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

    private boolean cohortExists(QaCohortDto qaCohortDto) {
       return cohortRepository.findByName(qaCohortDto.getName())
                .map(c -> true)
               .orElseGet(() -> false);
    }
}
