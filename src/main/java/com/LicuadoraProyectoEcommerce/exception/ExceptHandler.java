package com.LicuadoraProyectoEcommerce.exception;

import com.LicuadoraProyectoEcommerce.message.MessageInfo;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptHandler {
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<MessageInfo> badRequestException(BadRequestException ex, HttpServletRequest request){
        return ResponseEntity.badRequest().body(new MessageInfo(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), request.getRequestURL().toString()));
    }
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<MessageInfo> notFoundException(NotFoundException ex, HttpServletRequest request){
        return ResponseEntity.status(404).body(new MessageInfo(ex.getMessage(), HttpStatus.NOT_FOUND.value(), request.getRequestURL().toString()));
    }
    @ExceptionHandler({NumberFormatException.class})
    public ResponseEntity<MessageInfo> numberFormatException(NumberFormatException ex, HttpServletRequest request){
        return ResponseEntity.badRequest().body(new MessageInfo("Format error "+ ex.getMessage(), HttpStatus.BAD_REQUEST.value(), request.getRequestURL().toString()));
    }
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Map<String, String>> constraintViolationException(ConstraintViolationException exception, HttpServletRequest request){
        Map<String, String> errors = new HashMap<>();
        exception.getConstraintViolations().stream().forEach((e)->
                errors.put(e.getPropertyPath().toString(), e.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public ResponseEntity<MessageInfo> sQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception, HttpServletRequest request){
        String msgComplete = exception.getMessage();
        String message  = msgComplete.substring(msgComplete.lastIndexOf("entry")+5, msgComplete.lastIndexOf("for"));
        return ResponseEntity.badRequest().body(new MessageInfo("the value"+ message + "its duplicate", HttpStatus.BAD_REQUEST.value(), request.getRequestURL().toString()));
    }
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> methodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().stream().forEach((e) ->
                {errors.put(e.getField(), e.getDefaultMessage());});
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<MessageInfo> illegalArgumentException(IllegalArgumentException ex, HttpServletRequest request){
        return ResponseEntity.badRequest().body(new MessageInfo(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), request.getRequestURL().toString()));
    }
    @ExceptionHandler({TokenExpiredException.class})
    public ResponseEntity<MessageInfo> tokenExpiredException(TokenExpiredException ex, HttpServletRequest request){
        String msgComplete = ex.getMessage();
        String message  = msgComplete.substring(0, msgComplete.lastIndexOf(" on "));
        return ResponseEntity.badRequest().body(new MessageInfo(message + ", please login again!!", HttpStatus.BAD_REQUEST.value(), request.getRequestURL().toString()));
    }
    @ExceptionHandler({JWTDecodeException.class})
    public ResponseEntity<MessageInfo> jWTDecodeException(JWTDecodeException ex, HttpServletRequest request){
        return ResponseEntity.badRequest().body(new MessageInfo("the token is incomplete or don't have a valid format", HttpStatus.BAD_REQUEST.value(), request.getRequestURL().toString()));
    }
}
