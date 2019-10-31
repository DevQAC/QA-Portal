package com.qa.portal.reflection.service;

import com.qa.portal.common.dto.QuestionDto;
import com.qa.portal.common.exception.QaResourceNotFoundException;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.reflection.dto.ReflectionQuestionDto;
import com.qa.portal.reflection.persistence.entity.ReflectionQuestionEntity;
import com.qa.portal.reflection.persistence.repository.ReflectionQuestionRepository;
import com.qa.portal.reflection.service.mapper.ReflectionQuestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReflectionQuestionService {
    private final Logger LOGGER = LoggerFactory.getLogger(ReflectionQuestionService.class);

    private ReflectionQuestionRepository reflectionQuestionRepo;

    private QaCohortRepository cohortRepository;

    private ReflectionQuestionMapper reflectionQuestionMapper;

    private QaSecurityContext context;

    private Comparator<ReflectionQuestionDto> reflectionQuestionComparator = Comparator.comparingInt(rq -> rq.getQuestion().getId());

    private Comparator<QuestionDto> questionComparator = Comparator.comparingInt(QuestionDto::getId);

    @Autowired
    public ReflectionQuestionService(ReflectionQuestionRepository reflectionQuestionRepo,
                                     QaCohortRepository cohortRepository,
                                     ReflectionQuestionMapper reflectionQuestionMapper,
                                     QaSecurityContext context) {
        this.reflectionQuestionRepo = reflectionQuestionRepo;
        this.cohortRepository = cohortRepository;
        this.reflectionQuestionMapper = reflectionQuestionMapper;
        this.context = context;
    }

    @Transactional
    public List<ReflectionQuestionDto> updateReflectionQuestions(Set<ReflectionQuestionDto> reflectionQuestions) {
        return reflectionQuestions.stream()
                .map(rqdto -> {
                    ReflectionQuestionEntity reflectionQuestionToUpdate = this.reflectionQuestionRepo.findById(rqdto.getId())
                            .orElseThrow(() -> new QaResourceNotFoundException("Reflection Question not found"));
                    ReflectionQuestionEntity reflectionQuestionToUpdateFrom = this.reflectionQuestionMapper.mapToReflectionQuestionEntity(rqdto);
                    reflectionQuestionToUpdate.setResponse(reflectionQuestionToUpdateFrom.getResponse());
                    reflectionQuestionToUpdate.setTrainerResponse(reflectionQuestionToUpdateFrom.getTrainerResponse());
                    reflectionQuestionToUpdate.setLastUpdatedBy(context.getUserName());
                    return this.reflectionQuestionMapper.mapToReflectionQuestionDto(this.reflectionQuestionRepo.save(reflectionQuestionToUpdate));
                })
                .sorted(reflectionQuestionComparator)
                .collect(Collectors.toList());
    }

	@Transactional
	public List<ReflectionQuestionDto> createReflectionQuestions(Set<ReflectionQuestionDto> reflectionQuestions) {
		return reflectionQuestions.stream().map(rqdto ->
					this.reflectionQuestionMapper
					.mapToReflectionQuestionDto(this.reflectionQuestionRepo
							.save(this.reflectionQuestionMapper.mapToReflectionQuestionEntity(rqdto)))
				)
                .sorted(reflectionQuestionComparator)
                .collect(Collectors.toList());
	}
}
