package org.example.doctorpatientbackend.entities;

import org.example.doctorpatientbackend.enums.Symptoms;
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
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer patientId;

    @Length(min = 3, message = "Should be atleast 3 characters!")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must not contain any numbers and special characters!")
    private String name;

    @Length(max = 20,message = "Should be at max 20 characters!")
    private String city;

    @Email(message = "Invalid Email id!")
    private String email;

    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @Pattern(regexp = "\\d{10}",message = "Phone number must contains 10 digit")
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    private Symptoms symptoms;

    // Getter and Setter methods for patientId
    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    // Getter and Setter methods for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter methods for city
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Getter and Setter methods for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter methods for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter methods for phoneNo
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    // Getter and Setter methods for symptoms
    public Symptoms getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(Symptoms symptoms) {
        this.symptoms = symptoms;
    }
}
