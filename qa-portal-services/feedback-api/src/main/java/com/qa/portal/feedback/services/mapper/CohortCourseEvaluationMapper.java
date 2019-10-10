package com.qa.portal.feedback.services.mapper;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.TraineeEntity;
import com.qa.portal.common.persistence.repository.QaTraineeRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.feedback.dto.CohortCourseEvaluationDto;
import com.qa.portal.feedback.persistence.entity.CohortCourseEvaluationEntity;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CohortCourseEvaluationMapper {

    private final Logger LOGGER = LoggerFactory.getLogger(CohortCourseEvaluationMapper.class);

    private BaseMapper baseMapper;

    private QaTraineeRepository qaTraineeRepository;

    private EvaluationQuestionCategoryResponseMapper evaluationQuestionCategoryResponseMapper;

    private CohortCourseEvaluationRepository cohortCourseEvaluationRepository;

    public CohortCourseEvaluationMapper(BaseMapper baseMapper,
                                        QaTraineeRepository qaTraineeRepository,
                                        EvaluationQuestionCategoryResponseMapper evaluationQuestionCategoryResponseMapper,
                                        CohortCourseEvaluationRepository cohortCourseEvaluationRepository) {
        this.baseMapper = baseMapper;
        this.qaTraineeRepository = qaTraineeRepository;
        this.evaluationQuestionCategoryResponseMapper = evaluationQuestionCategoryResponseMapper;
        this.cohortCourseEvaluationRepository = cohortCourseEvaluationRepository;
    }

    public CohortCourseEvaluationDto mapToCohortCourseEvaluationDto(CohortCourseEvaluationEntity cohortCourseEvaluationEntity) {
        CohortCourseEvaluationDto cohortCourseEvaluationDto =  baseMapper.mapObject(cohortCourseEvaluationEntity, CohortCourseEvaluationDto.class);
        cohortCourseEvaluationDto.setCategoryResponses(cohortCourseEvaluationDto.getCategoryResponses()
                .stream()
                .collect(Collectors.toList()));
        return cohortCourseEvaluationDto;
    }
    
    public CohortCourseEvaluationEntity createCohortCourseEvaluationEntity(CohortCourseEvaluationDto cohortCourseEvaluationDto,
            String traineeUserName) {
        CohortCourseEvaluationEntity cohortCourseEvaluationEntity = baseMapper.mapObject(cohortCourseEvaluationDto, CohortCourseEvaluationEntity.class);
        cohortCourseEvaluationEntity.setCategoryResponses(
                evaluationQuestionCategoryResponseMapper.createCategoryResponsesEntities(cohortCourseEvaluationDto.getCategoryResponses(), cohortCourseEvaluationEntity));
        cohortCourseEvaluationEntity.setCohortCourse(evaluationQuestionCategoryResponseMapper.getCohortCourseEntity(cohortCourseEvaluationDto.getCohortCourse()));
        cohortCourseEvaluationEntity.setTrainee(getTraineeEntity(traineeUserName));
        return cohortCourseEvaluationEntity;
    }

    public CohortCourseEvaluationEntity updateCohortCourseEvaluationEntity(CohortCourseEvaluationDto cohortCourseEvaluationDto) {
        CohortCourseEvaluationEntity cohortCourseEvaluationEntity = getExistingCohortCourseEvaluationEntity(cohortCourseEvaluationDto.getId());
        evaluationQuestionCategoryResponseMapper.setUpdatedCategoryResponses(cohortCourseEvaluationEntity.getCategoryResponses(),
                cohortCourseEvaluationDto.getCategoryResponses());
        cohortCourseEvaluationEntity.setStatus(cohortCourseEvaluationDto.getStatus());
        return cohortCourseEvaluationEntity;
    }

    private CohortCourseEvaluationEntity getExistingCohortCourseEvaluationEntity(Integer id) {
        return cohortCourseEvaluationRepository.findById(id).orElseThrow(() -> new QaPortalBusinessException("Cannot find course evaluation"));
    }

    private TraineeEntity getTraineeEntity(String traineeUserName) {
        return qaTraineeRepository.findByUserName(traineeUserName)
                .orElseThrow(() -> new QaPortalBusinessException("Cannot find trainee for course evaluation"));
    }
}
