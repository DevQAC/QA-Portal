package com.qa.portal.core.service.refdata;

import com.qa.portal.common.persistence.repository.CvStatusRepository;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.common.persistence.repository.TechnologyRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReferenceDataManager {

    private QaCohortRepository cohortRepo;

    private CvStatusRepository statusRepo;

    private TechnologyRepository techRepo;

    public ReferenceDataManager(QaCohortRepository cohortRepo, CvStatusRepository statusRepo, TechnologyRepository techRepo) {
        super();
        this.cohortRepo = cohortRepo;
        this.statusRepo = statusRepo;
        this.techRepo = techRepo;
    }

    public Map<String, List<String>> getReferenceDataForCategories() {
        Map<String, List<String>> map = new HashMap();

        List cohorts = this.cohortRepo.findAll().stream()
                .map(temp -> {
                    return temp.getName();
                }).collect(Collectors.toList());
        map.put("cohort", cohorts);


        List statuses = this.statusRepo.findAll().stream()
                .map(temp -> {
                    return temp.getStatusName();
                }).collect(Collectors.toList());
        map.put("cvStatus", statuses);


        List techs = this.techRepo.findAll().stream()
                .map(temp -> {
                    return temp.getTechnologyName();
                }).collect(Collectors.toList());
        map.put("technology", techs);

        return map;
    }
}


