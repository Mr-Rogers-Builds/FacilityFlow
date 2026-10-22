package com.facilityflow.workorder;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkOrderService {

    private final WorkOrderRepository workOrderRepository;
    private final WorkOrderEventRepository workOrderEventRepository;

    public WorkOrderService(WorkOrderRepository workOrderRepository,
            WorkOrderEventRepository workOrderEventRepository) {
        this.workOrderRepository = workOrderRepository;
        this.workOrderEventRepository = workOrderEventRepository;
    }

    public List<WorkOrder> getAllWorkOrders() {
        return workOrderRepository.findAll();
    }

    public List<WorkOrderEventResponse> getWorkOrderEvents(Long id) {
        getWorkOrderById(id);
        List<WorkOrderEvent> events = workOrderEventRepository.findByWorkOrderIdOrderByCreatedAtDesc(id);
        return events.stream().map(event -> {
            WorkOrderEventResponse response = new WorkOrderEventResponse();
            response.setId(event.getId());
            response.setEventType(event.getEventType());
            response.setPreviousValue(event.getPreviousValue());
            response.setNewValue(event.getNewValue());
            response.setWorkOrderId(event.getWorkOrder().getId());
            response.setCreatedAt(event.getCreatedAt());
            return response;
        }).toList();
    }

    @Transactional
    public WorkOrder createWorkOrder(CreateWorkOrderRequest request) {

        WorkOrder workOrder = new WorkOrder();

        workOrder.setTitle(request.getTitle());
        workOrder.setDescription(request.getDescription());
        workOrder.setPriority(request.getPriority());
        workOrder.setLocation(request.getLocation());
        workOrder.setStatus(WorkOrderStatus.NEW);

        WorkOrder savedWorkOrder = workOrderRepository.save(workOrder);
        recordWorkOrderEvent(savedWorkOrder, WorkOrderEventType.CREATED, null, null);

        return savedWorkOrder;
    }

    public WorkOrder getWorkOrderById(Long id) {
        return workOrderRepository.findById(id).orElseThrow(() -> new WorkOrderNotFoundException(id));
    }

    @Transactional
    public WorkOrder updateWorkOrder(Long id, UpdateWorkOrderRequest request) {
        WorkOrder workOrder = getWorkOrderById(id);

        if (request.getTitle() != null && !request.getTitle().equals(workOrder.getTitle())) {

            recordWorkOrderEvent(workOrder, WorkOrderEventType.TITLE_CHANGED, workOrder.getTitle(), request.getTitle());
            workOrder.setTitle(request.getTitle());
        }

        if (request.getDescription() != null && !request.getDescription().equals(workOrder.getDescription())) {

            recordWorkOrderEvent(workOrder, WorkOrderEventType.DESCRIPTION_CHANGED, workOrder.getDescription(),
                    request.getDescription());
            workOrder.setDescription(request.getDescription());
        }

        if (request.getStatus() != null && request.getStatus() != workOrder.getStatus()) {

            recordWorkOrderEvent(workOrder, WorkOrderEventType.STATUS_CHANGED, workOrder.getStatus().name(),
                    request.getStatus().name());
            workOrder.setStatus(request.getStatus());
        }

        if (request.getPriority() != null && request.getPriority() != workOrder.getPriority()) {

            recordWorkOrderEvent(workOrder, WorkOrderEventType.PRIORITY_CHANGED, workOrder.getPriority().name(),
                    request.getPriority().name());
            workOrder.setPriority(request.getPriority());
        }
        if (request.getLocation() != null && !request.getLocation().equals(workOrder.getLocation())) {
            recordWorkOrderEvent(workOrder, WorkOrderEventType.LOCATION_CHANGED, workOrder.getLocation(),
                    request.getLocation());
            workOrder.setLocation(request.getLocation());
        }

        // Return entity with updated values and save persistence
        return workOrderRepository.save(workOrder);

    }

    private void recordWorkOrderEvent(WorkOrder workOrder, WorkOrderEventType eventType, String previousValue,
            String newValue) {
        WorkOrderEvent workOrderEvent = new WorkOrderEvent();
        workOrderEvent.setPreviousValue(previousValue);
        workOrderEvent.setNewValue(newValue);
        workOrderEvent.setEventType(eventType);
        workOrderEvent.setWorkOrder(workOrder);
        workOrderEventRepository.save(workOrderEvent);
    }

}
