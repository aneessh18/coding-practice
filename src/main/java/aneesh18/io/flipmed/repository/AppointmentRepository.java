package aneesh18.io.flipmed.repository;

import aneesh18.io.flipmed.model.Appointment;
import aneesh18.io.flipmed.model.AppointmentStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppointmentRepository {
    Map<String, Appointment> appointments = new HashMap<>();

    public void add(Appointment appointment){
        appointments.put(appointment.id(), appointment);
    }

    public Appointment get(String appointmentId){
        // illegal state exception
       return appointments.get(appointmentId);
    }
    public void cancel(String id){
        // illegal state exception
        Appointment cancelled = appointments.get(id);
        appointments.put(cancelled.id(), new Appointment(cancelled.id(), cancelled.doctorId(), AppointmentStatus.VACANT, cancelled.startTime(), cancelled.patientId()));
    }
    public List<Appointment> getAppointmentsForDoctorsByStatus(List<String> doctorIds, AppointmentStatus status){
        return appointments.values()
                .stream()
                .filter(appointment -> appointment.status() == status&& doctorIds.contains(appointment.doctorId()))
                .toList();
    }
    public List<Appointment> getAppointmentsForDoctor(String doctorId){
        return appointments.values()
                .stream()
                .filter(appointment ->  appointment.doctorId().equals(doctorId))
                .toList();
    }
    public List<Appointment> getBookingsForPatient(String patientId){
        return appointments.values()
                .stream()
                .filter(appointment ->  appointment.status() == AppointmentStatus.BOOKED && appointment.patientId() != null && appointment.patientId().equals(patientId))
                .toList();
    }

}
