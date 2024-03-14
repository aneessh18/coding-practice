package aneesh18.io.flipmed.repository;

import aneesh18.io.flipmed.model.Patient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientRepository {

    Map<String, Patient> patients = new HashMap<>();
    public void add(Patient patient){
        patients.put(patient.id(), patient);
    }
}
