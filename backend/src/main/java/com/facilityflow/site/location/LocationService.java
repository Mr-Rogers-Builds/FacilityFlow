package com.facilityflow.site.location;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.facilityflow.site.Site;
import com.facilityflow.site.SiteRepository;

@Service
public class LocationService {

    private final LocationTypeRepository locationTypeRepository;

    private final LocationNodeRepository locationNodeRepository;

    private final SiteRepository siteRepository;

    public LocationService(LocationTypeRepository locationTypeRepository,
            LocationNodeRepository locationNodeRepository,
            SiteRepository siteRepository) {
        this.locationTypeRepository = locationTypeRepository;
        this.locationNodeRepository = locationNodeRepository;
        this.siteRepository = siteRepository;
    }

    @Transactional
    public LocationTypeResponse createLocationType(Long siteId, String locationTypeName) {

        if (locationTypeName == null || locationTypeName.isBlank()) {
            throw new IllegalArgumentException("Location type name cannot be null or empty");
        }

        LocationType locationType = new LocationType();
        locationType.setSite(
                siteRepository.findById(siteId).orElseThrow(() -> new IllegalArgumentException("Site not found")));
        locationType.setName(locationTypeName);
        return toLocationTypeResponse(locationTypeRepository.save(locationType));

    }

    @Transactional
    public LocationNodeResponse createLocationNode(Long siteId, Long locationTypeId, String locationNodeName,
            Long parentId) {

        if (locationNodeName == null || locationNodeName.isBlank()) {
            throw new IllegalArgumentException("Location node name cannot be null or empty");
        }

        Site site = siteRepository.findById(siteId).orElseThrow(() -> new IllegalArgumentException("Site not found"));

        LocationType locationType = locationTypeRepository.findById(locationTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Location type not found"));

        if (!locationType.getSite().getId().equals(site.getId())) {
            throw new IllegalArgumentException("Location type does not belong to the specified site");
        }

        LocationNode parent = null;

        if (parentId != null) {
            parent = locationNodeRepository.findById(parentId)
                    .orElseThrow(() -> new IllegalArgumentException("Parent location node not found"));
        }
        if (parent != null && !parent.getSite().getId().equals(site.getId())) {
            throw new IllegalArgumentException("Parent location node does not belong to the specified site");
        }

        LocationNode locationNode = new LocationNode();
        locationNode.setSite(site);
        locationNode.setLocationType(locationType);
        locationNode.setName(locationNodeName);
        locationNode.setParent(parent);
        return toLocationNodeResponse(locationNodeRepository.save(locationNode));

    }

    @Transactional(readOnly = true)
    public List<LocationNodeResponse> getLocationNodesBySite(Long siteId) {
        Site site = siteRepository.findById(siteId).orElseThrow(() -> new IllegalArgumentException("Site not found"));
        return locationNodeRepository.findBySiteId(site.getId())
                .stream()
                .map(this::toLocationNodeResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<LocationTypeResponse> getLocationTypesBySite(Long siteId) {
        Site site = siteRepository.findById(siteId).orElseThrow(() -> new IllegalArgumentException("Site not found"));
        return locationTypeRepository.findBySiteId(site.getId())
                .stream()
                .map(this::toLocationTypeResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<LocationNodeResponse> getLocationNodesByLocationType(Long locationTypeId) {
        LocationType locationType = locationTypeRepository.findById(locationTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Location type not found"));
        return locationNodeRepository.findByLocationTypeId(locationType.getId())
                .stream()
                .map(this::toLocationNodeResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<LocationNodeResponse> getLocationNodesBySiteAndLocationType(Long siteId, Long locationTypeId) {
        Site site = siteRepository.findById(siteId).orElseThrow(() -> new IllegalArgumentException("Site not found"));
        LocationType locationType = locationTypeRepository.findById(locationTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Location type not found"));
        if (!locationType.getSite().getId().equals(site.getId())) {
            throw new IllegalArgumentException("Location type does not belong to the specified site");
        }
        return locationNodeRepository.findBySiteIdAndLocationTypeId(site.getId(), locationType.getId())
                .stream()
                .map(this::toLocationNodeResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<LocationNodeResponse> getRootLocationNodesBySite(Long siteId) {

        Site site = siteRepository.findById(siteId).orElseThrow(() -> new IllegalArgumentException("Site not found"));

        return locationNodeRepository.findBySiteIdAndParentIsNull(site.getId())
                .stream()
                .map(this::toLocationNodeResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<LocationNodeResponse> getLocationNodesBySiteAndParent(Long siteId, Long parentId) {
        Site site = siteRepository.findById(siteId).orElseThrow(() -> new IllegalArgumentException("Site not found"));
        LocationNode parent = locationNodeRepository.findById(parentId)
                .orElseThrow(() -> new IllegalArgumentException("Parent location node not found"));
        if (!parent.getSite().getId().equals(site.getId())) {
            throw new IllegalArgumentException("Parent location node does not belong to the specified site");
        }
        return locationNodeRepository.findBySiteIdAndParentId(site.getId(), parent.getId())
                .stream()
                .map(this::toLocationNodeResponse)
                .toList();
    }

    private LocationNodeResponse toLocationNodeResponse(LocationNode locationNode) {
        LocationNodeResponse response = new LocationNodeResponse();
        response.setId(locationNode.getId());
        response.setLocationTypeId(locationNode.getLocationType().getId());
        if (locationNode.getParent() != null) {
            response.setParentId(locationNode.getParent().getId());
        }
        response.setName(locationNode.getName());

        return response;
    }

    private LocationTypeResponse toLocationTypeResponse(LocationType locationType) {
        LocationTypeResponse response = new LocationTypeResponse();
        response.setId(locationType.getId());
        response.setName(locationType.getName());
        return response;
    }
}