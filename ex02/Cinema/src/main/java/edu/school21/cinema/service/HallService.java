package edu.school21.cinema.service;

import edu.school21.cinema.model.Hall;
import edu.school21.cinema.repositories.HallRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {

    @Autowired
    public HallRepo hallRepo;

    public List<Hall> getHalls() {
        return hallRepo.findAll();
    }

    public void addNew(Hall hall) {
        hallRepo.save(hall);
    }
}
