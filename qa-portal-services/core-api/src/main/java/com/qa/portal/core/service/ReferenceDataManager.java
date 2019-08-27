package com.qa.portal.core.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import static java.util.stream.Collectors.toList;

import com.qa.portal.common.util.mapper.BaseMapper;
import com.qa.portal.common.dto.*;
import com.qa.portal.common.persistence.entity.*;
import com.qa.portal.common.persistence.repository.*;
import com.qa.portal.common.persistence.entity.QaCohortEntity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

public class ReferenceDataManager {

    private QaCohortRepository cohortRepository;


   private  Map<String, List<String>> getReferenceDataForCategories(List<String> refDataCategories) {
        List cohorts = this.cohortRepository.findAll().stream()
                .map(d -> new String(d.getName()))
                .collect(Collectors.toList());

        Map<String, List<String>> map = new HashMap();
        map.put("cohorts", cohorts);

        return map;
    }
}
