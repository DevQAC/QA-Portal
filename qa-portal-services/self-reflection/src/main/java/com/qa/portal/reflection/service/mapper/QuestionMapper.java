package com.qa.portal.reflection.service.mapper;

import com.qa.portal.common.dto.QuestionDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.persistence.entity.QuestionEntity;
import com.qa.portal.common.persistence.repository.QuestionCategoryRepository;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

    private DozerBeanMapper mapper;

    private QuestionCategoryRepository questionCategoryRepository;

    public QuestionMapper(DozerBeanMapper mapper, QuestionCategoryRepository questionCategoryRepository) {
        this.mapper = mapper;
        this.questionCategoryRepository = questionCategoryRepository;
    }

    public QuestionDto mapToQuestionDto(QuestionEntity entity) {
        QuestionDto questionDto = mapper.map(entity, QuestionDto.class);
        questionDto.setQuestionCategoryName(entity.getCategory().getCategoryName());
        return questionDto;
    }

    public QuestionEntity mapToQuestionEntity(QuestionDto dto) {
        QuestionEntity questionEntity = mapper.map(dto, QuestionEntity.class);
        QuestionCategoryEntity questionCategoryEntity = questionCategoryRepository.findByCategoryName(dto.getQuestionCategoryName())
                .orElseThrow(() -> new QaPortalBusinessException("No question category for " + dto.getQuestionCategoryName()));
        questionEntity.setCategory(questionCategoryEntity);
        return questionEntity;
    }

}
