package com.yosef.moontrack.exceptions.globalExceptionHandler;


import com.yosef.moontrack.exceptions.applicationException.ApplicationException;
import com.yosef.moontrack.exceptions.notFoundException.NotFoundException;
import com.yosef.moontrack.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.UnknownHostException;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Response<String>> handleAllExceptions(Exception ex) {
    log.error(ex.getMessage(), ex);

    if (ex.getCause() instanceof UnknownHostException) {
      return Response.failedResponse(HttpStatus.BAD_REQUEST.value(), "Unable to process the request", ex.getLocalizedMessage());
    }

    return Response.failedResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "We are unable to process your request at this time, please try again later.", ex.getMessage());
  }

  @ExceptionHandler(ApplicationException.class)
  public ResponseEntity<Response<Object>> handleApplicationException(ApplicationException ex) {
    return Response.failedResponse(ex.getHttpStatus().value(), "Bad Request", ex.getMessage());
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Response<Object>> handleNotFoundException (NotFoundException ex) {
    log.error(ex.getMessage(), ex);
    return Response.failedResponse(HttpStatus.NOT_FOUND.value(), "Not found", null);
  }

  @ExceptionHandler(MetaDataAccessException.class)
  public ResponseEntity<Response<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    log.error(ex.getMessage(), ex);
    String errorMessage = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
    return Response.failedResponse(HttpStatus.BAD_REQUEST.value(), "Unable to process the request", errorMessage);
  }
}
