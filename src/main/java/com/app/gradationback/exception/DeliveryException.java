package com.app.gradationback.exception;

public class DeliveryException extends RuntimeException{
    public DeliveryException(){;}
    public DeliveryException(String message){
        super(message);
    }
    public DeliveryException(String message, Throwable cause){
        super(message, cause);
    }
    public DeliveryException(Throwable cause){
        super(cause);
    }
}
