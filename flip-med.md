
A new doctor should be able to register, and mention his/her speciality among (Cardiologist, Dermatologist, Orthopedic, General Physician)

A doctor should be able to declare his/her availability in each slot for the day. For example, the slots will be of 30 mins like 9am-9.30am, 9.30am-10am..

Patients should be able to register. Patients should be able to search available slots based on speciality.

The slots should be displayed in a ranked fashion. Default ranking strategy should be to rank by start startTime. But we should be able to plugin more strategies like Doctor’s rating etc in future.

Patients should be able to book appointments with a doctor for an available slot.A patient can book multiple appointments in a day.  A patient cannot book two appointments with two different doctors in the same startTime slot.

Patients can also cancel an appointment, in which case that slot becomes available for someone else to book.

Build a waitlist feature:
If the patient wishes to book a slot for a particular doctor that is already booked, then add this patient to the waitlist.
If the patient with whom the appointment was booked originally cancels the appointment, then the first in the waitlist gets the appointment.

A patient/doctor should be able to view his/her booked appointments for the day.

Trending Doctor: Maintain at any point of startTime which doctor has the most appointments.

Different ranking strategy - use rating of doctor as a strategy to display a list of available doctors for a given specialization.

i:registerDoc -> Curious-> Cardiologist

o: Welcome Dr. Curious !!

i: markDocAvail: Curious 9:30-10:30

o: Sorry Dr. Curious slots are 30 mins only

i: markDocAvail: Curious 9:30-10:00, 12:30-13:00, 16:00-16:30

o: Done Doc!

i:registerDoc -> Dreadful-> Dermatologist

o: Welcome Dr. Dreadful !!

i: markDocAvail: Dreadful 9:30-10:00, 12:30-13:00, 16:00-16:30

o: Done Doc!

i: showAvailByspeciality: Cardiologist

o: Dr.Curious: (9:30-10:00)

o: Dr.Curious: (12:30-13:00)

o: Dr.Curious: (16:00-16:30)

i: registerPatient ->PatientA

o: PatientA registered successfully.

i:  bookAppointment: (PatientA, Dr.Curious, 12:30)

O: Booked. Booking id: 1234

i:showAvailByspeciality: Cardiologist

o: Dr.Curious: (9:30-10:00)

o: Dr.Curious: (16:00-16:30)

i: cancelBookingId: 1234

o: Booking Cancelled

i: showAvailByspeciality: Cardiologist

o: Dr.Curious: (9:30-10:00)

o: Dr.Curious: (12:30-13:00)

o: Dr.Curious: (16:00-16:30)

i: bookAppointment: (PatientB, Dr.Curious, 12:30)

o: Booked. Booking id: 5678

i:registerDoc -> Daring-> Dermatologist

o: Welcome Dr. Daring !!

i: markDocAvail: Daring 11:30-12:00 14:00-14:30

o: Done Doc!

i: showAvailByspeciality: Dermatologist

o: Dr.Dreadful: (9:30-10:00)

o: Dr.Daring: (11:30-12:00)

o: Dr.Dreadful: (12:30-13:00)

o:Dr.Daring:(14:00-14:30)

o: Dr.Dreadful: (16:00-16:30)

i: bookAppointment: (PatientF, Dr.Daring, 11:30)

o: Booked. Booking id: 5587

i: bookAppointment: (PatientA, Dr.Curious, 12:30)

o: Booked. Booking id: 5678

i: bookAppointment: (PatientF, Dr.Curious, 9:30)

o: Booked. Booking id: 5280

i: bookAppointment: (PatientC, Dr.Curious, 16:00)

o: Booked. Booking id: 5701

i: showAvailByspeciality: Cardiologist

o: Dr.Curious: No slots available

i: bookAppointment: (PatientD, Dr.Curious, 16:00, waitlist=true)

o: Added to the waitlist. Booking id: 5710

i: cancelBookingId: 5701

o: Booking Cancelled

o: Booking confirmed for Booking id: 5710

i: showAppointmentsBooked(PatientF)

o: Booking id: 5280, Dr Curious 9:30

o: Booking id: 5587 , Dr Daring 11:30