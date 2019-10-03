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
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TechnologyCategoryMapper {

    private TechnologyRepository technologyRepository;

    private TechnologyCategoryRepository technologyCategoryRepository;

    private BaseMapper baseMapper;

    public TechnologyCategoryMapper(TechnologyRepository technologyRepository,
                                    TechnologyCategoryRepository technologyCategoryRepository,
                                    BaseMapper baseMapper) {
        this.technologyRepository = technologyRepository;
        this.technologyCategoryRepository = technologyCategoryRepository;
        this.baseMapper = baseMapper;
    }

    public TechnologyCategoryDto mapToTechnologyCategoryDto(TechnologyCategoryEntity technologyCategoryEntity) {
        TechnologyCategoryDto technologyCategoryDto = baseMapper.mapObject(technologyCategoryEntity, TechnologyCategoryDto.class);
        List<TechnologyDto> technologyDtos = technologyCategoryEntity.getTechnologies().stream()
                .map(entity -> baseMapper.mapObject(entity, TechnologyDto.class))
                .collect(Collectors.toList());
        technologyCategoryDto.setTechnologies(technologyDtos);
        return technologyCategoryDto;
    }

    public TechnologyCategoryEntity mapToNewTechnologyCategoryEntity(TechnologyCategoryDto technologyCategoryDto) {
        TechnologyCategoryEntity technologyCategoryEntity = baseMapper.mapObject(technologyCategoryDto, TechnologyCategoryEntity.class);
        List<TechnologyEntity> technologyEntities = technologyCategoryDto.getTechnologies().stream()
                .map(t -> getTechnologyEntity(t.getId()))
                .collect(Collectors.toList());
        technologyCategoryEntity.setTechnologies(technologyEntities);
        return technologyCategoryEntity;
    }

    public TechnologyCategoryEntity mapToUpdatedTechnologyCategoryEntity(TechnologyCategoryDto technologyCategoryDto) {
        TechnologyCategoryEntity technologyCategoryEntity =
                technologyCategoryRepository.findById(technologyCategoryDto.getId())
                        .orElseThrow(() -> new QaPortalBusinessException("No Technology Category for supplied id"));
        deleteRemovedTechnologies(technologyCategoryEntity, technologyCategoryDto);
        addNewTechnologies(technologyCategoryEntity, technologyCategoryDto);
        return technologyCategoryEntity;
    }

    private void deleteRemovedTechnologies(TechnologyCategoryEntity technologyCategoryEntity,
                                           TechnologyCategoryDto technologyCategoryDto) {
        List<String> newTechnologyNames = getNewTechnologyNames(technologyCategoryDto);
        technologyCategoryEntity.getTechnologies().stream()
                .filter(t -> !newTechnologyNames.contains(t.getTechnologyName()))
                .collect(Collectors.toList())
                .iterator()
                .forEachRemaining(t -> technologyCategoryEntity.removeTechnology(t));
    }

    private void addNewTechnologies(TechnologyCategoryEntity technologyCategoryEntity,
                                    TechnologyCategoryDto technologyCategoryDto) {
        List<String> oldTechnologyNames = getOldTechnologyNames(technologyCategoryEntity);
        technologyCategoryDto.getTechnologies().stream()
                .filter(t -> !oldTechnologyNames.contains(t.getTechnologyName()))
                .collect(Collectors.toList())
                .stream()
                .map(t -> getTechnology(technologyCategoryEntity, t))
                .forEach(entity -> technologyCategoryEntity.addTechnology(entity));
    }

    private TechnologyEntity getTechnology(TechnologyCategoryEntity technologyCategoryEntity, TechnologyDto technologyDto) {
        return Optional.ofNullable(technologyDto.getId())
                .map(id -> getTechnologyEntity(id))
                .orElseGet(() -> mapToNewTechnologyEntity(technologyDto, technologyCategoryEntity.getId()));
    }

    private TechnologyEntity mapToNewTechnologyEntity(TechnologyDto technologyDto, Integer technologyCategoryId) {
        TechnologyEntity technologyEntity = baseMapper.mapObject(technologyDto, TechnologyEntity.class);
        technologyCategoryRepository.findById(technologyCategoryId)
                .ifPresent(tc -> technologyEntity.setTechnologyCategory(tc));
        return technologyEntity;
    }

    private TechnologyEntity getTechnologyEntity(Integer id) {
        return technologyRepository.findById(id)
                .orElseThrow(() -> new QaPortalBusinessException("No Technology found for supplied id"));
    }

    private List<String> getOldTechnologyNames(TechnologyCategoryEntity oldTechnologyCategory) {
        return oldTechnologyCategory.getTechnologies().stream()
                .map(t -> t.getTechnologyName())
                .collect(Collectors.toList());
    }

    private List<String> getNewTechnologyNames(TechnologyCategoryDto technologyCategoryDto) {
        return technologyCategoryDto.getTechnologies().stream()
                .map(t -> t.getTechnologyName())
                .collect(Collectors.toList());
    }
}
