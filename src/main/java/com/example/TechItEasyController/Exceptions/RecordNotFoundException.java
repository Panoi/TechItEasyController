package com.example.TechItEasyController.Exceptions;

// Runtime exception heeft ergens een throwable
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(int id) {
        super("Dit object is niet gevonden" + id);
    }

    public RecordNotFoundException(String message) {
        super(message);
    }


}