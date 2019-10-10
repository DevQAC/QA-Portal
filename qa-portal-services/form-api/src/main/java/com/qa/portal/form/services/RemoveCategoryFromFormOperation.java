package com.qa.portal.form.services;

import com.qa.portal.common.dto.FormTypeDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.FormTypeEntity;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.persistence.repository.QuestionCategoryRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RemoveCategoryFromFormOperation {

    private QuestionCategoryRepository questionCategoryRepository;

    private BaseMapper baseMapper;

    public RemoveCategoryFromFormOperation(QuestionCategoryRepository questionCategoryRepository,
                                           BaseMapper baseMapper) {
        this.questionCategoryRepository = questionCategoryRepository;
        this.baseMapper = baseMapper;
    }

    public FormTypeDto removeQuestionCategoryFromForm(Integer questionCategoryId) {
        return getQuestionCategory(questionCategoryId)
                .map(qc -> removeQuestionCategoryFromForm(qc))
                .orElseThrow(() -> new QaPortalBusinessException("Question Category not found for supplied id"));
    }

    private FormTypeDto removeQuestionCategoryFromForm(QuestionCategoryEntity questionCategoryEntity) {
        FormTypeEntity formTypeEntity = questionCategoryEntity.getFormType();
        formTypeEntity.removeQuestionCategory(questionCategoryEntity);
        return baseMapper.mapObject(formTypeEntity, FormTypeDto.class);
    }

    private Optional<QuestionCategoryEntity> getQuestionCategory(Integer questionCategoryId) {
        return questionCategoryRepository.findById(questionCategoryId);
    }
}
