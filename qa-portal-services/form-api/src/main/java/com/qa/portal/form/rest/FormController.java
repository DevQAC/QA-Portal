package com.qa.portal.form.rest;

import com.qa.portal.common.dto.FormTypeDto;
import com.qa.portal.form.services.FormService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FormController {

    private FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @GetMapping("/forms")
    public ResponseEntity<List<FormTypeDto>> getForms() {
        return ResponseEntity.ok(formService.getForms());
    }

    @GetMapping("/form/{id}")
    public ResponseEntity<FormTypeDto> getFormById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(formService.getFormById(id));
    }
}
