package com.qa.portal.common.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @Value("${build.version}")
    private String projVersion;


    @GetMapping("/version")
    public ResponseEntity<String> getVersion() {
        return ResponseEntity.ok(projVersion);
    }
}
