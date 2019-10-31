package com.qa.portal.cohort.services.course;

import com.qa.portal.cohort.services.user.GetTraineeCoursesOperation;
import com.qa.portal.common.dto.CourseDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.repository.CourseRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private GetTraineeCoursesOperation getTraineeCoursesOperation;

    private CourseRepository courseRepository;

    private BaseMapper baseMapper;

    public CourseService(GetTraineeCoursesOperation getTraineeCoursesOperation,
                         CourseRepository courseRepository,
                         BaseMapper baseMapper) {
        this.getTraineeCoursesOperation = getTraineeCoursesOperation;
        this.courseRepository = courseRepository;
        this.baseMapper = baseMapper;
    }

    @Transactional
    public List<CourseDto> getCoursesForTrainee(String username){
        return this.getTraineeCoursesOperation.getCoursesForTrainee(username);
    }

    @Transactional
    public List<CourseDto> getCourses() {
        return courseRepository.findAll().stream()
                .map(ce -> baseMapper.mapObject(ce, CourseDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public CourseDto getCourseById(Integer courseId) {
        return courseRepository.findById(courseId)
                .map(ce -> baseMapper.mapObject(ce, CourseDto.class))
                .orElseThrow(() -> new QaPortalBusinessException("No Course found for supplied id"));
    }
}
