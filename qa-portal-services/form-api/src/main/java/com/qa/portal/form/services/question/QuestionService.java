package com.qa.portal.form.services.question;

import com.qa.portal.common.dto.QuestionDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.FormTypeEntity;
import com.qa.portal.common.persistence.repository.FormTypeRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private FormTypeRepository formTypeRepository;

    private BaseMapper baseMapper;

    private Comparator<QuestionDto> questionComparator = Comparator.comparingInt(q -> q.getId());


    public QuestionService(FormTypeRepository formTypeRepository,
                           BaseMapper baseMapper) {
        this.formTypeRepository = formTypeRepository;
        this.baseMapper = baseMapper;
    }

    @Transactional
    public List<QuestionDto> getQuestionsForFormType(String formName) {
        return formTypeRepository.findByFormName(formName)
                .map(f -> getQuestions(f))
                .orElseThrow(() -> new QaPortalBusinessException("Form not found for supplied form name"));
    }

    private List<QuestionDto> getQuestions(FormTypeEntity f) {
        return f.getQuestionCategories().stream()
                .flatMap(qc -> qc.getQuestions().stream())
                .map(q -> baseMapper.mapObject(q, QuestionDto.class))
                .sorted(questionComparator)
                .collect(Collectors.toList());
    }
}
