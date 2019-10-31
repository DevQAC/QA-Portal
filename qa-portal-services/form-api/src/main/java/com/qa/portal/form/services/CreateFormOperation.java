package com.qa.portal.form.services;

import com.qa.portal.common.dto.FormTypeDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.FormTypeEntity;
import com.qa.portal.common.persistence.repository.FormTypeRepository;
import com.qa.portal.form.services.mapper.FormMapper;
import org.springframework.stereotype.Component;

@Component
public class CreateFormOperation {

    private FormTypeRepository formTypeRepository;

    private FormMapper formMapper;

    public CreateFormOperation(FormTypeRepository formTypeRepository,
                               FormMapper formMapper) {
        this.formTypeRepository = formTypeRepository;
        this.formMapper = formMapper;
    }

    public FormTypeDto createForm(FormTypeDto formTypeDto) {
        if (formExists(formTypeDto)) {
            throw new QaPortalBusinessException("Form already exists with supplied name");
        }

        FormTypeEntity formTypeEntity = formMapper.createNewFormTypeEntity(formTypeDto);
        FormTypeEntity savedForm = formTypeRepository.save(formTypeEntity);
        return formMapper.createFormDto(savedForm);
    }

    private boolean formExists(FormTypeDto formTypeDto) {
        return formTypeRepository.findByFormName(formTypeDto.getFormName())
                .map(f -> true)
                .orElseGet(() -> false);
    }
}
