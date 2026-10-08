package com.facilityflow.workorder;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/work-orders")
public class WorkOrderController {

    private final WorkOrderService workOrderService;

    public WorkOrderController(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }

    @GetMapping
    public List<WorkOrder> getAllWorkOrders() {
        return workOrderService.getAllWorkOrders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorkOrder createWorkOrder(@Valid @RequestBody CreateWorkOrderRequest request) {
        return workOrderService.createWorkOrder(request);
    }

    @GetMapping("/{id}")
    public WorkOrder getWorkOrderById(@PathVariable Long id) {
        return workOrderService.getWorkOrderById(id);
    }

    @PatchMapping("/{id}")
    public WorkOrder updateWorkOrder(
            @PathVariable Long id,
            @Valid @RequestBody UpdateWorkOrderRequest request) {

        return workOrderService.updateWorkOrder(id, request);
    }

}