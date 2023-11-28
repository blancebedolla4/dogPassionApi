package com.dog.dogapi.handler;


//import com.dog.dogapi.error.ErrorDetail;
//import com.dog.dogapi.error.ValidationError;
//import com.dog.dogapi.exceptions.DogNotFoundException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@ControllerAdvice
//public class DogRestExceptionHandler extends ResponseEntityExceptionHandler {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(DogRestExceptionHandler.class);
//
//    @ExceptionHandler(DogNotFoundException.class)
//    public ResponseEntity<?> handleDogNotFoundException(DogNotFoundException dnfe, WebRequest request) {
//        LOGGER.error("Dog Not Found", dnfe);
//        ErrorDetail errorDetail = new ErrorDetail();
//        errorDetail.setTimeStamp(new Date().getTime());
//        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
//        errorDetail.setTitle("Dog Not Found");
//        errorDetail.setDetail(dnfe.getMessage());
//        errorDetail.setDeveloperMessage(dnfe.getClass().getName());
//        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
//    }
//
//
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        LOGGER.error("Message Not Readable", ex);
//        ErrorDetail errorDetail = new ErrorDetail();
//        errorDetail.setTimeStamp(new Date().getTime());
//        errorDetail.setStatus(status.value());
//        errorDetail.setTitle("Message Not Readable");
//        errorDetail.setDetail(ex.getMessage());
//        errorDetail.setDeveloperMessage(ex.getClass().getName());
//
//        return handleExceptionInternal(ex, errorDetail, headers, status, request);
//    }
//
//
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manve, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        LOGGER.error("Method Argument Not Valid", manve);
//        ErrorDetail errorDetail = new ErrorDetail();
//        errorDetail.setTimeStamp(new Date().getTime());
//        errorDetail.setStatus(status.value());
//        errorDetail.setTitle("Method Argument Not Valid");
//        errorDetail.setDetail("Input Validation Failed");
//        errorDetail.setDeveloperMessage(manve.getClass().getName());
//
//        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
//        for (FieldError fe : fieldErrors) {
//            List<ValidationError> validationErrorList = errorDetail.getErrors().get(fe.getField());
//            if (validationErrorList == null) {
//                validationErrorList = new ArrayList<>();
//                errorDetail.getErrors().put(fe.getField(), validationErrorList);
//            }
//            ValidationError validationError = new ValidationError();
//            validationError.setCode(fe.getCode());
//            validationError.setMessage(fe.getDefaultMessage());
//            validationErrorList.add(validationError);
//        }
//
//        return handleExceptionInternal(manve, errorDetail, headers, status, request);
//    }
//}
