package org.example.doctorpatientbackend.repository;

import org.example.doctorpatientbackend.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer> {

    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByPhoneNo(String phoneNo);

    Optional<Patient> findByEmailAndPassword(String email,String password);
}