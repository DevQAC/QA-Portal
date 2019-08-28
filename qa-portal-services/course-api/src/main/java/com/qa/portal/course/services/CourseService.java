package com.qa.portal.course.services;

import com.qa.portal.common.dto.CourseDto;
import com.qa.portal.common.persistence.repository.CourseRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    private BaseMapper baseMapper;

    public CourseService(CourseRepository courseRepository, BaseMapper baseMapper) {
        this.courseRepository = courseRepository;
        this.baseMapper = baseMapper;
    }

    @Transactional
    public List<CourseDto> getCourses() {
        return courseRepository.findAll().stream()
                .map(ce -> baseMapper.mapObject(ce, CourseDto.class)).collect(Collectors.toList());
    }
}
