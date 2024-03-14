package aneesh18.io.flipmed.model;

import java.time.LocalTime;

public record Appointment(String id, String doctorId, AppointmentStatus status, LocalTime startTime, String patientId) {
}
