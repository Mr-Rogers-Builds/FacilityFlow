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

    public WorkOrder getWorkOrderById(Long id) {
        return workOrderRepository.findById(id).orElseThrow(() -> new WorkOrderNotFoundException(id));
    }

    public WorkOrder updateWorkOrder(Long id, UpdateWorkOrderRequest request) {
        WorkOrder workOrder = getWorkOrderById(id);

        if (request.getTitle() != null) {
            workOrder.setTitle(request.getTitle());
        }

        if (request.getDescription() != null) {
            workOrder.setDescription(request.getDescription());
        }

        if (request.getStatus() != null) {
            workOrder.setStatus(request.getStatus());
        }

        if (request.getPriority() != null) {
            workOrder.setPriority(request.getPriority());
        }
        if (request.getLocation() != null) {
            workOrder.setLocation(request.getLocation());
        }

        // Return entity with updated values and save persistence
        return workOrderRepository.save(workOrder);

    }

}
