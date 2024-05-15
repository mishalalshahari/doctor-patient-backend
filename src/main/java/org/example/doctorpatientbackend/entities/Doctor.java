package org.example.doctorpatientbackend.entities;

import org.example.doctorpatientbackend.enums.City;
import org.example.doctorpatientbackend.enums.Speciality;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer doctorId;

    @Length(min = 3, message = "Should be atleast 3 characters!")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must not contain any numbers and special characters!")
    private String name;

    @Enumerated(EnumType.STRING)
    private City city;

    @Email(message = "Invalid Email id!")
    private String email;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @Pattern(regexp = "\\d{10}", message = "Phone number must contain 10 digits!")
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;
}
