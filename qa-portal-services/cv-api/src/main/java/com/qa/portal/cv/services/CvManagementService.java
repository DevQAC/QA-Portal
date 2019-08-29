package com.qa.portal.cv.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.util.CvPdfGenerator;

@Service
@Transactional
public class CvManagementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CvManagementService.class);
   
    private SaveGeneratedCvOperation saveCvOperation;
    private CvPdfGenerator cvPdfGenerator;
    private CreateCvOperation createCvService;
    private UpdateCvVersionOperation updateCvService;
    private GetCurrentCvVersionOperation getCvService;
    
    public CvManagementService(SaveGeneratedCvOperation saveCvOperation, CvPdfGenerator cvPdfGenerator, 
    		CreateCvOperation createCvService, GetCurrentCvVersionOperation getCvService, 
    		UpdateCvVersionOperation updateCvService) {
    	
        this.saveCvOperation = saveCvOperation;
        this.cvPdfGenerator = cvPdfGenerator;
        this.createCvService = createCvService;
    	this.getCvService = getCvService;
    	this.updateCvService = updateCvService;
    }
    
    public void saveGeneratedCv(CvVersion cvVersion) {
    	saveCvOperation.saveGeneratedCv(cvVersion);
    }

    public byte[] getGeneratedCv(CvVersion cvVersion){
        return cvPdfGenerator.generateCv(cvVersion);
    }
  
    public CvVersion createCv(CvVersion newCv) {
    	return this.createCvService.createCv(newCv);
    }
    
    public CvVersion findByUserNameAndVersionNumberTest(String userName, int versionNumber) {
    	return this.createCvService.findByUserNameAndVersionNumberTest(userName, versionNumber);
    }
    
    public CvVersion updateCv(CvVersion updatedCv) {
    	return this.updateCvService.updateCv(updatedCv);
    }
    
    public List<CvVersion> getAll() {
    	return this.getCvService.getAll();
    }
    
    public List<CvVersion> findByFullNameIgnoreCase(String fullName) {
    	return this.getCvService.findByFullNameIgnoreCase(fullName);
    }
    
    public List<CvVersion> findByUserNameIgnoreCase(String userName) {
    	return this.getCvService.findByUserNameIgnoreCase(userName);
    }
    
    public CvVersion findByVersionNumber(Integer versionNumber) {
    	return this.getCvService.findByVersionNumber(versionNumber);
    }
} 
