package com.qa.portal.form.services.mapper;

import com.qa.portal.common.dto.FormTypeDto;
import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.FormTypeEntity;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.persistence.repository.FormTypeRepository;
import com.qa.portal.common.persistence.repository.QuestionCategoryRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class FormMapper {

    private FormTypeRepository formTypeRepository;

    private QuestionCategoryRepository questionCategoryRepository;

    private BaseMapper baseMapper;

    public FormMapper(FormTypeRepository formTypeRepository,
                      QuestionCategoryRepository questionCategoryRepository,
                      BaseMapper baseMapper) {
        this.formTypeRepository = formTypeRepository;
        this.questionCategoryRepository = questionCategoryRepository;
        this.baseMapper = baseMapper;
    }

    public FormTypeDto createFormDto(FormTypeEntity formTypeEntity) {
        return baseMapper.mapObject(formTypeEntity, FormTypeDto.class);
    }

    public FormTypeEntity mapToNewFormTypeEntity(FormTypeDto formTypeDto) {
        FormTypeEntity formTypeEntity = baseMapper.mapObject(formTypeDto, FormTypeEntity.class);
        addNewCategoriesToForm(formTypeEntity, formTypeDto);
        return formTypeEntity;
    }

    public void updatedFormTypeEntity(FormTypeEntity formTypeEntity, FormTypeDto formTypeDto) {
        formTypeEntity.setDescription(formTypeDto.getDescription());
        removeExistingCategoriesFromForm(formTypeEntity);
        addNewCategoriesToForm(formTypeEntity, formTypeDto);
    }

    private void addNewCategoriesToForm(FormTypeEntity formTypeEntity, FormTypeDto formTypeDto) {
        formTypeDto.getQuestionCategories().stream()
                .forEach(qc -> formTypeEntity.addQuestionCategory(getQuestionCategoryEntity(qc)));
    }

    private void removeExistingCategoriesFromForm(FormTypeEntity formTypeEntity) {
        formTypeEntity.getQuestionCategories().iterator()
                .forEachRemaining(qc -> formTypeEntity.removeQuestionCategory(qc));
    }

    private QuestionCategoryEntity getQuestionCategoryEntity(QuestionCategoryDto questionCategoryDto) {
        return questionCategoryRepository.findById(questionCategoryDto.getId())
                .orElseThrow(() -> new QaPortalBusinessException("Question Category not found for supplied id"));
    }
}
