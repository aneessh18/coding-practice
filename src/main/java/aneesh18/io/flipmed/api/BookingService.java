package aneesh18.io.flipmed.api;

import aneesh18.io.flipmed.model.Appointment;
import aneesh18.io.flipmed.model.Specialisation;

import java.util.List;

public interface BookingService {

    List<Appointment> getAppointmentsBySpeciality(Specialisation specialisation);
    void waitListPatient(String patientId, String appointmentId);
    void bookAppointmentForPatient(String patientId, String appointmentId);
    void cancelAppointmentForPatient( String appointmentId);
    List<Appointment> getBookedAppointmentsForPatient(String patientId);
    List<Appointment> getBookedAppointmentsForDoctor(String doctorId);
}
