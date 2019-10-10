package com.qa.portal.common.persistence.refdata;

import com.qa.portal.common.persistence.repository.TechnologyRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TechnologyReferenceData implements QaReferenceData {

    private TechnologyRepository technologyRepository;

    public static final String REF_DATA_NAME = "technology";

    public TechnologyReferenceData(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public List<String> getRefDataItems() {
        return technologyRepository.findAll().stream()
                .map(t -> t.getTechnologyName())
                .collect(Collectors.toList());
    }

    @Override
    public String getRefDataName() {
        return REF_DATA_NAME;
    }
}
