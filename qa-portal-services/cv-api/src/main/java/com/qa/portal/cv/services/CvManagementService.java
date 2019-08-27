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
    
//    Nick I commented this out to get rid of the error.
//    @Transactional(value = TxType.REQUIRED)
    public void saveGeneratedCv(CvVersion cvVersion) {
    	saveCvOperation.saveGeneratedCv(cvVersion);
    }

    public byte[] getGeneratedCv(CvVersion cvVersion){
        return cvPdfGenerator.generateCv(cvVersion);
    }
  
    public String createCv(CvVersion newCv) {
    	return this.createCvService.createCv(newCv);
    }
    
    public String updateCv(CvVersion updatedCv) {
    	return this.updateCvService.updateCv(updatedCv);
    }
    
    public List<CvVersion> getAll() {
    	return this.getCvService.getAll();
    }
    
//    public CvVersion getCurrent(Integer versionNumber) {
//    	return this.getCvService.getCurrent(versionNumber);
//    }
}
