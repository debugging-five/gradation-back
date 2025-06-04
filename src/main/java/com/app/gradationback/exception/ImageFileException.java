package com.app.gradationback.exception;

import java.io.IOException;

public class ImageFileException extends IOException {
    public ImageFileException(){;}
    public ImageFileException(String message){
        super(message);
    }
    public ImageFileException(String message, Throwable cause){
        super(message, cause);
    }
    public ImageFileException(Throwable cause){
        super(cause);
    }
}
