package com.qa.portal.cohort.services.user;

import com.qa.portal.common.dto.CourseDto;
import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetTraineeCoursesOperation {

    private QaTraineeRepository traineeRepository;

    private BaseMapper baseMapper;

    public GetTraineeCoursesOperation(QaTraineeRepository traineeRepository, BaseMapper baseMapper) {
        this.traineeRepository = traineeRepository;
        this.baseMapper = baseMapper;
    }

    public List<CourseDto> getCoursesForTrainee(String traineeUserName){
        return traineeRepository.findByUserName(traineeUserName)
                .map(t -> getCoursesForTrainee(t))
                .orElseGet(() -> Collections.emptyList());

    }

    private List<CourseDto> getCoursesForTrainee(TraineeEntity traineeEntity) {
        return traineeEntity.getCohort().getCohortCourses().stream()
                .map(cce -> cce.getCourse())
                .map(ce -> baseMapper.mapObject(ce, CourseDto.class))
                .collect(Collectors.toList());
    }
}
