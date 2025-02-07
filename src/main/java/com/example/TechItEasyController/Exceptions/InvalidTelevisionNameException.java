package com.example.TechItEasyController.Exceptions;

public class InvalidTelevisionNameException extends RuntimeException {

    public InvalidTelevisionNameException(String getName) {
        super(getName + " has the wrong number of characters, the name must be between 3 and 20 characters.");
    }
}
