package com.byteworks.servbyte.response;

import com.byteworks.servbyte.exception.ApiSubError;
import com.byteworks.servbyte.exception.ApiValidationError;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Data
@JsonIgnoreProperties
public class AppResponse<T> implements Serializable {

    private HttpStatus status;

    private String message;

    private String error;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private String debugMessage;

    private List<ApiSubError> subErrors;

    private T data;


    private AppResponse() {
        timestamp = LocalDateTime.now();
    }


    public AppResponse(HttpStatus status) {
        this();
        this.status = status;
    }


    public AppResponse(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.error = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }


    public AppResponse(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    public AppResponse(HttpStatus status, String message, T data) {
        this();
        this.status = status;
        this.message = message;
        this.data = data;
    }


    private void addSubError(ApiSubError subError) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }


    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubError(new ApiValidationError(object, field, rejectedValue, message));
    }


    private void addValidationError(String object, String message) {
        addSubError(new ApiValidationError(object, message));
    }


    private void addValidationError(FieldError fieldError) {
        this.addValidationError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }


    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }


    private void addValidationError(ObjectError objectError) {
        this.addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
    }


    public void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }


    /**
     * Utility method for adding error of ConstraintViolation. Usually when
     * a @Validated validation fails.
     *
     * @param cv the ConstraintViolation
     */
    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(cv.getRootBeanClass().getSimpleName(),
                ((PathImpl) cv.getPropertyPath()).getLeafNode().asString(), cv.getInvalidValue(), cv.getMessage());
    }


    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }

}
