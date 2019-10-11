package com.qa.portal.cohort.services.course.mapper;

import com.qa.portal.common.dto.TechnologyDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.CourseEntity;
import com.qa.portal.common.persistence.entity.CourseTechnologyEntity;
import com.qa.portal.common.persistence.entity.TechnologyEntity;
import com.qa.portal.common.persistence.repository.TechnologyRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class CourseTechnologyMapper {

    private TechnologyRepository technologyRepository;

    private BaseMapper baseMapper;

    public CourseTechnologyMapper(TechnologyRepository technologyRepository,
                                  BaseMapper baseMapper) {
        this.technologyRepository = technologyRepository;
        this.baseMapper = baseMapper;
    }

    public CourseTechnologyEntity createCourseTechnologyEntity(CourseEntity courseEntity, TechnologyDto technologyDto) {
        TechnologyEntity technologyEntity = getTechnologyEntity(technologyDto);
        CourseTechnologyEntity courseTechnologyEntity = new CourseTechnologyEntity();
        courseTechnologyEntity.setCourse(courseEntity);
        courseTechnologyEntity.setTechnology(technologyEntity);
        return courseTechnologyEntity;
    }

    private TechnologyEntity getTechnologyEntity(TechnologyDto technologyDto) {
        return technologyRepository.findById(technologyDto.getId())
                .orElseThrow(() -> new QaPortalBusinessException("No Technology found for the supplied id"));
    }
}
