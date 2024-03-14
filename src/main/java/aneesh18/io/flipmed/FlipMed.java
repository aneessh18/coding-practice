package aneesh18.io.flipmed;


import aneesh18.io.flipmed.model.Doctor;
import aneesh18.io.flipmed.model.Patient;
import aneesh18.io.flipmed.model.Specialisation;
import aneesh18.io.flipmed.repository.AppointmentRepository;
import aneesh18.io.flipmed.repository.DoctorRepository;
import aneesh18.io.flipmed.repository.PatientRepository;
import aneesh18.io.flipmed.service.*;

import java.time.LocalTime;
import java.util.UUID;

public class FlipMed {
    public void test(){
        AppointmentRepository appointmentRepository = new AppointmentRepository();
        DoctorRepository doctorRepository = new DoctorRepository();
        PatientRepository patientRepository = new PatientRepository();
        RankingStrategyService rankingStrategyService = new SimpleRankingStrategyService();
        BookingService bookingService = new FlipMedBookingService(appointmentRepository, doctorRepository, rankingStrategyService);
        RegistrationService registrationService = new SimpleRegistrationService(doctorRepository, patientRepository, appointmentRepository);

        Doctor curious = new Doctor(UUID.randomUUID().toString(), "Curious", Specialisation.CARDIOLOGIST, 4);

        registrationService.registerDoctor(curious);
        var curiousA1 = registrationService.addAppointment(LocalTime.parse("09:30"), curious.id());
        var curiousA2 = registrationService.addAppointment(LocalTime.parse("12:30"), curious.id());
        var curiousA3 = registrationService.addAppointment(LocalTime.parse("16:30"), curious.id());

        Doctor dreadful = new Doctor(UUID.randomUUID().toString(), "Dreadful", Specialisation.DERMATOLOGIST, 4);

         registrationService.addAppointment(LocalTime.parse("09:30"), dreadful.id());
         registrationService.addAppointment(LocalTime.parse("12:30"), dreadful.id());
         registrationService.addAppointment(LocalTime.parse("16:30"), dreadful.id());

        System.out.println(bookingService.getAppointmentsBySpeciality(Specialisation.CARDIOLOGIST));

        Patient Aneesh = new Patient(UUID.randomUUID().toString(), "Aneesh");
        Patient Bola = new Patient(UUID.randomUUID().toString(), "Bola");
        Patient Chola = new Patient(UUID.randomUUID().toString(), "Chola");
        Patient Dola = new Patient(UUID.randomUUID().toString(), "Dola");
        Patient Fiona = new Patient(UUID.randomUUID().toString(), "Fiona");
        registrationService.registerPatient(Aneesh);

        // the appointment id is the booking id here.
        //
        bookingService.bookAppointmentForPatient(Aneesh.id(), curiousA2.id());

        System.out.println(bookingService.getAppointmentsBySpeciality(Specialisation.CARDIOLOGIST));

        bookingService.cancelAppointmentForPatient(curiousA1.id());

        System.out.println(bookingService.getAppointmentsBySpeciality(Specialisation.CARDIOLOGIST));

        bookingService.bookAppointmentForPatient(Bola.id(), curiousA2.id());

        Doctor daring = new Doctor(UUID.randomUUID().toString(), "Daring", Specialisation.DERMATOLOGIST, 4);

        registrationService.registerDoctor(daring);

        var daringA1 = registrationService.addAppointment(LocalTime.parse("11:30"), daring.id());
        registrationService.addAppointment(LocalTime.parse("14:00"), daring.id());

        System.out.println(bookingService.getAppointmentsBySpeciality(Specialisation.DERMATOLOGIST));

        bookingService.bookAppointmentForPatient(Fiona.id(), daringA1.id());

        bookingService.bookAppointmentForPatient(Aneesh.id(), curiousA2.id());

        bookingService.bookAppointmentForPatient(Fiona.id(), curiousA1.id());

        bookingService.bookAppointmentForPatient(Chola.id(), curiousA3.id());

        System.out.println(bookingService.getAppointmentsBySpeciality(Specialisation.CARDIOLOGIST));

        bookingService.waitListPatient(Dola.id(), curiousA3.id());

        bookingService.cancelAppointmentForPatient(curiousA3.id());

        bookingService.getBookedAppointmentsForPatient(Fiona.id());

    }
}
