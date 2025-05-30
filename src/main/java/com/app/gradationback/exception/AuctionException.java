package com.app.gradationback.exception;

public class AuctionException extends RuntimeException{
    public AuctionException(){;}
    public AuctionException(String message){
        super(message);
    }
    public AuctionException(String message, Throwable cause){
        super(message, cause);
    }
    public AuctionException(Throwable cause){
        super(cause);
    }
}
