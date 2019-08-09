package com.qa.portal.reflection.service.mapper;

import com.qa.portal.common.dto.QuestionDto;
import com.qa.portal.common.persistence.entity.QuestionEntity;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {

    private DozerBeanMapper mapper;

    public QuestionMapper(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }

    public QuestionDto mapToQuestionDto(QuestionEntity entity) {
        return mapper.map(entity, QuestionDto.class);
    }

    public QuestionEntity mapToQuestionEntity(QuestionDto dto) {
        return mapper.map(dto, QuestionEntity.class);
    }

}
