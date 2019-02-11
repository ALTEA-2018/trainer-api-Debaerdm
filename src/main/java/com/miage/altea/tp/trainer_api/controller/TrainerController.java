package com.miage.altea.tp.trainer_api.controller;

import com.miage.altea.tp.trainer_api.bo.Trainer;
import com.miage.altea.tp.trainer_api.service.TrainerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trainers")
public class TrainerController {
    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService){
        this.trainerService = trainerService;
    }

    @GetMapping(value = "/")
    public Iterable<Trainer> getAllTrainers(){
        return this.trainerService.getAllTrainers();
    }

    @GetMapping(value = "/{name}", produces="application/json")
    public Trainer getTrainer(@PathVariable("name") String name){
        return this.trainerService.getTrainer(name);
    }

    @PostMapping(value = "/", produces="application/json", consumes="application/json")
    public ResponseEntity addTrainer(@RequestBody Trainer trainer) {
        try {
            return ResponseEntity.ok(this.trainerService.createTrainer(trainer));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping(value = "/", produces="application/json", consumes="application/json")
    public ResponseEntity updateTrainer(@RequestBody Trainer trainer) {
        try {
            return ResponseEntity.ok(this.trainerService.updateTrainer(trainer));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{name}", produces="application/json")
    public ResponseEntity deleteTrainer(@PathVariable("name") String name){
        try {
            return ResponseEntity.ok(this.trainerService.removeTrainer(name));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
