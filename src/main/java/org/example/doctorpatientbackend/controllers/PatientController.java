package org.example.doctorpatientbackend.controllers;

import jakarta.validation.Valid;
import org.example.doctorpatientbackend.DTO.LoginDTO;
import org.example.doctorpatientbackend.entities.Doctor;
import org.example.doctorpatientbackend.entities.Patient;
import org.example.doctorpatientbackend.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/patients")
public class PatientController {

    @Autowired
    private PatientServices patService;

    @PostMapping("/register")
    public ResponseEntity<Patient> registerHandler(@Valid @RequestBody Patient patient){
        Patient patObject = patService.register(patient);
        return new ResponseEntity<>(patObject, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<Patient> registerHandler(@Valid @RequestBody LoginDTO logindto){
        Patient patObject = patService.login(logindto);
        return new ResponseEntity<>(patObject,HttpStatus.CREATED);

    }

    @GetMapping("")
    public ResponseEntity<List<Patient>> getAllHandler(){
        List<Patient> list = patService.getAllPatient();
        return new ResponseEntity<List<Patient>>(list,HttpStatus.OK);

    }
    @GetMapping("/{email}")
    public ResponseEntity<Patient> getByEmailHandler(@Valid @RequestParam String email){
        Patient patObject = patService.getPatientEmail(email);
        return new ResponseEntity<Patient>(patObject,HttpStatus.OK);

    }
    @DeleteMapping("/{email}")
    public ResponseEntity<Patient> deleteByEmailHandler(@Valid @RequestParam String email){
        Patient patObject = patService.deleteByEmail(email);
        return new ResponseEntity<Patient>(patObject,HttpStatus.OK);

    }

    @GetMapping("/suggessions/{email}")
    public ResponseEntity<List<Doctor>> getSugeessionsHandler(@Valid @RequestParam String email){
        List<Doctor> patObject = patService.getSuggesstions(email);
        return new ResponseEntity<List<Doctor>>(patObject,HttpStatus.OK);

    }
}
