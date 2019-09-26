package com.qa.portal.application.service.refdata;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReferenceDataService {

    private ReferenceDataManager referenceDataManager;

    public ReferenceDataService(ReferenceDataManager referenceDataManager) {
        this.referenceDataManager = referenceDataManager;
    }


    public Map<String, List<String>> getReferenceDataForCategories(){
        return referenceDataManager.getReferenceDataForCategories();
    }

}
