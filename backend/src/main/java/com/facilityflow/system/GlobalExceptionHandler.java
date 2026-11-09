package com.facilityflow.system;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.facilityflow.workorder.WorkOrderNotFoundException;
import com.facilityflow.organization.OrganizationNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(WorkOrderNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ApiError handleWorkOrderNotFound(WorkOrderNotFoundException exception) {
                return new ApiError(
                                HttpStatus.NOT_FOUND.value(),
                                HttpStatus.NOT_FOUND.getReasonPhrase(),
                                exception.getMessage());
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ValidationErrorResponse handleValidationErrors(
                        MethodArgumentNotValidException exception) {

                Map<String, String> fieldErrors = new LinkedHashMap<>();

                exception.getBindingResult()
                                .getFieldErrors()
                                .forEach(error -> fieldErrors.put(
                                                error.getField(),
                                                error.getDefaultMessage()));

                return new ValidationErrorResponse(
                                HttpStatus.BAD_REQUEST.value(),
                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                                "Validation failed",
                                fieldErrors);
        }

        @ExceptionHandler(OrganizationNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ApiError handleOrganizationNotFound(OrganizationNotFoundException exception) {
                return new ApiError(
                                HttpStatus.NOT_FOUND.value(),
                                HttpStatus.NOT_FOUND.getReasonPhrase(),
                                exception.getMessage());
        }
}