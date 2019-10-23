package com.qa.portal.cohort.services.mapper;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.dto.TrainerDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.entity.TrainerEntity;
import com.qa.portal.common.persistence.repository.CourseRepository;
import com.qa.portal.common.persistence.repository.LocationRepository;
import com.qa.portal.common.persistence.repository.QaTrainerRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Optional;

@Component
public class CohortCourseMapper {

    private LocationRepository locationRepository;

    private CourseRepository courseRepository;

    private QaTrainerRepository trainerRepository;

    private BaseMapper baseMapper;

    public CohortCourseMapper(LocationRepository locationRepository,
                              CourseRepository courseRepository,
                              QaTrainerRepository trainerRepository,
                              BaseMapper baseMapper) {
        this.locationRepository = locationRepository;
        this.courseRepository = courseRepository;
        this.trainerRepository = trainerRepository;
        this.baseMapper = baseMapper;
    }

    public CohortCourseDto mapToCohortCourseDto(CohortCourseEntity cohortCourseEntity) {
        CohortCourseDto cohortCourseDto =  baseMapper.mapObject(cohortCourseEntity, CohortCourseDto.class);
        cohortCourseDto.setTrainer(getTrainerDto(cohortCourseEntity.getTrainer()));
        return cohortCourseDto;
    }

    public CohortCourseEntity mapToNewCohortCourseEntity(CohortCourseDto cohortCourseDto, QaCohortEntity cohortEntity) {
        CohortCourseEntity cohortCourseEntity = new CohortCourseEntity();
        cohortCourseEntity.setStartDate(Date.valueOf(cohortCourseDto.getStartDate()));
        cohortCourseEntity.setEndDate(Date.valueOf(cohortCourseDto.getEndDate()));
        cohortCourseEntity.setCohort(cohortEntity);
        cohortCourseEntity.setTrainer(cohortEntity.getTrainer());
        courseRepository.findById(cohortCourseDto.getCourse().getId()).ifPresent(course -> cohortCourseEntity.setCourse(course));
        locationRepository.findById(cohortCourseDto.getLocation().getId()).ifPresent(location -> cohortCourseEntity.setLocation(location));
        cohortEntity.addCohortCourse(cohortCourseEntity);
        return cohortCourseEntity;
    }

    public CohortCourseEntity mapToUpdatedCohortCourseEntity(CohortCourseDto cohortCourseDto, CohortCourseEntity cohortCourseEntity) {
        cohortCourseEntity.setStartDate(Date.valueOf(cohortCourseDto.getStartDate()));
        cohortCourseEntity.setEndDate(Date.valueOf(cohortCourseDto.getEndDate()));
        getTrainer(cohortCourseDto).ifPresent(trainer -> cohortCourseEntity.setTrainer(trainer));
        locationRepository.findById(cohortCourseDto.getLocation().getId()).ifPresent(location -> cohortCourseEntity.setLocation(location));
        return cohortCourseEntity;
    }

    private Optional<TrainerEntity> getTrainer(CohortCourseDto cohortCourseDto) {
        TrainerEntity trainerEntity = null;
        if (cohortCourseDto.getTrainer() != null) {
             trainerEntity = trainerRepository.findByUserName(cohortCourseDto.getTrainer().getUserName())
                    .orElseThrow(() -> new QaPortalBusinessException("No trainer found for supplied username"));
        }
        return Optional.ofNullable(trainerEntity);
    }

    private TrainerDto getTrainerDto(TrainerEntity trainerEntity) {
        TrainerDto trainerDto = new TrainerDto();
        trainerDto.setId(trainerEntity.getId());
        trainerDto.setUserName(trainerEntity.getUserName());
        trainerDto.setFirstName(trainerEntity.getFirstName());
        trainerDto.setLastName(trainerEntity.getLastName());
        return trainerDto;
    }
}
