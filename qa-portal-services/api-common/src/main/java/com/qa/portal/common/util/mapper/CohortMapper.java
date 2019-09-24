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

    private BaseMapper baseMapper;

    public CohortMapper(QaCohortRepository cohortRepository,
                        QaTrainerRepository trainerRepository,
                        CourseRepository courseRepository,
                        CohortCourseRepository cohortCourseRepository,
                        LocationRepository locationRepository,
                        BaseMapper baseMapper) {
        this.cohortRepository = cohortRepository;
        this.trainerRepository = trainerRepository;
        this.courseRepository = courseRepository;
        this.cohortCourseRepository = cohortCourseRepository;
        this.locationRepository = locationRepository;
        this.baseMapper = baseMapper;
    }

    public QaCohortDto mapToQaCohortDto(QaCohortEntity qaCohortEntity) {
        QaCohortDto cohortDto = baseMapper.mapObject(qaCohortEntity, QaCohortDto.class);
        cohortDto.setTrainerUserName(qaCohortEntity.getTrainer().getUserName());
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
        return cohortEntity;
    }

    public QaCohortEntity mapToExistingQaCohortEntity(QaCohortDto qaCohortDto) {
        QaCohortEntity cohortEntity = cohortRepository.findById(qaCohortDto.getId())
                .orElseThrow(() -> new QaPortalBusinessException("Cohort not found"));
        trainerRepository.findByUserName(qaCohortDto.getTrainerUserName())
                .ifPresent(t -> cohortEntity.setTrainer(t));
        cohortEntity.setStartDate(Date.valueOf(qaCohortDto.getStartDate()));
        updateCohortCourses(qaCohortDto, cohortEntity);
        return cohortEntity;
    }

    private void updateCohortCourses(QaCohortDto cohortDto, QaCohortEntity cohortEntity) {
        deleteRemovedCohortCourses(cohortDto, cohortEntity);
        addNewCohortCourses(cohortDto, cohortEntity);
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
}
