package com.miage.altea.tp.trainer_api.service;

import com.miage.altea.tp.trainer_api.bo.Trainer;
import com.miage.altea.tp.trainer_api.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService {

    private TrainerRepository trainerRepository;

    @Autowired
    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Iterable<Trainer> getAllTrainers() {
        return this.trainerRepository.findAll();
    }

    @Override
    public Trainer getTrainer(String name) {
        return this.trainerRepository.findById(name).orElse(null);
    }

    @Override
    public Trainer createTrainer(Trainer trainer) {
        if (trainer == null) {
            throw new IllegalArgumentException("Trainer don't exist or already deleted");
        }

        return this.trainerRepository.save(trainer);
    }

    @Override
    public Trainer updateTrainer(Trainer trainer) {
        if (this.trainerRepository.existsById(trainer.getName())) {
            return this.trainerRepository.save(trainer);
        } else {
            throw new IllegalArgumentException("Trainer don't exist or already deleted");
        }
    }

    @Override
    public Trainer removeTrainer(String name) {
        if (this.trainerRepository.existsById(name)) {
            Trainer trainer = this.getTrainer(name);
            this.trainerRepository.delete(trainer);
            return trainer;
        } else {
            throw new IllegalArgumentException("Trainer don't exist or already deleted");
        }
    }
}
