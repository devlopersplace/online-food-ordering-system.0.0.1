package com.example.foodordering.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice  //t centralizes error handling so we don’t repeat try‑catch blocks in every controller.
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) //When a ResourceNotFoundException occurs, it returns a 404 with the exception message
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    //For any other exception, it returns a 500 with a generic message. This improves API design by ensuring consistent error responses, better user experience, and cleaner code.”
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Something went wrong");
    }
}
