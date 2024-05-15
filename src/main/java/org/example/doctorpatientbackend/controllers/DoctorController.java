package org.example.doctorpatientbackend.controllers;


import jakarta.validation.Valid;
import org.example.doctorpatientbackend.DTO.LoginDTO;
import org.example.doctorpatientbackend.entities.Doctor;
import org.example.doctorpatientbackend.services.DoctorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/doctors")
public class DoctorController {

    @Autowired
    private DoctorServices docService;

    @PostMapping("/register")
    public ResponseEntity<Doctor> registerHandler(@Valid @RequestBody Doctor doctor) {
        Doctor docObject = docService.register(doctor);
        return new ResponseEntity<>(docObject, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Doctor> registerHandler(@Valid @RequestBody LoginDTO logindto){
        Doctor docObject = docService.login(logindto);
        return new ResponseEntity<>(docObject, HttpStatus.CREATED);

    }

    @GetMapping("")
    public ResponseEntity<List<Doctor>> getAllHandler(){
        List<Doctor> list = docService.getAllDoctors();
        return new ResponseEntity<List<Doctor>>(list, HttpStatus.OK);

    }
    @GetMapping("/{email}")
    public ResponseEntity<Doctor> getByEmailHandler(@RequestParam String email){
        Doctor docObject = docService.getDoctorEmail(email);
        return new ResponseEntity<Doctor>(docObject, HttpStatus.OK);

    }
    @DeleteMapping("/{email}")
    public ResponseEntity<Doctor> deleteByEmailHandler(@RequestParam String email){
        Doctor docObject = docService.deleteByEmail(email);
        return new ResponseEntity<Doctor>(docObject, HttpStatus.OK);

    }
}
