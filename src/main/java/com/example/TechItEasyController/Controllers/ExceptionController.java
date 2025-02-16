package com.example.TechItEasyController.Controllers;

import com.example.TechItEasyController.Exceptions.InvalidTelevisionNameException;
import com.example.TechItEasyController.Exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {


    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<String> recordNotFoundException(RecordNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidTelevisionNameException.class)
    public ResponseEntity<String> invalidTelevisionNameException(InvalidTelevisionNameException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }


//    @ExceptionHandler
//    public ResponseEntity<String> indexOutOfBoundsException(IndexOutOfBoundsException exception) {
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<String> nameOfTelevisionToLong(ConstraintViolationException exception) {
//        return new ResponseEntity<>(exception.getMessage(), HttpStatus.LENGTH_REQUIRED);
//    }
}
