package com.qa.portal.cv.services;

import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;
import org.springframework.stereotype.Component;

@Component
public class GetCvByIdOperation {

    private CvVersionRepository cvVersionRepository;

    public GetCvByIdOperation(CvVersionRepository cvVersionRepository) {
        this.cvVersionRepository = cvVersionRepository;
    }

    public CvVersion findById(String id) {
        return cvVersionRepository.findById(id)
                .orElseThrow(() -> new QaPortalBusinessException("No Cv found for supplied id"));
    }
}
