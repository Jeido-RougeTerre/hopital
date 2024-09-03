package com.jeido.hospital.utils.exception;

public class ConsultationNotFoundException extends RuntimeException {
    public ConsultationNotFoundException(int id) {
        super("Consultation#" + id + " not Found");
    }
}
