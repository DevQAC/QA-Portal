package com.qa.portal.cohort.services.technology;

import com.qa.portal.common.dto.TechnologyCategoryDto;
import com.qa.portal.common.dto.TechnologyDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.TechnologyCategoryEntity;
import com.qa.portal.common.persistence.entity.TechnologyEntity;
import com.qa.portal.common.persistence.repository.TechnologyCategoryRepository;
import com.qa.portal.common.persistence.repository.TechnologyRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TechnologyManagementService {

    private TechnologyCategoryRepository technologyCategoryRepository;

    private TechnologyRepository technologyRepository;

    private BaseMapper baseMapper;

    public TechnologyManagementService(TechnologyCategoryRepository technologyCategoryRepository,
                                       TechnologyRepository technologyRepository,
                                       BaseMapper baseMapper) {
        this.technologyCategoryRepository = technologyCategoryRepository;
        this.technologyRepository = technologyRepository;
        this.baseMapper = baseMapper;
    }

    @Transactional
    public TechnologyCategoryDto createTechnologyCategory(TechnologyCategoryDto technologyCategoryDto) {
        TechnologyCategoryEntity technologyCategoryEntity = baseMapper.mapObject(technologyCategoryDto, TechnologyCategoryEntity.class);
        TechnologyCategoryEntity savedEntity = technologyCategoryRepository.save(technologyCategoryEntity);
        return baseMapper.mapObject(savedEntity, TechnologyCategoryDto.class);
    }

    @Transactional
    public TechnologyDto createTechnology(TechnologyDto technologyDto) {
        TechnologyEntity technologyEntity = baseMapper.mapObject(technologyDto, TechnologyEntity.class);
        getTechnologyCategoryEntity(technologyDto.getTechnologyCategory())
                .ifPresent(tc -> technologyEntity.setTechnologyCategory(tc));
        TechnologyEntity savedEntity = technologyRepository.save(technologyEntity);
        return baseMapper.mapObject(savedEntity, TechnologyDto.class);
    }

    @Transactional
    public TechnologyDto updateTechnology(TechnologyDto technologyDto) {
        TechnologyEntity technologyEntity = technologyRepository.findById(technologyDto.getId())
                .orElseThrow(() -> new QaPortalBusinessException("Cannot find Technology to be updated"));
        getTechnologyCategoryEntity(technologyDto.getTechnologyCategory())
                .ifPresent(tc -> technologyEntity.setTechnologyCategory(tc));
        TechnologyEntity savedEntity = technologyRepository.save(technologyEntity);
        return baseMapper.mapObject(savedEntity, TechnologyDto.class);
    }

    private Optional<TechnologyCategoryEntity> getTechnologyCategoryEntity(TechnologyCategoryDto technologyCategoryDto) {
        return technologyCategoryRepository.findById(technologyCategoryDto.getId());
    }
}
