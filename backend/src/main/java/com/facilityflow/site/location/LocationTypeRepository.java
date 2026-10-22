package com.facilityflow.site.location;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LocationTypeRepository extends JpaRepository<LocationType, Long> {
    List<LocationType> findBySiteId(Long siteId);

    List<LocationType> findBySiteIdAndActiveTrue(Long siteId);

    List<LocationType> findBySiteIdAndArchivedAtIsNull(Long siteId);

    List<LocationType> findBySiteIdAndActiveTrueAndArchivedAtIsNull(Long siteId);

}
