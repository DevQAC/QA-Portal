package com.qa.portal.cv.services;

import java.io.IOException;
import java.util.List;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.util.CvPdfGenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CvManagementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CvManagementService.class);
   
    private SaveGeneratedCvOperation saveCvOperation;
    private CvPdfGenerator cvPdfGenerator;
    private CreateCvOperation createCvService;
    private GetCurrentCvVersionOperation getCvService;
    
    public CvManagementService(SaveGeneratedCvOperation saveCvOperation, CvPdfGenerator cvPdfGenerator, CreateCvOperation createCvService, GetCurrentCvVersionOperation getCvService) {
        this.saveCvOperation = saveCvOperation;
        this.cvPdfGenerator = cvPdfGenerator;
        this.createCvService = createCvService;
    	this.getCvService = getCvService;
    }

    public void saveGeneratedCv(CvVersion cvVersion) throws IOException {
    	saveCvOperation.saveGeneratedCv(cvVersion);
    }

    public byte[] getGeneratedCv(CvVersion cvVersion) throws IOException {
        return cvPdfGenerator.generateCv(cvVersion);
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
