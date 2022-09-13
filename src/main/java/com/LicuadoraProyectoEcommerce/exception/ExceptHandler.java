package com.LicuadoraProyectoEcommerce.exception;

import com.LicuadoraProyectoEcommerce.message.MessageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptHandler {
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<MessageInfo> badRequestException(BadRequestException ex, HttpServletRequest request){
        return ResponseEntity.badRequest().body(new MessageInfo(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), request.getRequestURL().toString()));
    }
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<MessageInfo> notFoundException(NotFoundException ex, HttpServletRequest request){
        return ResponseEntity.badRequest().body(new MessageInfo(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), request.getRequestURL().toString()));
    }
    @ExceptionHandler({NumberFormatException.class})
    public ResponseEntity<MessageInfo> numberFormatException(NumberFormatException ex, HttpServletRequest request){
        return ResponseEntity.badRequest().body(new MessageInfo("Format error "+ ex.getMessage(), HttpStatus.BAD_REQUEST.value(), request.getRequestURL().toString()));
    }
}
