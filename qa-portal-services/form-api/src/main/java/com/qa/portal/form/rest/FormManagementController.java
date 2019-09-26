package com.qa.portal.form.rest;

import com.qa.portal.common.dto.FormTypeDto;
import com.qa.portal.form.services.FormManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class FormManagementController {

    private FormManagementService formManagementService;

    public FormManagementController(FormManagementService formManagementService) {
        this.formManagementService = formManagementService;
    }

    @PostMapping("form")
    public ResponseEntity<FormTypeDto> createForm(@RequestBody FormTypeDto formTypeDto) {
        return ResponseEntity.ok(formManagementService.createForm(formTypeDto));
    }

    @PutMapping("form")
    public ResponseEntity<FormTypeDto> updateForm(@RequestBody FormTypeDto formTypeDto) {
        return ResponseEntity.ok(formManagementService.updateForm(formTypeDto));
    }
}
