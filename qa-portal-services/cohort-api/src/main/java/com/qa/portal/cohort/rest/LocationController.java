package com.qa.portal.cohort.rest;

import com.qa.portal.cohort.services.location.LocationService;
import com.qa.portal.common.dto.LocationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationController {

    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("locations")
    public ResponseEntity<List<LocationDto>> getLocations() {
        return ResponseEntity.ok(locationService.getLocations());
    }

    @GetMapping("location/{id}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(locationService.getLocationById(id));
    }
}
