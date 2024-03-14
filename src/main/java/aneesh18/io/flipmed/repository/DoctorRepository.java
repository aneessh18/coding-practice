package aneesh18.io.flipmed.repository;

import aneesh18.io.flipmed.model.Doctor;
import aneesh18.io.flipmed.model.Specialisation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorRepository {
    Map<String,Doctor> doctors = new HashMap<>();
    public void add(Doctor doctor) {
        doctors.put(doctor.id(), doctor);
    }
    public List<String> getDoctorIdsBySpecialisation(Specialisation specialisation){
        return doctors.values().stream().filter(doctor -> doctor.specialisation() == specialisation).map(Doctor::id).toList();
    }
}
