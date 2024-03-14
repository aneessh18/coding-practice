package aneesh18.io.flipmed.service;

import aneesh18.io.flipmed.model.Appointment;

import java.util.List;

public interface RankingStrategyService {
    List<Appointment> rank(List<Appointment> appointments);
}
