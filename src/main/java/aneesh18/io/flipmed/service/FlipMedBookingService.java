package aneesh18.io.flipmed.service;

import aneesh18.io.flipmed.api.BookingService;
import aneesh18.io.flipmed.api.RankingStrategyService;
import aneesh18.io.flipmed.model.Appointment;
import aneesh18.io.flipmed.model.AppointmentStatus;
import aneesh18.io.flipmed.model.Specialisation;
import aneesh18.io.flipmed.repository.AppointmentRepository;
import aneesh18.io.flipmed.repository.DoctorRepository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// best logging strategy is to log all the side effects of the function. to know the
public class FlipMedBookingService implements BookingService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final RankingStrategyService rankingStrategyService;
    private final HashMap<String, Queue<String>> waitListForAppointments = new HashMap<>();
    public FlipMedBookingService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, RankingStrategyService rankingStrategyService) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.rankingStrategyService = rankingStrategyService;
    }

    @Override
    public List<Appointment> getAppointmentsBySpeciality(Specialisation specialisation) {
        List<String> doctorIds = doctorRepository.getDoctorIdsBySpecialisation(specialisation);
        List<Appointment> vacantAppointments = appointmentRepository.getAppointmentsForDoctorsByStatus(doctorIds, AppointmentStatus.VACANT);
        rankingStrategyService.rank(vacantAppointments);
        return vacantAppointments;
    }

    @Override
    public void waitListPatient(String patientId, String appointmentId) {
        waitListForAppointments.putIfAbsent(appointmentId, new LinkedList<>());
        waitListForAppointments.get(appointmentId).add(patientId);
        System.out.println("Wait-listed patient-%s for appointment-%s".formatted(patientId, appointmentId));
    }


    @Override
    public void bookAppointmentForPatient(String patientId, String appointmentId) {
        Appointment vacantAppointment = appointmentRepository.get(appointmentId);
        Appointment bookedAppointment = new Appointment(
                vacantAppointment.id(),
                vacantAppointment.doctorId(),
                AppointmentStatus.BOOKED,
                vacantAppointment.startTime(),
                patientId
        );
        appointmentRepository.add(bookedAppointment);
        System.out.println("Booked appointment %s for patient %s".formatted(appointmentId, patientId));
    }

    @Override
    public void cancelAppointmentForPatient(String appointmentId) {
        Appointment existing = appointmentRepository.get(appointmentId);
        appointmentRepository.cancel(appointmentId);
        System.out.printf("Cancelled appointment-%s for %s%n",appointmentId, existing.patientId());
        if(waitListForAppointments.containsKey(appointmentId) && !waitListForAppointments.get(appointmentId).isEmpty()){
            var waitingPatient = waitListForAppointments.get(appointmentId).poll();
            bookAppointmentForPatient(waitingPatient, appointmentId);
        }

    }

    @Override
    public List<Appointment> getBookedAppointmentsForPatient(String patientId) {
        return appointmentRepository.getBookingsForPatient(patientId);
    }

    @Override
    public List<Appointment> getBookedAppointmentsForDoctor(String doctorId) {
        return appointmentRepository.getAppointmentsForDoctorsByStatus(List.of(doctorId), AppointmentStatus.BOOKED);
    }
}
