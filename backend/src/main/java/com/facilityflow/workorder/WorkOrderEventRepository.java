package com.facilityflow.workorder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOrderEventRepository extends JpaRepository<WorkOrderEvent, Long> {

    List<WorkOrderEvent> findByWorkOrderIdOrderByCreatedAtDesc(Long workOrderId);

}
