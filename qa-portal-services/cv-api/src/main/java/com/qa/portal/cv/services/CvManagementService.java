package com.qa.portal.cv.services;

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
    
    public CvManagementService(CreateCvOperation createCvService) {
    	super();
    	this.createCvService = createCvService;
    }
    
    public String createCv(CvVersion newCv) {
    	return this.createCvService.createCv(newCv);
    }
    
}
