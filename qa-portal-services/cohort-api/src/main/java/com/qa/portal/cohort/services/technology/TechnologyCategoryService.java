package com.qa.portal.cohort.services.technology;

import com.qa.portal.cohort.services.technology.mapper.TechnologyCategoryMapper;
import com.qa.portal.common.dto.TechnologyCategoryDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.repository.TechnologyCategoryRepository;
import com.qa.portal.common.persistence.repository.TechnologyRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TechnologyCategoryService {

    private TechnologyCategoryRepository technologyCategoryRepository;

    private TechnologyRepository technologyRepository;

    private TechnologyCategoryMapper technologyCategoryMapper;

    public TechnologyCategoryService(TechnologyCategoryRepository technologyCategoryRepository,
                                     TechnologyRepository technologyRepository,
                                     TechnologyCategoryMapper technologyCategoryMapper) {
        this.technologyCategoryRepository = technologyCategoryRepository;
        this.technologyRepository = technologyRepository;
        this.technologyCategoryMapper = technologyCategoryMapper;
    }

    @Transactional
    public List<TechnologyCategoryDto> getTechnologyCategories() {
        return technologyCategoryRepository.findAll()
                .stream()
                .map(tc -> technologyCategoryMapper.mapToTechnologyCategoryDto(tc))
                .collect(Collectors.toList());
    }

    @Transactional
    public TechnologyCategoryDto getTechnologyCategory(Integer id) {
        return technologyCategoryRepository.findById(id)
                .map(tc -> technologyCategoryMapper.mapToTechnologyCategoryDto(tc))
                .orElseThrow(() -> new QaPortalBusinessException("No Technology Category found for the supplied id"));
    }
}
