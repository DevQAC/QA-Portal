package com.qa.portal.user.services;

import com.qa.portal.common.dto.TechnologyDto;
import com.qa.portal.common.persistence.entity.CourseEntity;
import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.common.persistence.repository.TechnologyRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GetSkillsForTraineeOperation {

    private TechnologyRepository technologyRepository;

    private QaTraineeRepository traineeRepository;

    private BaseMapper baseMapper;


    public GetSkillsForTraineeOperation(TechnologyRepository technologyRepository,
                                        QaTraineeRepository traineeRepository,
                                        BaseMapper baseMapper) {
        this.technologyRepository = technologyRepository;
        this.traineeRepository = traineeRepository;
        this.baseMapper = baseMapper;
    }

    public Map<String, List<TechnologyDto>> getSkillsForTrainee(String traineeUserName) {
        return traineeRepository.findByUserName(traineeUserName)
                .map(t -> getSkillsForTrainee(t))
                .orElseGet(() -> Collections.emptyMap());
    }

    private Map<String, List<TechnologyDto>> getSkillsForTrainee(TraineeEntity traineeEntity) {
        return traineeEntity.getCohort().getCohortCourses().stream()
                .flatMap(cce -> getTechnologiesForCourse(cce.getCourse()).stream())
                .collect(Collectors.groupingBy(TechnologyDto::getTechnologyCategoryName, Collectors.toList()));
    }

    private List<TechnologyDto> getTechnologiesForCourse(CourseEntity courseEntity) {
        return courseEntity.getCourseTechnologies().stream()
                .map(cte -> baseMapper.mapObject(cte.getTechnology(), TechnologyDto.class))
                .collect(Collectors.toList());
    }
}
