package com.qa.portal.cohort.services.mapper;

import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.CourseRepository;
import com.qa.portal.common.persistence.repository.LocationRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class CohortCourseMapper {

    private LocationRepository locationRepository;

    private CourseRepository courseRepository;

    private BaseMapper baseMapper;

    public CohortCourseMapper(LocationRepository locationRepository,
                              CourseRepository courseRepository,
                              BaseMapper baseMapper) {
        this.locationRepository = locationRepository;
        this.courseRepository = courseRepository;
        this.baseMapper = baseMapper;
    }

    public CohortCourseDto mapToCohortCourseDto(CohortCourseEntity cohortCourseEntity) {
        return baseMapper.mapObject(cohortCourseEntity, CohortCourseDto.class);
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
        return cohortCourseEntity;
    }
}
