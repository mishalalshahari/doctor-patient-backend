package org.example.doctorpatientbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.example.doctorpatientbackend.entities.Doctor;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Integer> {
    Optional<Doctor> findByPhoneNo(String phoneNo);

    Optional<Doctor> findByEmail(String email);

    Optional<Doctor> findByEmailAndPassword(String email, String password);

    @Query(value = "select * from Doctor d where d.city = :city", nativeQuery = true)
    List<Doctor> getDoctorBasedOnLocation(@Param("city") String city);
}
