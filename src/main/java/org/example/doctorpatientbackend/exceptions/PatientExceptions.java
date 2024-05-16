package org.example.doctorpatientbackend.exceptions;

public class PatientExceptions extends RuntimeException{

    public PatientExceptions(){}

    public PatientExceptions(String mess){
        super(mess);
    }

}
