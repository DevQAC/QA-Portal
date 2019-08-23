package com.qa.portal.cv.services;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.util.CvPdfGenerator;

@Service
public class CvManagementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CvManagementService.class);
   
    private SaveGeneratedCvOperation saveCvOperation;
    private CvPdfGenerator cvPdfGenerator;
    
    public CvManagementService(SaveGeneratedCvOperation saveCvOperation, CvPdfGenerator cvPdfGenerator) {
        this.saveCvOperation = saveCvOperation;
        this.cvPdfGenerator = cvPdfGenerator;
    }
    
    @Transactional(value = TxType.REQUIRED)
    public void saveGeneratedCv(CvVersion cvVersion) {
    	saveCvOperation.saveGeneratedCv(cvVersion);
    }

    public byte[] getGeneratedCv(CvVersion cvVersion){
        return cvPdfGenerator.generateCv(cvVersion);
    }
}
