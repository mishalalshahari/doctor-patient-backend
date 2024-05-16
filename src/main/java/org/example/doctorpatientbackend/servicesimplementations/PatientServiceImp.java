package org.example.doctorpatientbackend.servicesimplementations;

import org.example.doctorpatientbackend.DTO.LoginDTO;
import org.example.doctorpatientbackend.entities.Doctor;
import org.example.doctorpatientbackend.entities.Patient;
import org.example.doctorpatientbackend.enums.Symptoms;
import org.example.doctorpatientbackend.exceptions.DoctorExceptions;
import org.example.doctorpatientbackend.exceptions.PatientExceptions;
import org.example.doctorpatientbackend.repository.DoctorRepo;
import org.example.doctorpatientbackend.repository.PatientRepo;
import org.example.doctorpatientbackend.services.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImp implements PatientServices {
    @Autowired
    private PatientRepo patRepo;

    @Autowired
    private DoctorRepo docRepo;

    @Override
    public Patient register(Patient patient) throws PatientExceptions {
        Optional<Patient> emailCheck = patRepo.findByEmail(patient.getEmail());
        Optional<Patient> phoneCheck = patRepo.findByPhoneNo(patient.getPhoneNo());
        if (emailCheck.isPresent()) {
            throw new PatientExceptions("Patient already exists with the email: " + patient.getEmail());
        }else if(phoneCheck.isPresent()) {
            throw new PatientExceptions("Patient already exists with the phone number: " + patient.getPhoneNo());
        }
        else {
            return patRepo.save(patient);
        }
    }

    @Override
    public Patient login(LoginDTO logindto) throws PatientExceptions {
        Optional<Patient> opt = patRepo.findByEmailAndPassword(logindto.getEmail(), logindto.getPassword());
        if (opt.isEmpty()) {
            throw new PatientExceptions(
                    "Patient does not exist with the email: " + logindto.getEmail() + " or you have enter wrong details!");
        } else {
            return opt.get();
        }

    }

    @Override
    public Patient getPatientEmail(String email) throws PatientExceptions {
        Optional<Patient> opt = patRepo.findByEmail(email);
        if (opt.isEmpty()) {
            throw new PatientExceptions("patient does not exist with the email: " + email);
        } else {
            return opt.get();
        }
    }

    @Override
    public List<Patient> getAllPatient() throws PatientExceptions {
        List<Patient> list = patRepo.findAll();
        if (list.isEmpty()) {
            throw new PatientExceptions("Patient does not exists!");
        } else {
            return list;
        }
    }

    @Override
    public Patient deleteByEmail(String email) throws PatientExceptions {
        Optional<Patient> opt = patRepo.findByEmail(email);
        if (opt.isEmpty()) {
            throw new PatientExceptions("Patient does not exists with the email: " + email);
        } else {
            Patient p = opt.get();
            patRepo.delete(p);
            return p;
        }
    }

    @Override
    public List<Doctor> getSuggesstions(String email) throws DoctorExceptions, PatientExceptions {
        Optional<Patient> opt = patRepo.findByEmail(email);

        if (opt.isEmpty()) {
            throw new PatientExceptions("Patient does not exists with the email: " + email);
        } else {
            Patient p = opt.get();
            Symptoms symptoms = p.getSymptoms();
            String city = p.getCity();
            System.out.println(symptoms.toString().equals("Arthritis"));

            List<Doctor> doctors = docRepo.getDoctorBasedOnLocation(city);
            if (doctors.isEmpty()) {
                throw new DoctorExceptions("We are still waiting to expand to your location, Thank you!");
            } else {
                List<Doctor> output = new ArrayList<>();
                for (Doctor doctor : doctors) {

                    if (symptoms.toString().equals("Arthritis") || symptoms.toString().equals("BackPain")
                            || symptoms.toString().equals("TissueInjuries")) {
                        if (doctor.getSpeciality().toString().equals("Orthopedic")) {
                            output.add(doctor);
                        } else {
                            throw new DoctorExceptions(
                                    "There isn’t any doctor present at your location for your symptom.");
                        }
                    } else if (symptoms.toString().equals("Dysmenorrhea")) {
                        if (doctor.getSpeciality().toString().equals("Gynecology")) {
                            output.add(doctor);
                        } else {
                            throw new DoctorExceptions(
                                    "There isn’t any doctor present at your location for your symptom.");
                        }
                    } else if (symptoms.toString().equals("SkinInfection") || symptoms.toString().equals("SkinBurn")) {
                        if (doctor.getSpeciality().toString().equals("Dermatology")) {
                            output.add(doctor);
                        } else {
                            throw new DoctorExceptions(
                                    "There isn’t any doctor present at your location for your symptom.");
                        }
                    } else if (symptoms.toString().equals("EarPain")) {
                        if (doctor.getSpeciality().toString().equals("ENT")) {
                            output.add(doctor);
                        } else {
                            throw new DoctorExceptions(
                                    "There isn’t any doctor present at your location for your symptom.");
                        }
                    } else {
                        throw new DoctorExceptions("There isn’t any doctor present at your location for your symptom.");
                    }
                }
                System.out.println(output);
                return output;
            }
        }
    }
}
