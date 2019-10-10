package com.qa.portal.feedback.services.mapper;

import com.qa.portal.common.persistence.repository.CohortCourseRepository;
import com.qa.portal.common.persistence.repository.QuestionCategoryRepository;
import com.qa.portal.common.persistence.repository.QuestionRepository;
import com.qa.portal.common.service.mapper.QuestionCategoryResponseMapper;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.feedback.persistence.entity.CohortCourseEvaluationEntity;
import com.qa.portal.feedback.persistence.entity.EvalQuestionCategoryResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EvaluationQuestionCategoryResponseMapper extends QuestionCategoryResponseMapper<EvalQuestionCategoryResponseEntity, CohortCourseEvaluationEntity> {
    public EvaluationQuestionCategoryResponseMapper(BaseMapper baseMapper,
                                                    CohortCourseRepository cohortCourseRepository,
                                                    QuestionCategoryRepository questionCategoryRepository,
                                                    QuestionRepository questionRepository) {
        super(baseMapper, cohortCourseRepository, questionCategoryRepository, questionRepository);
    }
}
