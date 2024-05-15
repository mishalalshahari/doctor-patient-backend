package org.example.doctorpatientbackend.servicesimplementations;

import org.example.doctorpatientbackend.DTO.LoginDTO;
import org.example.doctorpatientbackend.entities.Doctor;
import org.example.doctorpatientbackend.exceptions.DoctorExceptions;
import org.example.doctorpatientbackend.repository.DoctorRepo;
import org.example.doctorpatientbackend.services.DoctorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImp implements DoctorServices {

    @Autowired
    private DoctorRepo docRepo;

    @Override
    public Doctor register(Doctor doctor) throws DoctorExceptions {

        Optional<Doctor> emailCheck = docRepo.findByEmail(doctor.getEmail());
        Optional<Doctor> phoneCheck = docRepo.findByPhoneNo(doctor.getPhoneNo());
        if(phoneCheck.isPresent()) {
            throw new DoctorExceptions("Doctor already exists with the phone number: " + doctor.getPhoneNo());
        }
        else if(emailCheck.isPresent()) {
            throw new DoctorExceptions("Doctor already exists with the email: " + doctor.getEmail());
        }
        else {
            return docRepo.save(doctor);
        }
    }

    @Override
    public Doctor login(LoginDTO logindto) throws DoctorExceptions {

        Optional<Doctor> opt = docRepo.findByEmailAndPassword(logindto.getEmail(), logindto.getPassword());
        if(opt.isEmpty()) {
            throw new DoctorExceptions("Doctor does not exists with the email: " + logindto.getEmail() + ", Please register first!");
        }
        else {
            return opt.get();
        }
    }

    @Override
    public Doctor getDoctorEmail(String email) throws DoctorExceptions {
        Optional<Doctor> opt = docRepo.findByEmail(email);
        if(opt.isEmpty()) {
            throw new DoctorExceptions("Doctor does not exist with the email: " + email);
        }
        else {
            return opt.get();
        }
    }

    @Override
    public List<Doctor> getAllDoctors() throws DoctorExceptions {
        List<Doctor> doctors = docRepo.findAll();
        if(doctors.isEmpty()) {
            throw new DoctorExceptions("Doctor does not exists!");
        }
        return doctors;
    }

    @Override
    public Doctor deleteByEmail(String email) throws DoctorExceptions {
        Optional<Doctor> opt = docRepo.findByEmail(email);
        if(opt.isEmpty()) {
            throw new DoctorExceptions("Doctor does not exist with the email: " + email);
        }else {
            Doctor d1 = opt.get();
            docRepo.delete(d1);
            return d1;
        }
    }
}
