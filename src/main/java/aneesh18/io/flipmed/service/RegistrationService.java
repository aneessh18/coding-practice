package aneesh18.io.flipmed.service;

import aneesh18.io.flipmed.model.Appointment;
import aneesh18.io.flipmed.model.Doctor;
import aneesh18.io.flipmed.model.Patient;

import java.time.LocalTime;

public interface RegistrationService {
    // let the client handle creation of doctor because this way they can decide the id for themselves
    void registerDoctor(Doctor doctor);
    void registerPatient(Patient patient);
    Appointment addAppointment(LocalTime startTime, String doctorId);
}
