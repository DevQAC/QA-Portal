package com.qa.portal.cohort.services.technology;

import com.qa.portal.cohort.services.technology.mapper.TechnologyMapper;
import com.qa.portal.common.dto.TechnologyCategoryDto;
import com.qa.portal.common.dto.TechnologyDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.TechnologyCategoryEntity;
import com.qa.portal.common.persistence.entity.TechnologyEntity;
import com.qa.portal.common.persistence.repository.TechnologyCategoryRepository;
import com.qa.portal.common.persistence.repository.TechnologyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TechnologyManagementService {

    private TechnologyCategoryRepository technologyCategoryRepository;

    private TechnologyRepository technologyRepository;

    private TechnologyMapper technologyMapper;

    public TechnologyManagementService(TechnologyCategoryRepository technologyCategoryRepository,
                                       TechnologyRepository technologyRepository,
                                       TechnologyMapper technologyMapper) {
        this.technologyCategoryRepository = technologyCategoryRepository;
        this.technologyRepository = technologyRepository;
        this.technologyMapper = technologyMapper;
    }

    @Transactional
    public TechnologyCategoryDto createTechnologyCategory(TechnologyCategoryDto technologyCategoryDto) {
        TechnologyCategoryEntity technologyCategoryEntity = technologyMapper.mapToNewTechnologyCategoryEntity(technologyCategoryDto);
        TechnologyCategoryEntity savedEntity = technologyCategoryRepository.save(technologyCategoryEntity);
        return technologyMapper.mapToTechnologyCategoryDto(savedEntity);
    }

    @Transactional
    public TechnologyDto createTechnology(TechnologyDto technologyDto) {
        TechnologyEntity technologyEntity = technologyMapper.mapToNewTechnologyEntity(technologyDto);
        getTechnologyCategoryEntity(technologyDto.getTechnologyCategoryId())
                .ifPresent(tc -> technologyEntity.setTechnologyCategory(tc));
        TechnologyEntity savedEntity = technologyRepository.save(technologyEntity);
        return technologyMapper.mapToTechnologyDto(savedEntity);
    }

    @Transactional
    public TechnologyDto updateTechnology(TechnologyDto technologyDto) {
        TechnologyEntity technologyEntity = technologyRepository.findById(technologyDto.getId())
                .orElseThrow(() -> new QaPortalBusinessException("Cannot find Technology to be updated"));
        getTechnologyCategoryEntity(technologyDto.getTechnologyCategoryId())
                .ifPresent(tc -> technologyEntity.setTechnologyCategory(tc));
        TechnologyEntity savedEntity = technologyRepository.save(technologyEntity);
        return technologyMapper.mapToTechnologyDto(savedEntity);
    }

    private Optional<TechnologyCategoryEntity> getTechnologyCategoryEntity(Integer id) {
        return technologyCategoryRepository.findById(id);
    }
}
