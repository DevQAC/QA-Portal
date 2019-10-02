package com.qa.portal.cohort.services.technology.mapper;

import com.qa.portal.common.dto.TechnologyCategoryDto;
import com.qa.portal.common.dto.TechnologyDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.TechnologyCategoryEntity;
import com.qa.portal.common.persistence.entity.TechnologyEntity;
import com.qa.portal.common.persistence.repository.TechnologyCategoryRepository;
import com.qa.portal.common.persistence.repository.TechnologyRepository;
import com.qa.portal.common.util.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TechnologyMapper {

    private TechnologyRepository technologyRepository;

    private TechnologyCategoryRepository technologyCategoryRepository;

    private BaseMapper baseMapper;

    public TechnologyMapper(TechnologyRepository technologyRepository,
                            TechnologyCategoryRepository technologyCategoryRepository,
                            BaseMapper baseMapper) {
        this.technologyRepository = technologyRepository;
        this.technologyCategoryRepository = technologyCategoryRepository;
        this.baseMapper = baseMapper;
    }

    public TechnologyCategoryDto mapToTechnologyCategoryDto(TechnologyCategoryEntity technologyCategoryEntity) {
        TechnologyCategoryDto technologyCategoryDto = baseMapper.mapObject(technologyCategoryEntity, TechnologyCategoryDto.class);
        List<TechnologyDto> technologyDtos = technologyCategoryEntity.getTechnologies().stream()
                .map(entity -> mapToTechnologyDto(entity))
                .collect(Collectors.toList());
        technologyCategoryDto.setTechnologies(technologyDtos);
        return technologyCategoryDto;
    }

    public TechnologyCategoryEntity mapToNewTechnologyCategoryEntity(TechnologyCategoryDto technologyCategoryDto) {
        TechnologyCategoryEntity technologyCategoryEntity = baseMapper.mapObject(technologyCategoryDto, TechnologyCategoryEntity.class);
        List<TechnologyEntity> technologyEntities = technologyCategoryDto.getTechnologies().stream()
                .map(t -> getTechnologyEntity(t))
                .collect(Collectors.toList());
        technologyCategoryEntity.setTechnologies(technologyEntities);
        return technologyCategoryEntity;
    }

    public TechnologyDto mapToTechnologyDto(TechnologyEntity technologyEntity) {
        TechnologyDto technologyDto = baseMapper.mapObject(technologyEntity, TechnologyDto.class);
        technologyDto.setTechnologyCategoryId(technologyEntity.getId());
        technologyDto.setTechnologyCategoryName(technologyEntity.getTechnologyCategory().getCategoryName());
        return technologyDto;
    }

    public TechnologyEntity mapToNewTechnologyEntity(TechnologyDto technologyDto) {
        TechnologyEntity technologyEntity = baseMapper.mapObject(technologyDto, TechnologyEntity.class);
        technologyCategoryRepository.findById(technologyDto.getTechnologyCategoryId())
                .ifPresent(tc -> technologyEntity.setTechnologyCategory(tc));
        return technologyEntity;
    }

    public TechnologyEntity mapToUpdatedTechnologyEntity(TechnologyDto technologyDto) {
        TechnologyEntity technologyEntity = technologyRepository.findById(technologyDto.getId())
                .orElseThrow(() -> new QaPortalBusinessException("No technology found or the supplied id"));
        technologyCategoryRepository.findById(technologyDto.getTechnologyCategoryId())
                .ifPresent(tc -> technologyEntity.setTechnologyCategory(tc));
        return technologyEntity;
    }

    private TechnologyEntity getTechnologyEntity(TechnologyDto technologyDto) {
        return technologyRepository.findById(technologyDto.getId())
                .orElseThrow(() -> new QaPortalBusinessException("No Technology found for supplied id"));
    }
}
