package com.qa.portal.cohort.services.location;

import com.qa.portal.common.dto.LocationDto;
import com.qa.portal.common.exception.QaPortalBusinessException;
import com.qa.portal.common.persistence.entity.LocationEntity;
import com.qa.portal.common.persistence.repository.LocationRepository;
import com.qa.portal.common.service.mapper.BaseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LocationManagementService {

    private LocationRepository locationRepository;

    private BaseMapper baseMapper;

    public LocationManagementService(LocationRepository locationRepository,
                           BaseMapper baseMapper) {
        this.locationRepository = locationRepository;
        this.baseMapper = baseMapper;
    }

    @Transactional
    public LocationDto createLocation(LocationDto locationDto) {
        LocationEntity locationEntity = baseMapper.mapObject(locationDto, LocationEntity.class);
        LocationEntity savedLocation = locationRepository.save(locationEntity);
        return baseMapper.mapObject(savedLocation, LocationDto.class);
    }

    @Transactional
    public LocationDto updateLocation(LocationDto locationDto) {
        Optional<LocationEntity> locationEntity = locationRepository.findById(locationDto.getId());
        return locationEntity.map(l -> updateLocationAddress(l, locationDto))
                .orElseThrow(() -> new QaPortalBusinessException("Location not found"));
    }

    private LocationDto updateLocationAddress(LocationEntity locationEntity, LocationDto locationDto) {
        locationEntity.setAddress(locationDto.getAddress());
        LocationEntity savedLocation = locationRepository.save(locationEntity);
        return baseMapper.mapObject(savedLocation, LocationDto.class);
    }
}
