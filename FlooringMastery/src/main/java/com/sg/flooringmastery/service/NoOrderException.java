
package com.sg.flooringmastery.service;


public class NoOrderException extends Exception{
    public NoOrderException(String message) {
        super(message);
    }
    
    public NoOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
