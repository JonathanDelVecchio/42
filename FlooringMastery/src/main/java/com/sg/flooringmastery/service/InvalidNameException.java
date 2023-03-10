
package com.sg.flooringmastery.service;


public class InvalidNameException extends Exception{
    public InvalidNameException(String message){
        super(message);
    }
    public InvalidNameException(String message, Throwable cause){
        super(message, cause);
    }
}
