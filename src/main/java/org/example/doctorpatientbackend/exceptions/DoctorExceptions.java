package org.example.doctorpatientbackend.exceptions;

public class DoctorExceptions extends RuntimeException{
    public DoctorExceptions(){}

    public DoctorExceptions(String mess) {
        super(mess);
    }
}
