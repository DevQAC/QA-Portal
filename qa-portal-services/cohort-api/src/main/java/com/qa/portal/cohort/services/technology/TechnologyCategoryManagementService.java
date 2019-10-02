package com.qa.portal.cohort.services.technology;

import com.qa.portal.cohort.services.technology.mapper.TechnologyCategoryMapper;
import com.qa.portal.common.dto.TechnologyCategoryDto;
import com.qa.portal.common.persistence.entity.TechnologyCategoryEntity;
import com.qa.portal.common.persistence.repository.TechnologyCategoryRepository;
import com.qa.portal.common.persistence.repository.TechnologyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TechnologyCategoryManagementService {

    private TechnologyCategoryRepository technologyCategoryRepository;

    private TechnologyRepository technologyRepository;

    private TechnologyCategoryMapper technologyCategoryMapper;

    public TechnologyCategoryManagementService(TechnologyCategoryRepository technologyCategoryRepository,
                                               TechnologyRepository technologyRepository,
                                               TechnologyCategoryMapper technologyCategoryMapper) {
        this.technologyCategoryRepository = technologyCategoryRepository;
        this.technologyRepository = technologyRepository;
        this.technologyCategoryMapper = technologyCategoryMapper;
    }

    @Transactional
    public TechnologyCategoryDto createTechnologyCategory(TechnologyCategoryDto technologyCategoryDto) {
        TechnologyCategoryEntity technologyCategoryEntity = technologyCategoryMapper.mapToNewTechnologyCategoryEntity(technologyCategoryDto);
        TechnologyCategoryEntity savedEntity = technologyCategoryRepository.save(technologyCategoryEntity);
        return technologyCategoryMapper.mapToTechnologyCategoryDto(savedEntity);
    }

    @Transactional
    public TechnologyCategoryDto updateTechnologyCategory(TechnologyCategoryDto technologyCategoryDto) {
        TechnologyCategoryEntity technologyCategoryEntity = technologyCategoryMapper.mapToUpdatedTechnologyCategoryEntity(technologyCategoryDto);
        TechnologyCategoryEntity savedEntity = technologyCategoryRepository.save(technologyCategoryEntity);
        return technologyCategoryMapper.mapToTechnologyCategoryDto(savedEntity);
    }
}
