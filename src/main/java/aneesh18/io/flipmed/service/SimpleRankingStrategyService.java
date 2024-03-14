package aneesh18.io.flipmed.service;

import aneesh18.io.flipmed.api.RankingStrategyService;
import aneesh18.io.flipmed.model.Appointment;

import java.util.Comparator;
import java.util.List;

public class SimpleRankingStrategyService implements RankingStrategyService {
    @Override
    public List<Appointment> rank(List<Appointment> appointments) {
        return appointments.stream().sorted(Comparator.comparing(Appointment::startTime)).toList();
    }
}
