package com.facilityflow.site.location;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationNodeRepository extends JpaRepository<LocationNode, Long> {

    List<LocationNode> findBySiteId(Long siteId);

    List<LocationNode> findByLocationTypeId(Long locationTypeId);

    List<LocationNode> findByParentId(Long parentId);

    List<LocationNode> findBySiteIdAndParentIsNull(Long siteId);

}
