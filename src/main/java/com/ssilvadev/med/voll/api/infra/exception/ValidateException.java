package com.ssilvadev.med.voll.api.infra.exception;

public class ValidateException extends RuntimeException {
   public ValidateException(String message){
       super(message);
   }
}
