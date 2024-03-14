package aneesh18.io.flipmed.service;

import aneesh18.io.flipmed.model.Appointment;
import aneesh18.io.flipmed.model.AppointmentStatus;
import aneesh18.io.flipmed.model.Doctor;
import aneesh18.io.flipmed.model.Patient;
import aneesh18.io.flipmed.repository.AppointmentRepository;
import aneesh18.io.flipmed.repository.DoctorRepository;
import aneesh18.io.flipmed.repository.PatientRepository;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

public class SimpleRegistrationService implements RegistrationService{

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    public SimpleRegistrationService(DoctorRepository doctorRepository, PatientRepository patientRepository, AppointmentRepository appointmentRepository1) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository1;
    }

    @Override
    public void registerDoctor(Doctor doctor) {
        doctorRepository.add(doctor);
        System.out.println("Welcome Dr. %s".formatted(doctor.name()));
    }

    @Override
    public void registerPatient(Patient patient) {
        patientRepository.add(patient);
        System.out.println("Patient %s registered successfully".formatted(patient.name()));
    }

    @Override
    public Appointment addAppointment(LocalTime startTime, String doctorId) {
        List<Appointment> existingAppointments = appointmentRepository.getAppointmentsForDoctor(doctorId);
        LocalTime endTime = startTime.plus(30, ChronoUnit.MINUTES);

        boolean collides = existingAppointments.stream().anyMatch(appointment -> {
            var s1  =startTime;
            var e1 = endTime;
            var s2 =  appointment.startTime();
            var e2 = appointment.startTime().plus(30, ChronoUnit.MINUTES);
            return !(e1.isBefore(s2) || e2.isBefore(s1));
        } );
        if(collides){
            throw new IllegalStateException("appointments collides with an existing slot exception");
        } else{
            Appointment newAppointment = new Appointment(UUID.randomUUID().toString(), doctorId, AppointmentStatus.VACANT, startTime, null);
            appointmentRepository.add(newAppointment);
            System.out.println("Done Doc");
            return newAppointment;
        }
    }

}
