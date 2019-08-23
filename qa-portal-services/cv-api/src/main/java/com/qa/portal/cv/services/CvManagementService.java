package com.qa.portal.cv.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qa.portal.cv.domain.CvVersion;

@Service
@Transactional
public class CvManagementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CvManagementService.class);
    
    private CreateCvOperation createCvService;
    private GetCurrentCvVersionOperation getCvService;
    
    public CvManagementService(CreateCvOperation createCvService, GetCurrentCvVersionOperation getCvService) {
    	super();
    	this.createCvService = createCvService;
    	this.getCvService = getCvService;
    }
    
    public String createCv(CvVersion newCv) {
    	return this.createCvService.createCv(newCv);
    }
    
    public List<CvVersion> getAll() {
    	return this.getCvService.getAll();
    }
    
    public Integer findByVersionNumber(Integer versionNumber) {
    	return this.getCvService.findByVersionNumber(versionNumber);
    }
    
}
