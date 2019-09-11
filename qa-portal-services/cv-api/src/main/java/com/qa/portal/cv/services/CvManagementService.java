package com.qa.portal.cv.services;

import java.io.IOException;
import java.util.List;

import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.cv.domain.CvSearchCriteria;
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
   
    private SaveGeneratedCvOperation saveGeneratedCvOperation;

    private CvPdfGenerator cvPdfGenerator;

    private GetCvByIdOperation getCvByIdOperation;

    private CreateCvOperation createCvOperation;

    private UpdateCvVersionOperation updateCvVersionOperation;

    private GetCvVersionsOperation getCvVersionsOperation;

    private CvSearchOperation cvSearchOperation;

    public CvManagementService(SaveGeneratedCvOperation saveGeneratedCvOperation,
                               CvPdfGenerator cvPdfGenerator,
                               GetCvByIdOperation getCvByIdOperation,
                               CreateCvOperation createCvOperation,
                               UpdateCvVersionOperation updateCvVersionOperation,
                               GetCvVersionsOperation getCvVersionsOperation,
                               CvSearchOperation cvSearchOperation) {
        this.saveGeneratedCvOperation = saveGeneratedCvOperation;
        this.cvPdfGenerator = cvPdfGenerator;
        this.getCvByIdOperation = getCvByIdOperation;
        this.createCvOperation = createCvOperation;
        this.updateCvVersionOperation = updateCvVersionOperation;
        this.getCvVersionsOperation = getCvVersionsOperation;
        this.cvSearchOperation = cvSearchOperation;
    }

    
    // Create CV Operation
    public CvVersion createCv(CvVersion newCv, QaSecurityContext qaSecurityContext) {
    	return this.createCvOperation.createCv(newCv, qaSecurityContext);
    }
    
    // Update CV Operation
    public CvVersion updateCv(CvVersion updatedCv) {
    	return this.updateCvVersionOperation.updateCv(updatedCv);
    }

    
    //	Get CVs Operations
    public CvVersion findById(String id) {
        return getCvByIdOperation.findById(id);
    }

    public List<CvVersion> getAll() {
    	return this.getCvVersionsOperation.getAll();
    }
    
    public List<CvVersion> findByFullNameIgnoreCase(String fullName) {
    	return this.getCvVersionsOperation.findByFullNameIgnoreCase(fullName);
    }
    
    public List<CvVersion> findByUserNameIgnoreCase(String userName) {
    	return this.getCvVersionsOperation.findByUserNameIgnoreCase(userName);
    }

    public CvVersion getCurrentCvVersionForUser(String userName) {
        return this.getCvVersionsOperation.findByUserNameIgnoreCase(userName)
                .stream()
                .sorted((cv1, cv2) -> cv2.getVersionNumber() - cv1.getVersionNumber())
                .findFirst()
                .orElseGet(() -> null);
    }


    // Generated CV operations
    public void saveGeneratedCv(CvVersion cvVersion) throws IOException {
        saveGeneratedCvOperation.saveGeneratedCv(cvVersion);
    }

    public byte[] getGeneratedCv(CvVersion cvVersion) throws IOException {
        return cvPdfGenerator.generateCv(cvVersion);
    }


    // CV Search operations
    public List<CvVersion>  cvSearch(CvSearchCriteria criteria){
        return this.cvSearchOperation.findByCriteria(criteria);
    }
}
