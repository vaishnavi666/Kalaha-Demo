package com.demo.kalaha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class KalahaExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(KalahaException.class)
    public final ResponseEntity handleKalahaException(final KalahaException e){
        ExceptionResponse response = new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


    @ExceptionHandler(KalahaIllegalMoveException.class)
    public final ResponseEntity handleIllegalMove(final KalahaIllegalMoveException e){
        ExceptionResponse response = new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
