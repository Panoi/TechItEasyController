package com.example.TechItEasyController.Controllers;


import com.example.TechItEasyController.Exceptions.InvalidTelevisionNameException;
import com.example.TechItEasyController.Exceptions.RecordNotFoundException;
import com.example.TechItEasyController.models.Television;
import com.example.TechItEasyController.repositories.TelevisionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private final TelevisionRepository repos;

    public TelevisionController(TelevisionRepository repos) {
        this.repos = repos;
    }


    @PostMapping
    public ResponseEntity<Television> postTelevision(@RequestBody Television television) {
        this.repos.save(television);
        if (television.getName().length() > 20 || television.getName().length() < 3) {
            throw new InvalidTelevisionNameException(television.getName());
        }
        return new ResponseEntity<>(television, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevisionsById(@PathVariable long id) {
        Optional<Television> op = this.repos.findById(id);
        if (op.isEmpty()) {
            throw new RecordNotFoundException("Television id doesn't exist");
        }
        return new ResponseEntity<>(op.get(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Television>> getAllTelevisions() {
        return new ResponseEntity<>(this.repos.findAll(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Television> putTelevisionById(@PathVariable long id, @RequestBody Television television) {
        Optional<Television> op = this.repos.findById(id);
        if (television.getName().length() > 20 || television.getName().length() < 3) {
            throw new InvalidTelevisionNameException(television.getName());
        }
        if (op.isPresent()) {
            Television existingTelevision = op.get();
            existingTelevision.setBrand(television.getBrand());
            existingTelevision.setPrice(television.getPrice());
            existingTelevision.setName(television.getName());
            existingTelevision.setAmbiLight(television.isAmbiLight());
            existingTelevision.setBluetooth(television.isBluetooth());

            repos.save(existingTelevision);
            return ResponseEntity.ok(existingTelevision);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Television> deleteTelevisionById(@PathVariable long id) {
        Optional<Television> op = this.repos.findById(id);
        if (op.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else return ResponseEntity.ok(this.repos.deleteById(id));
    }

    @GetMapping("/salesday/{id}")
    public ResponseEntity<List<Television>> getDateOfSold(@PathVariable long id, @RequestParam LocalDate date) {
        Optional<Television> op = this.repos.findById(id);
        if (op.isEmpty()) {
            throw new RecordNotFoundException("id  doesn't exist");
        } else return ResponseEntity.ok(this.repos.findBySold(date));
        }
    }