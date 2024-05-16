package org.example.doctorpatientbackend.services;

import org.example.doctorpatientbackend.DTO.LoginDTO;
import org.example.doctorpatientbackend.entities.Doctor;
import org.example.doctorpatientbackend.entities.Patient;
import org.example.doctorpatientbackend.exceptions.DoctorExceptions;
import org.example.doctorpatientbackend.exceptions.PatientExceptions;

import java.util.List;

public interface PatientServices {

    Patient register(Patient patient) throws PatientExceptions;

    Patient login(LoginDTO Logindto) throws PatientExceptions;

    Patient getPatientEmail(String email) throws PatientExceptions;

    List<Patient> getAllPatient() throws PatientExceptions;

    Patient deleteByEmail(String email) throws PatientExceptions;

    List<Doctor> getSuggesstions(String email) throws DoctorExceptions,PatientExceptions;

}
