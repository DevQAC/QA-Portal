package com.qa.portal.cohort.services.course;

import com.qa.portal.common.dto.CourseDto;
import com.qa.portal.common.dto.CourseTechnologyDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.CourseEntity;
import com.qa.portal.common.persistence.entity.CourseTechnologyEntity;
import com.qa.portal.common.persistence.repository.CourseRepository;
import com.qa.portal.common.persistence.repository.CourseTechnologyRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseManagementService {

    private CourseRepository courseRepository;

    private CourseTechnologyRepository courseTechnologyRepository;

    private BaseMapper baseMapper;

    public CourseManagementService(CourseRepository courseRepository,
                                   CourseTechnologyRepository courseTechnologyRepository,
                                   BaseMapper baseMapper) {
        this.courseRepository = courseRepository;
        this.courseTechnologyRepository = courseTechnologyRepository;
        this.baseMapper = baseMapper;
    }

    @Transactional
    public CourseDto createCourse(CourseDto courseDto) {
        // Check course doesn't already exist - for name or code
        CourseEntity courseEntity = baseMapper.mapObject(courseDto, CourseEntity.class);
        addNewCourseTechnologies(courseEntity, courseDto);
        CourseEntity savedEntity = courseRepository.save(courseEntity);
        return baseMapper.mapObject(savedEntity, CourseDto.class);
    }

    @Transactional
    public CourseDto updateCourse(CourseDto courseDto) {
        // Throw Exception if the course does not exist
        CourseEntity courseEntity = courseRepository.findById(courseDto.getId())
                .orElseThrow(() -> new QaPortalBusinessException("No course found for supplied course id"));
        courseEntity.setDuration(courseDto.getDuration());
        courseEntity.setCourseCode(courseDto.getCourseCode());
        addNewCourseTechnologies(courseEntity, courseDto);
        deleteRemovedCourseTechnologies(courseEntity, courseDto);
        return courseDto;
    }

    private void deleteRemovedCourseTechnologies(CourseEntity courseEntity, CourseDto courseDto) {
        List<String> newCourseTechnologies = getCurrentTechnologiesForCourse(courseDto);
        courseEntity.getCourseTechnologies().stream()
                .filter(ct -> !newCourseTechnologies.contains(ct.getTechnology().getTechnologyName()))
                .collect(Collectors.toList())
                .iterator()
                .forEachRemaining(ct -> courseEntity.removeCourseTechnology(ct));
    }

    private void addNewCourseTechnologies(CourseEntity courseEntity, CourseDto courseDto) {
        List<String> previousCourseTechnologies = getPreviousTechnologiesForCourse(courseEntity);
        List<CourseTechnologyDto> courseTechnologyDtos = Optional.ofNullable(courseDto.getTechnologies())
                .orElseGet(() -> Collections.emptyList());
         courseTechnologyDtos.stream()
                .filter(ctDto -> !previousCourseTechnologies.contains(ctDto.getTechnology().getTechnologyName()))
                .forEach(ctDto -> courseEntity.addCourseTechnology(baseMapper.mapObject(ctDto, CourseTechnologyEntity.class)));
    }

    private List<String> getPreviousTechnologiesForCourse(CourseEntity courseEntity) {
        return Optional.ofNullable(courseEntity.getCourseTechnologies())
                .map(list -> courseEntity.getCourseTechnologies().stream()
                        .map(ct -> ct.getTechnology().getTechnologyName())
                        .collect(Collectors.toList()))
                .orElseGet(() -> Collections.emptyList());
    }

    private List<String> getCurrentTechnologiesForCourse(CourseDto courseDto) {
        return Optional.ofNullable(courseDto.getTechnologies())
                .map(list -> list.stream()
                        .map(ct -> ct.getTechnology().getTechnologyName())
                        .collect(Collectors.toList()))
                .orElseGet(() -> Collections.emptyList());
    }
}
