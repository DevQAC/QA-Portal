package com.qa.portal.cohort.services.location;

import com.qa.portal.common.dto.LocationDto;
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
}
