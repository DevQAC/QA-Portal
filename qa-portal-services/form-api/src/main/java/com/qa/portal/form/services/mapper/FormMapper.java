package com.qa.portal.form.services.mapper;

import com.qa.portal.common.dto.FormTypeDto;
import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.FormTypeEntity;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.persistence.repository.FormTypeRepository;
import com.qa.portal.common.persistence.repository.QuestionCategoryRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import com.qa.portal.form.services.category.mapper.QuestionCategoryMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FormMapper {

    private FormTypeRepository formTypeRepository;

    private QuestionCategoryRepository questionCategoryRepository;

    private QuestionCategoryMapper questionCategoryMapper;

    private BaseMapper baseMapper;

    public FormMapper(FormTypeRepository formTypeRepository,
                      QuestionCategoryRepository questionCategoryRepository,
                      QuestionCategoryMapper questionCategoryMapper,
                      BaseMapper baseMapper) {
        this.formTypeRepository = formTypeRepository;
        this.questionCategoryRepository = questionCategoryRepository;
        this.questionCategoryMapper = questionCategoryMapper;
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
        removeExistingCategoriesFromForm(formTypeEntity, formTypeDto);
        addNewCategoriesToForm(formTypeEntity, formTypeDto);
    }

    private void addNewCategoriesToForm(FormTypeEntity formTypeEntity, FormTypeDto formTypeDto) {
        getQuestionCategories(formTypeDto)
                .ifPresent(cats -> addNewCategoriesToForm(formTypeEntity, cats));
    }

    private void addNewCategoriesToForm(FormTypeEntity formTypeEntity, List<QuestionCategoryDto> questionCategoryDtos) {
        questionCategoryDtos.stream()
                .filter(qc -> !getExistingCategoryNames(formTypeEntity).contains(qc.getCategoryName()))
                .collect(Collectors.toList())
                .forEach(qc -> formTypeEntity.addQuestionCategory(questionCategoryMapper.mapToNewQuestionCategoryEntity(qc)));
    }

    private void removeExistingCategoriesFromForm(FormTypeEntity formTypeEntity, FormTypeDto formTypeDto) {
        formTypeEntity.getQuestionCategories().stream()
                .filter(qc -> !getNewCategoryNames(formTypeDto).contains(qc.getCategoryName()))
                .collect(Collectors.toList())
                .iterator()
                .forEachRemaining(qc -> formTypeEntity.removeQuestionCategory(qc));
    }

    private QuestionCategoryEntity getQuestionCategoryEntity(QuestionCategoryDto questionCategoryDto) {
        return questionCategoryRepository.findById(questionCategoryDto.getId())
                .orElseThrow(() -> new QaPortalBusinessException("Question Category not found for supplied id"));
    }

    private Optional<List<QuestionCategoryDto>> getQuestionCategories(FormTypeDto formTypeDto) {
        return Optional.ofNullable(formTypeDto.getQuestionCategories());
    }

    private List<String> getExistingCategoryNames(FormTypeEntity formTypeEntity) {
        return Optional.ofNullable(formTypeEntity.getQuestionCategories())
                .map(cats -> cats.stream()
                        .map(qc -> qc.getCategoryName())
                        .collect(Collectors.toList()))
                .orElseGet(() -> Collections.emptyList());
    }

    private List<String> getNewCategoryNames(FormTypeDto formTypeDto) {
        return Optional.ofNullable(formTypeDto)
                .map(form -> form.getQuestionCategories()
                        .stream()
                        .map(qc -> qc.getCategoryName())
                        .collect(Collectors.toList()))
                .orElseGet(() -> Collections.emptyList());
    }
}
