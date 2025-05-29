package com.app.gradationback.exception;

public class BiddingException extends RuntimeException{
    public BiddingException(){;}
    public BiddingException(String message){
        super(message);
    }
    public BiddingException(String message, Throwable cause){
        super(message, cause);
    }
    public BiddingException(Throwable cause){
        super(cause);
    }
}
