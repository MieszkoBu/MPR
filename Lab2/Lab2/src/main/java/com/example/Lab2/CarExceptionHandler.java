package com.example.Lab2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CarExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CarNotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler({CarAlreadyExistExeption.class})
    protected ResponseEntity<Object> handleExist(RuntimeException ex, WebRequest request){
        return ResponseEntity.notFound().build();
    }
    public static class CarNotFoundException extends RuntimeException{
         public CarNotFoundException(){
            super("Samochód nie został znaleziony");
        }
    }
    public static class CarAlreadyExistExeption extends RuntimeException{
        public CarAlreadyExistExeption(){
            super("Samochód istnieje");
        }
    }

}
