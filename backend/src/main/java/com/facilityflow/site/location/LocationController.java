package com.facilityflow.site.location;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/sites/{siteId}/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/nodes")
    public List<LocationNodeResponse> getAllLocationNodesBySite(@PathVariable Long siteId) {
        return locationService.getLocationNodesBySite(siteId);
    }

    @GetMapping("/types")
    public List<LocationTypeResponse> getAllLocationTypesBySite(@PathVariable Long siteId) {
        return locationService.getLocationTypesBySite(siteId);
    }

    @GetMapping("/nodes/roots")
    public List<LocationNodeResponse> getRootLocationNodesBySite(@PathVariable Long siteId) {
        return locationService.getRootLocationNodesBySite(siteId);
    }

    @GetMapping("/nodes/{parentId}/children")
    public List<LocationNodeResponse> getLocationNodesByParent(@PathVariable Long siteId, @PathVariable Long parentId) {
        return locationService.getLocationNodesBySiteAndParent(siteId, parentId);
    }

    @PostMapping("/nodes")
    @ResponseStatus(HttpStatus.CREATED)
    public LocationNodeResponse createLocationNode(@PathVariable Long siteId,
            @Valid @RequestBody CreateLocationNodeRequest request) {
        return locationService.createLocationNode(siteId, request.getLocationTypeId(), request.getLocationNodeName(),
                request.getParentId());
    }

    @PostMapping("/types")
    @ResponseStatus(HttpStatus.CREATED)
    public LocationTypeResponse createLocationType(@PathVariable Long siteId,
            @Valid @RequestBody CreateLocationTypeRequest request) {
        return locationService.createLocationType(siteId, request.getLocationTypeName());
    }

}
