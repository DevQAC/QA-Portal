package com.qa.portal.question.services.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.qa.portal.common.dto.QuestionCategoryDto;
import com.qa.portal.common.dto.QuestionDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.QuestionCategoryEntity;
import com.qa.portal.common.util.mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionCategoryMapper {

    private final Logger LOGGER = LoggerFactory.getLogger(QuestionCategoryMapper.class);

    private BaseMapper baseMapper;

    public QuestionCategoryMapper(BaseMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    public QuestionCategoryDto createQuestionCategoryDto(QuestionCategoryEntity questionCategoryEntity) {
        QuestionCategoryDto questionCategoryDto = baseMapper.mapObject(questionCategoryEntity, QuestionCategoryDto.class);
        setOptionLists(questionCategoryDto);
        return questionCategoryDto;
    }

    private void setOptionLists(QuestionCategoryDto questionCategoryDto) {
        questionCategoryDto.getQuestions().stream()
                .forEach(q -> setOptionsListForQuestion(q));
    }

    private void setOptionsListForQuestion(QuestionDto question) {
          try {
              ObjectMapper objectMapper = new ObjectMapper();
              TypeFactory typeFactory = objectMapper.getTypeFactory();
              question.setSelectionOptionsList(objectMapper.readValue(question.getSelectionOptionsJson(), typeFactory.constructCollectionType(List.class, String.class)));
          }
          catch (Exception e) {
              throw new QaPortalBusinessException("Could not get options for form questions.");
          }
    }
}
