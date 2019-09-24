package com.qa.portal.cohort.rest;

import com.qa.portal.cohort.services.location.LocationManagementService;
import com.qa.portal.common.dto.LocationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
public class LocationManagementController {

    private LocationManagementService locationManagementService;

    public LocationManagementController(LocationManagementService locationManagementService) {
        this.locationManagementService = locationManagementService;
    }

    @PostMapping("location")
    public ResponseEntity<LocationDto> createLocation(@RequestBody LocationDto locationDto) {
        return ResponseEntity.ok(locationManagementService.createLocation(locationDto));
    }

    @PutMapping("location")
    public ResponseEntity<LocationDto> updateLocation(@RequestBody LocationDto locationDto) {
        return ResponseEntity.ok(locationManagementService.updateLocation(locationDto));
    }
}
