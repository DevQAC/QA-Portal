package com.qa.portal.common.persistence.refdata;

import com.qa.portal.common.persistence.repository.CvStatusRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CvStatusReferenceData implements QaReferenceData {

    private CvStatusRepository statusRepo;

    public static final String REF_DATA_NAME = "cvStatus";

    public CvStatusReferenceData(CvStatusRepository statusRepo) {
        this.statusRepo = statusRepo;
    }

    @Override
    public List<String> getRefDataItems() {
        return statusRepo.findAll().stream()
                .map(temp -> temp.getStatusName())
                .collect(Collectors.toList());
    }

    @Override
    public String getRefDataName() {
        return REF_DATA_NAME;
    }
}
