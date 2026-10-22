package com.facilityflow.region;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {
    List<Region> findByOrganizationId(Long organizationId);

    List<Region> findByOrganizationIdAndActiveTrue(Long organizationId);

    List<Region> findByOrganizationIdAndArchivedAtIsNull(Long organizationId);

    List<Region> findByOrganizationIdAndActiveTrueAndArchivedAtIsNull(Long organizationId);

}
