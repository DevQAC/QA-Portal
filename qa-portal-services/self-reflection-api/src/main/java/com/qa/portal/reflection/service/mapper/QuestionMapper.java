package com.qa.portal.reflection.service.mapper;

import com.qa.portal.common.dto.QuestionDto;
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
}
