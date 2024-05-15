package org.example.doctorpatientbackend.services;

import org.example.doctorpatientbackend.DTO.LoginDTO;
import org.example.doctorpatientbackend.entities.Doctor;
import org.example.doctorpatientbackend.exceptions.DoctorExceptions;

import java.util.List;

public interface DoctorServices {

    Doctor register(Doctor doctor) throws DoctorExceptions;

    Doctor login(LoginDTO logindto) throws DoctorExceptions;

    Doctor getDoctorEmail(String mail) throws DoctorExceptions;

    List<Doctor> getAllDoctors() throws DoctorExceptions;

    Doctor deleteByEmail(String email) throws DoctorExceptions;
}
