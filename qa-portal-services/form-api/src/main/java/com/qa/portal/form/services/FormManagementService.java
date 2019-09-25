package com.qa.portal.form.services;

import com.qa.portal.common.dto.FormTypeDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormManagementService {

    private CreateFormOperation createFormOperation;

    private UpdateFormOperation updateFormOperation;

    private RemoveCategoryFromFormOperation removeCategoryFromFormOperation;

    public FormManagementService(CreateFormOperation createFormOperation,
                                 UpdateFormOperation updateFormOperation,
                                 RemoveCategoryFromFormOperation removeCategoryFromFormOperation) {
        this.createFormOperation = createFormOperation;
        this.updateFormOperation = updateFormOperation;
        this.removeCategoryFromFormOperation = removeCategoryFromFormOperation;
    }


    @Transactional
    public FormTypeDto createForm(FormTypeDto formTypeDto) {
        return createFormOperation.createForm(formTypeDto);
    }

    @Transactional
    public FormTypeDto updateForm(FormTypeDto formTypeDto) {
        return updateFormOperation.updateForm(formTypeDto);
    }

    @Transactional
    public FormTypeDto removeCategoryFromForm(Integer questionCategoryId) {
        return removeCategoryFromFormOperation.removeQuestionCategoryFromForm(questionCategoryId);
    }
}
