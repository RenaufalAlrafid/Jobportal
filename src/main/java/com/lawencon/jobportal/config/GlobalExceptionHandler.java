package com.lawencon.jobportal.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.lawencon.jobportal.model.response.WebResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<WebResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    // Create a Map<String, List<String>> for errors
    Map<String, List<String>> errors = ex.getBindingResult().getFieldErrors().stream()
        .collect(Collectors.toMap(
            fieldError -> fieldError.getField(),
            fieldError -> List.of(fieldError.getDefaultMessage()), // Wrap each error message in a List
            (messages1, messages2) -> {
              // Combine messages for the same field
              List<String> combinedMessages = new ArrayList<>(messages1);
              combinedMessages.addAll(messages2);
              return combinedMessages;
            }));

    // Build the WebResponse with errors
    WebResponse<Object> errorResponse = WebResponse.builder()
        .code(HttpStatus.BAD_REQUEST.value())
        .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
        .errors(errors)
        .message("Validation failed")
        .build();

    // Return the response with a 400 Bad Request status
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
