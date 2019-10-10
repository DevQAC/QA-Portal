package com.qa.portal.application.config;

import com.qa.portal.common.persistence.refdata.CohortReferenceData;
import com.qa.portal.common.persistence.refdata.CvStatusReferenceData;
import com.qa.portal.common.persistence.refdata.QaReferenceData;
import com.qa.portal.common.persistence.refdata.TechnologyReferenceData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class PortalApplicationConfig {

    private CohortReferenceData cohortReferenceData;

    private CvStatusReferenceData cvStatusReferenceData;

    private TechnologyReferenceData technologyReferenceData;

    public PortalApplicationConfig(CohortReferenceData cohortReferenceData,
                                   CvStatusReferenceData cvStatusReferenceData,
                                   TechnologyReferenceData technologyReferenceData) {
        this.cohortReferenceData = cohortReferenceData;
        this.cvStatusReferenceData = cvStatusReferenceData;
        this.technologyReferenceData = technologyReferenceData;
    }

    @Bean
    public List<QaReferenceData> referenceDataTypes() {
        List<QaReferenceData> refDataTypes = new ArrayList<>();
        refDataTypes.add(cohortReferenceData);
        refDataTypes.add(cvStatusReferenceData);
        refDataTypes.add(technologyReferenceData);
        return refDataTypes;
    }
}
