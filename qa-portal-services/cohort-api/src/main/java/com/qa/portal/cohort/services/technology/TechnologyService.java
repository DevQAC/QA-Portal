package com.qa.portal.cohort.services.technology;

import com.qa.portal.cohort.services.technology.mapper.TechnologyMapper;
import com.qa.portal.common.dto.TechnologyCategoryDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.repository.TechnologyCategoryRepository;
import com.qa.portal.common.persistence.repository.TechnologyRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TechnologyService {

    private TechnologyCategoryRepository technologyCategoryRepository;

    private TechnologyRepository technologyRepository;

    private TechnologyMapper technologyMapper;

    public TechnologyService(TechnologyCategoryRepository technologyCategoryRepository,
                             TechnologyRepository technologyRepository,
                             TechnologyMapper technologyMapper) {
        this.technologyCategoryRepository = technologyCategoryRepository;
        this.technologyRepository = technologyRepository;
        this.technologyMapper = technologyMapper;
    }

    @Transactional
    public List<TechnologyCategoryDto> getTechnologyCategories() {
        return technologyCategoryRepository.findAll()
                .stream()
                .map(tc -> technologyMapper.mapToTechnologyCategoryDto(tc))
                .collect(Collectors.toList());
    }

    @Transactional
    public TechnologyCategoryDto getTechnologyCategory(Integer id) {
        return technologyCategoryRepository.findById(id)
                .map(tc -> technologyMapper.mapToTechnologyCategoryDto(tc))
                .orElseThrow(() -> new QaPortalBusinessException("No Technology Category found for the supplied id"));
    }
}
