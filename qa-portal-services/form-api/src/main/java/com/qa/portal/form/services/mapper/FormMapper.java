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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class FormMapper {

    private final Logger LOGGER = LoggerFactory.getLogger(FormMapper.class);

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

    public FormTypeEntity createNewFormTypeEntity(FormTypeDto formTypeDto) {
        FormTypeEntity formTypeEntity = baseMapper.mapObject(formTypeDto, FormTypeEntity.class);
        addNewCategoriesToForm(formTypeEntity, formTypeDto);
        return formTypeEntity;
    }

    public void updateExistingFormTypeEntity(FormTypeEntity formTypeEntity, FormTypeDto formTypeDto) {
        formTypeEntity.setDescription(formTypeDto.getDescription());
        updateExistingCategories(formTypeEntity, formTypeDto);
        removeDeletedCategoriesFromForm(formTypeEntity, formTypeDto);
        addNewCategoriesToForm(formTypeEntity, formTypeDto);
    }

    private void updateExistingCategories(FormTypeEntity formTypeEntity, FormTypeDto formTypeDto) {
        formTypeEntity.getQuestionCategories().stream()
                .filter(qc -> getNewCategoryIds(formTypeDto).contains(qc.getId()))
                .forEach(qc -> questionCategoryMapper.updateQuestionCategoryEntity(qc, getQuestionCategoryDto(formTypeDto, qc)));
    }

    private void removeDeletedCategoriesFromForm(FormTypeEntity formTypeEntity, FormTypeDto formTypeDto) {
        formTypeEntity.getQuestionCategories().stream()
                .filter(qc -> !getNewCategoryIds(formTypeDto).contains(qc.getId()))
                .collect(Collectors.toList())
                .iterator()
                .forEachRemaining(qc -> formTypeEntity.removeQuestionCategory(qc));
    }

    private void addNewCategoriesToForm(FormTypeEntity formTypeEntity, FormTypeDto formTypeDto) {
        getQuestionCategories(formTypeDto)
                .map(cats -> getNewCategoriesForForm(cats))
                .orElseGet(() -> Collections.emptyList())
                .forEach(qc -> formTypeEntity.addQuestionCategory(qc));
    }

    private List<QuestionCategoryEntity> getNewCategoriesForForm(List<QuestionCategoryDto> questionCategoryDtos) {
        return questionCategoryDtos.stream()
                .filter(qc -> qc.getId() == null)
                .map(qc -> questionCategoryMapper.mapToNewQuestionCategoryEntity(qc))
                .collect(Collectors.toList());
    }

    private QuestionCategoryDto getQuestionCategoryDto(FormTypeDto formTypeDto, QuestionCategoryEntity questionCategoryEntity) {
        return formTypeDto.getQuestionCategories().stream()
                .filter(qc -> qc.getId().equals(questionCategoryEntity.getId()))
                .findFirst()
                .orElseThrow(() -> new QaPortalBusinessException("No Question Category found for the supplied id"));
    }

    private Optional<List<QuestionCategoryDto>> getQuestionCategories(FormTypeDto formTypeDto) {
        return Optional.ofNullable(formTypeDto.getQuestionCategories());
    }

    private List<Integer> getNewCategoryIds(FormTypeDto formTypeDto) {
        return formTypeDto.getQuestionCategories().stream()
                .filter(qc -> qc.getId() != null)
                .map(qc -> qc.getId())
                .collect(Collectors.toList());
    }
}
