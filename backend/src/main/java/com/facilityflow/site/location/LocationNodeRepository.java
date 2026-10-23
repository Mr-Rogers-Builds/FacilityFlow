package com.facilityflow.site.location;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationNodeRepository extends JpaRepository<LocationNode, Long> {

    List<LocationNode> findBySiteId(Long siteId);

    List<LocationNode> findByLocationTypeId(Long locationTypeId);

    List<LocationNode> findBySiteIdAndParentId(Long siteId, Long parentId);

    List<LocationNode> findBySiteIdAndParentIsNull(Long siteId);

    List<LocationNode> findBySiteIdAndLocationTypeId(Long siteId, Long locationTypeId);

    Optional<LocationNode> findBySiteIdAndId(Long siteId, Long id);

}
