package com.qa.portal.common.persistence.refdata;

import com.qa.portal.common.persistence.repository.QaCohortRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CohortReferenceData implements QaReferenceData {

    private QaCohortRepository cohortRepository;

    public static final String REF_DATA_NAME = "cohort";

    public CohortReferenceData(QaCohortRepository cohortRepository) {
        this.cohortRepository = cohortRepository;
    }

    @Override
    public List<String> getRefDataItems() {
        return cohortRepository.findAll().stream()
                .map(c -> c.getName())
                .collect(Collectors.toList());
    }

    @Override
    public String getRefDataName() {
        return REF_DATA_NAME;
    }
}
