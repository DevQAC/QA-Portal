package com.qa.portal.feedback.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.qa.portal.common.dto.CohortCourseDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.CohortCourseEntity;
import com.qa.portal.feedback.persistence.entity.CohortCourseEvaluationEntity;
import com.qa.portal.feedback.persistence.entity.EvalQuestionCategoryResponseEntity;
import com.qa.portal.feedback.persistence.repository.CohortCourseEvaluationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Stream;

@Component
public class EvaluationRatingsCalculator {

    private final Logger LOGGER = LoggerFactory.getLogger(EvaluationRatingsCalculator.class);

    public static final String NOT_APPLICABLE_STRING = "N/A";

    public static final String SUBMITTED_STATUS = "Submitted";

    private static final String TRAINER_EVALUATION = "Evaluation Trainer";

    private CohortCourseEvaluationRepository cohortCourseEvaluationRepository;

    public EvaluationRatingsCalculator(CohortCourseEvaluationRepository cohortCourseEvaluationRepository) {
        this.cohortCourseEvaluationRepository = cohortCourseEvaluationRepository;
    }

    public OptionalDouble getAverageKnowledgeRatingForCourse(List<CohortCourseEvaluationEntity> evaluations) {
        return getKnowledgeRatingStream(evaluations)
                .mapToInt(s -> Integer.valueOf(s))
                .average();
    }

    public OptionalDouble getTqiRatingForCourseHistory(List<CohortCourseEntity> cohortCourseEntities) {
        return cohortCourseEntities.stream()
                .map(cce -> getTqiRatingForCourse(cohortCourseEvaluationRepository.findByCohortCourse(cce)))
                .filter(tqi -> tqi.isPresent())
                .mapToDouble(tqi -> tqi.getAsDouble())
                .average();
    }

    public OptionalDouble getTqiRatingForCourse(List<CohortCourseEvaluationEntity> evaluations) {
        return getKnowledgeRatingStream(evaluations)
                .mapToInt(s -> getTqiForKnowledgeRating(s))
                .average();
    }

    private Stream<String> getKnowledgeRatingStream(List<CohortCourseEvaluationEntity> evaluations) {
        return evaluations.stream()
                .filter(cce -> cce.getStatus().equals(SUBMITTED_STATUS))
                .flatMap(e -> e.getCategoryResponses().stream())
                .filter(cr -> cr.getQuestionCategory().getCategoryName().equals(TRAINER_EVALUATION))
                .map(cr -> getEvaluationResponseValue(cr))
                .filter(s -> !s.contains(NOT_APPLICABLE_STRING));
    }

    private String getEvaluationResponseValue(EvalQuestionCategoryResponseEntity questionCategoryResponseEntity) {
        return questionCategoryResponseEntity.getQuestionResponses()
                .stream()
                .findFirst()
                .map(qr -> convertResponseValueToString(qr.getResponseValues()))
                .orElseThrow(() -> new QaPortalBusinessException("Error calculating Trainer evaluation"));
    }

    private Integer getTqiForKnowledgeRating(String knowledgeRating) {
        Integer knowledgeRatingInt = Integer.valueOf(knowledgeRating);
        if (knowledgeRatingInt >= 9) {
            return 1;
        }
        if (knowledgeRatingInt <= 6) {
            return -1;
        }
        return 0;
    }

    public OptionalDouble getAverageTqiRatingForCourses(List<CohortCourseDto> cohortCourses) {
        return cohortCourses.stream()
                .filter(cc -> !cc.getTqi().equals(NOT_APPLICABLE_STRING))
                .mapToDouble(cc -> Double.valueOf(cc.getTqi()))
                .average();
    }

    public OptionalDouble getAverageKnowledgeRatingForCourses(List<CohortCourseDto> cohortCourses) {
        return cohortCourses.stream()
                .filter(cc -> !cc.getAverageKnowledgeRating().equals(NOT_APPLICABLE_STRING))
                .mapToDouble(cc -> Double.valueOf(cc.getAverageKnowledgeRating()))
                .average();
    }

    public BigDecimal roundToTwoDecimalPlaces(BigDecimal value) {
        return value.setScale(2, RoundingMode.CEILING);
    }

    private String convertResponseValueToString(String responseValues) {
        try {
            ObjectMapper om = new ObjectMapper();
            TypeFactory typeFactory = om.getTypeFactory();
            List<String> values = om.readValue(responseValues, typeFactory.constructCollectionType(List.class, String.class));
            if (values.size() > 0) {
                return values.get(0);
            }
            return NOT_APPLICABLE_STRING;
        } catch (Exception e) {
            throw new QaPortalBusinessException("Error calculating Trainer evaluation");
        }
    }
}
