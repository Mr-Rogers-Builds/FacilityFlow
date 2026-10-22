package com.facilityflow.site;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SiteRepository extends JpaRepository<Site, Long> {

    List<Site> findByRegionId(Long regionId);

    List<Site> findByRegionIdAndActiveTrue(Long regionId);

    List<Site> findByRegionIdAndArchivedAtIsNull(Long regionId);

    List<Site> findByRegionIdAndActiveTrueAndArchivedAtIsNull(Long regionId);

}
