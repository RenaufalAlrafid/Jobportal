package com.lawencon.jobportal.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.lawencon.jobportal.model.response.WebResponse;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<WebResponse<Object>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    // Create a Map<String, List<String>> for errors
    Map<String, List<String>> errors = ex.getBindingResult().getFieldErrors().stream()
        .collect(Collectors.toMap(fieldError -> fieldError.getField(),
            fieldError -> List.of(fieldError.getDefaultMessage()), // Wrap each error message in a
                                                                   // List
            (messages1, messages2) -> {
              // Combine messages for the same field
              List<String> combinedMessages = new ArrayList<>(messages1);
              combinedMessages.addAll(messages2);
              return combinedMessages;
            }));

    // Build the WebResponse with errors
    WebResponse<Object> errorResponse = WebResponse.builder().code(HttpStatus.BAD_REQUEST.value())
        .status(HttpStatus.BAD_REQUEST.getReasonPhrase()).errors(errors)
        .message("Validation failed").build();

    // Return the response with a 400 Bad Request status
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<WebResponse<Object>> handleAccessDenied(HttpServletRequest request,
      AccessDeniedException ex) {
    String message;
    if (request.getUserPrincipal() == null) {
      message = "You must be logged in to access this resource.";
    } else {
      message = "You do not have permission to access this resource.";
    }
    WebResponse<Object> errorResponse = WebResponse.builder().code(HttpStatus.UNAUTHORIZED.value())
        .status(HttpStatus.UNAUTHORIZED.getReasonPhrase()).message(message).build();

    return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
  }

  // @ExceptionHandler(AuthorizationDeniedException.class)
  // public ResponseEntity<WebResponse<Object>> handleAuthorizationDenied(HttpServletRequest request,
  //     AuthorizationDeniedException ex) {
  //   String message = "Access Denied: " + ex.getMessage();
  //   WebResponse<Object> errorResponse = WebResponse.builder().code(HttpStatus.FORBIDDEN.value())
  //       .status(HttpStatus.FORBIDDEN.getReasonPhrase()).message(message).build();

  //   return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
  // }
}
