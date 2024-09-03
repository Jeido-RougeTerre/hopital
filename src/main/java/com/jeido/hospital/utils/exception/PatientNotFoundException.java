package com.jeido.hospital.utils.exception;

public class PatientNotFoundException extends RuntimeException{

    public PatientNotFoundException(int id) {
        super("Patient#" + id + " not Found");
    }
}
