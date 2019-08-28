package com.qa.portal.question.services;

import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.common.dto.QuestionCategoryResponseDto;
import com.qa.portal.common.dto.QuestionDto;
import com.qa.portal.common.dto.QuestionResponseDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.persistence.repository.FormTypeRepository;
import com.qa.portal.question.services.mapper.QuestionCategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private FormTypeRepository formTypeRepository;

    private QuestionCategoryMapper questionCategoryMapper;

    private Comparator<QuestionDto> questionComparator = Comparator.comparingInt(q -> q.getId());


    public QuestionService(FormTypeRepository formTypeRepository,
                           QuestionCategoryMapper questionCategoryMapper) {
        this.formTypeRepository = formTypeRepository;
        this.questionCategoryMapper = questionCategoryMapper;
    }

    @Transactional
    public List<QuestionCategoryDto> getQuestionCategoriesForFormType(String formName) {
	    return formTypeRepository.findByFormName(formName)
                .map(f -> getQuestionCategoryDtos(f.getQuestionCategories()))
                .orElseThrow(() -> new QaPortalBusinessException("No Questions found for supplied form type " + formName));
    }

    @Transactional
    public List<QuestionDto> getQuestionsForFormType(@PathVariable("formName") String formName) {
        return getQuestionCategoriesForFormType(formName).stream()
                .flatMap(qc -> qc.getQuestions().stream())
                .sorted(questionComparator)
                .collect(Collectors.toList());
    }

    private List<QuestionCategoryDto> getQuestionCategoryDtos(List<QuestionCategoryEntity> questionCategoryEntities) {
        return questionCategoryEntities.stream()
                .map(e -> questionCategoryMapper.createQuestionCategoryDto(e))
                .collect(Collectors.toList());
    }
}
