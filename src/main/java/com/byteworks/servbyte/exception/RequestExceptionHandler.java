package com.byteworks.servbyte.exception;

import com.byteworks.servbyte.response.AppResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;


@ControllerAdvice
public class RequestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ce) {
        AppResponse<?> ar = new AppResponse<>(ce.getStatus());
        ar.setError(ce.getMessage());
        return buildResponseEntity(ar);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException cve) {
        AppResponse<?> ar = new AppResponse<>(HttpStatus.BAD_REQUEST);
        ar.addValidationErrors(cve.getConstraintViolations());
        ar.setError("Validation Error");
        return buildResponseEntity(ar);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException iae) {
        AppResponse<?> ar = new AppResponse<>(HttpStatus.BAD_REQUEST);
        // ar.addValidationErrors();
        // iae.getCause().getLocalizedMessage();
        ar.setError(iae.getLocalizedMessage());
        return buildResponseEntity(ar);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException hmre, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        AppResponse<?> ar = new AppResponse<>(HttpStatus.BAD_REQUEST);
        ar.setError("Validation Error: "+hmre.getMostSpecificCause().getLocalizedMessage());
        return buildResponseEntity(ar);
    }

    @Override
    public ResponseEntity<Object> handleBindException(BindException be, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        AppResponse<?> ar = new AppResponse<>(HttpStatus.BAD_REQUEST);
        ar.addValidationErrors(be.getFieldErrors());
        ar.setError("Validation Error");
        return buildResponseEntity(ar);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException e) {
        AppResponse<?> ar = new AppResponse<>(HttpStatus.UNPROCESSABLE_ENTITY);
        ar.setError(e.getMessage());
        return buildResponseEntity(ar);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException mx, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        AppResponse<?> ar = new AppResponse<>(HttpStatus.BAD_REQUEST);
        ar.addValidationError(mx.getBindingResult().getAllErrors());
        ar.setError("Validation Error");
        return buildResponseEntity(ar);
    }


    private ResponseEntity<Object> buildResponseEntity(AppResponse<?> appResponse) {
        return new ResponseEntity<>(appResponse, appResponse.getStatus());
    }
}
