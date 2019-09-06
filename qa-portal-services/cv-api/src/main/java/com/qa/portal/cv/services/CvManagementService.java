package com.qa.portal.cv.services;

import java.io.IOException;
import java.util.List;

import com.qa.portal.cv.domain.CvSearchCriteria;
import com.qa.portal.cv.domain.UserDetails;
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

    private CvSearchOperation cvSearchOperation;

    public CvManagementService(SaveGeneratedCvOperation saveCvOperation, CvPdfGenerator cvPdfGenerator,
                               CreateCvOperation createCvService, GetCurrentCvVersionOperation getCvService,
                               UpdateCvVersionOperation updateCvService, CvSearchOperation cvSearchOperation) {
    	
        this.saveCvOperation = saveCvOperation;
        this.cvPdfGenerator = cvPdfGenerator;
        this.createCvService = createCvService;
    	this.getCvService = getCvService;
    	this.updateCvService = updateCvService;
        this.cvSearchOperation = cvSearchOperation;
    }
    
    public void saveGeneratedCv(CvVersion cvVersion) throws IOException {
    	saveCvOperation.saveGeneratedCv(cvVersion);
    }

    public byte[] getGeneratedCv(CvVersion cvVersion) throws IOException {
        return cvPdfGenerator.generateCv(cvVersion);
    }
    
//	Create Service
    public CvVersion createCv(CvVersion newCv, UserDetails user) {
    	return this.createCvService.createCv(newCv, user);
    }
    
//	Update Service
    public CvVersion updateCv(CvVersion updatedCv) {
    	return this.updateCvService.updateCv(updatedCv);
    }
    
    public CvVersion submitCv(CvVersion submittedCv) {
    	return this.updateCvService.submitCv(submittedCv);
    }
    
    public CvVersion approveCv(CvVersion submittedCv) {
    	return this.updateCvService.approveCv(submittedCv);
    }
    
    public CvVersion failCv(CvVersion submittedCv) {
    	return this.updateCvService.failCv(submittedCv);
    }
    
//	Get Service
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

    public List<CvVersion>  cvSearch(CvSearchCriteria criteria){
        return this.cvSearchOperation.findByCriteria(criteria);
    }
}
