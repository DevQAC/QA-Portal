package com.qa.portal.application.service.refdata;

import com.qa.portal.common.persistence.refdata.QaReferenceData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReferenceDataService {

    private List<QaReferenceData> referenceDataTypes;

    private Map<String, List<String>> referenceData;

    public ReferenceDataService(List<QaReferenceData> referenceDataTypes) {
        this.referenceDataTypes = referenceDataTypes;
    }

    @PostConstruct
    public void init() {
        referenceData = referenceDataTypes.stream()
                            .collect(Collectors.toMap(refDataType -> refDataType.getRefDataName(),
                                                      refDataType -> refDataType.getRefDataItems()));
    }

    public Map<String, List<String>> getReferenceDataForCategories(){
        return referenceData;
    }
}
