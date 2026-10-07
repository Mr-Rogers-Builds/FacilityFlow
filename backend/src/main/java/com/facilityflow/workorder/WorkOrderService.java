package com.facilityflow.workorder;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WorkOrderService {

    private final WorkOrderRepository workOrderRepository;

    public WorkOrderService(WorkOrderRepository workOrderRepository) {
        this.workOrderRepository = workOrderRepository;
    }

    public List<WorkOrder> getAllWorkOrders() {
        return workOrderRepository.findAll();
    }

    public WorkOrder createWorkOrder(CreateWorkOrderRequest request) {

        WorkOrder workOrder = new WorkOrder();

        workOrder.setTitle(request.getTitle());
        workOrder.setDescription(request.getDescription());
        workOrder.setPriority(request.getPriority());
        workOrder.setLocation(request.getLocation());
        workOrder.setStatus(WorkOrderStatus.NEW);

        return workOrderRepository.save(workOrder);
    }

}
