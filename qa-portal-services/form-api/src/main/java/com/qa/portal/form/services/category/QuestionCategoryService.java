package com.qa.portal.form.services.category;

import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.common.dto.QuestionDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.persistence.repository.FormTypeRepository;
import com.qa.portal.form.services.category.mapper.QuestionCategoryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionCategoryService {

    private FormTypeRepository formTypeRepository;

    private QuestionCategoryMapper questionCategoryMapper;

    private Comparator<QuestionDto> questionComparator = Comparator.comparingInt(q -> q.getId());

    public QuestionCategoryService(FormTypeRepository formTypeRepository,
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

    private List<QuestionCategoryDto> getQuestionCategoryDtos(List<QuestionCategoryEntity> questionCategoryEntities) {
        return questionCategoryEntities.stream()
                .map(e -> questionCategoryMapper.createQuestionCategoryDto(e))
                .collect(Collectors.toList());
    }
}
