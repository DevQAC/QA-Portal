package com.qa.portal.reflection.service;

import com.qa.portal.common.persistence.entity.QaCohortEntity;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.reflection.dto.CohortSummaryDto;
import com.qa.portal.reflection.persistence.entity.ReflectionEntity;
import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionQuestionRepository;
import com.qa.portal.reflection.persistence.repository.ReflectionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionQuestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class GetCohortSummaryOperation {
    private final Logger LOGGER = LoggerFactory.getLogger(GetCohortSummaryOperation.class);

    private QaCohortRepository cohortRepository;

    private ReflectionRepository reflectionRepository;

    private ReflectionQuestionRepository reflectionQuestionRepository;

    private ReflectionQuestionMapper reflectionQuestionMapper;

    private static final int NUM_COHORT_WEEKS = 13;

    @Autowired
    public GetCohortSummaryOperation(QaCohortRepository cohortRepository,
                                     ReflectionRepository reflectionRepository,
                                     ReflectionQuestionRepository reflectionQuestionRepository,
                                     ReflectionQuestionMapper reflectionQuestionMapper) {
        this.cohortRepository = cohortRepository;
        this.reflectionRepository = reflectionRepository;
        this.reflectionQuestionRepository = reflectionQuestionRepository;
        this.reflectionQuestionMapper = reflectionQuestionMapper;
    }

    public List<CohortSummaryDto> getCohortSummaries() {
        return this.cohortRepository.findAll().stream()
                .filter(cohort -> cohort.getStartDate().toLocalDate().isBefore(LocalDate.now().plusDays(7)))
                .map(this::createCohortSummary)
                .collect(Collectors.toList());
    }

    private CohortSummaryDto createCohortSummary(QaCohortEntity qaCohortEntity) {
        return Optional.ofNullable(getCohortWeekDates(qaCohortEntity.getStartDate()).stream()
                .map(weekStart -> getAverageRatingForCohortForWeek(qaCohortEntity, weekStart))
                .collect(Collectors.toList()))
                .map(l -> createCohortSummaryDto(qaCohortEntity, l))
                .orElseGet(() -> new CohortSummaryDto());
    }

    private List<Date> getCohortWeekDates(Date cohortStartDate) {
        return IntStream.range(1, NUM_COHORT_WEEKS)
                .mapToObj(index -> addNumWeeks(cohortStartDate, index - 1))
                .collect(Collectors.toList());
    }

    private Double getAverageRatingForCohortForWeek(QaCohortEntity qaCohortEntity, Date weekStartDate) {
        return qaCohortEntity.getTrainees().stream()
                .map(t -> this.reflectionRepository.findAllByResponder(t))
                .map(reflections -> getReflectionQuestions(reflections, weekStartDate))
                .map(rqs -> averageRatingForTrainee(rqs))
                .filter( rating -> rating.getAsDouble() > 0.0)
                .mapToDouble(x -> x.getAsDouble())
                .average().orElseGet(() -> new Double(0.0));
    }

    private Set<ReflectionQuestionEntity> getReflectionQuestions(Set<ReflectionEntity> reflectionEntities, Date weekStartDate) {
        return reflectionEntities.stream()
                .filter(r -> r.getFormDate().equals(weekStartDate))
                .flatMap(r -> r.getReflectionQuestions().stream())
                .collect(Collectors.toSet());
    }

    private CohortSummaryDto createEmptyCohortSummaryDto(QaCohortEntity qaCohortEntity) {
        return createCohortSummaryDto(qaCohortEntity, Collections.emptyList());
    }

    private CohortSummaryDto createCohortSummaryDto(QaCohortEntity qaCohortEntity, List<Double> cohortWeeklyAverages) {
        DecimalFormat df = new DecimalFormat("#.#");
        CohortSummaryDto cohortSummaryDto = new CohortSummaryDto();
        cohortSummaryDto.setCohortName(qaCohortEntity.getName());
        cohortSummaryDto.setAverageRatings(cohortWeeklyAverages.stream()
                .map(d -> new Double(df.format(d)))
                .collect(Collectors.toList()));
        return cohortSummaryDto;
    }

    private boolean anyReflectionsForWeek(Set<ReflectionEntity> reflections, Date weekStartDate) {
        return reflections.stream()
                .filter(r -> r.getFormDate().equals(weekStartDate))
                .findAny()
                .map(r -> true)
                .orElseGet(() -> false);
    }

    private OptionalDouble averageRatingForTrainee(Set<ReflectionQuestionEntity> reflectionQuestions) {
        LOGGER.info("Average Rating - Reflections are " + reflectionQuestions);
        if (reflectionQuestions.size() == 0) {
            return OptionalDouble.of(0.0);
        } else {
            return reflectionQuestions.stream()
                    .filter(q -> Optional.ofNullable(q.getTrainerResponse()).orElse(0) > 0)
                    .mapToInt(q -> q.getTrainerResponse())
                    .average();
        }
    }

    private Date addNumWeeks(Date baseDate, int numWeeks) {
        Calendar c = Calendar.getInstance();
        c.setTime(baseDate);
        c.add(Calendar.DATE, 7 * numWeeks);
        return new Date(c.getTimeInMillis());
    }
}
