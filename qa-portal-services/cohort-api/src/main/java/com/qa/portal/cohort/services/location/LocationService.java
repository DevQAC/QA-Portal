package com.qa.portal.cohort.services.location;

import com.qa.portal.common.dto.LocationDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.repository.LocationRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private LocationRepository locationRepository;

    private BaseMapper baseMapper;

    public LocationService(LocationRepository locationRepository,
                           BaseMapper baseMapper) {
        this.locationRepository = locationRepository;
        this.baseMapper = baseMapper;
    }

    @Transactional
    public List<LocationDto> getLocations() {
        return locationRepository.findAll().stream()
                .map(l -> baseMapper.mapObject(l, LocationDto.class))
                .collect(Collectors.toList());
    }

    public LocationDto getLocationById(Integer id) {
        return locationRepository.findById(id)
                .map(l -> baseMapper.mapObject(l, LocationDto.class))
                .orElseThrow(() -> new QaPortalBusinessException("No Location found for the supplied id"));
    }
}
