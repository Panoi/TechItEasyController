package com.example.TechItEasyController.Controllers;


import com.example.TechItEasyController.Exceptions.InvalidTelevisionNameException;
import com.example.TechItEasyController.Exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/televisions")
public class TelevisionController {

    private List<televisionDataBase> televisionDataBases = new ArrayList<>();


    @PostMapping
    public ResponseEntity<televisionDataBase> postTelevision(@RequestBody televisionDataBase televisionDataBase) {
        if (televisionDataBase.getName().length() > 20 || televisionDataBase.getName().length() < 3) {
            throw new InvalidTelevisionNameException(televisionDataBase.getName());
        }
        this.televisionDataBases.add(televisionDataBase);
        return new ResponseEntity<>(televisionDataBase, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<televisionDataBase> getTelevisionsById(@PathVariable int id) {
        if (id >= televisionDataBases.size() || id < 0) {
            throw new RecordNotFoundException("Television id out of bounds");
        }
        return new ResponseEntity<>(this.televisionDataBases.get(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<televisionDataBase>> getAllTelevisions() {
        return new ResponseEntity<>(this.televisionDataBases, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<televisionDataBase> putTelevisionById(@PathVariable int id, @RequestBody televisionDataBase televisionDataBase) {
        if (televisionDataBase.getName().length() > 20 || televisionDataBase.getName().length() < 3) {
            throw new InvalidTelevisionNameException(televisionDataBase.getName());
        }
        televisionDataBase existingTelevisionDataBase = televisionDataBases.get(id);
        existingTelevisionDataBase.setBrand(televisionDataBase.getBrand());
        existingTelevisionDataBase.setPrice(televisionDataBase.getPrice());
        existingTelevisionDataBase.setName(televisionDataBase.getName());
        return ResponseEntity.ok(existingTelevisionDataBase);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<televisionDataBase> deleteTelevisionById(@PathVariable int id) {
        if (id < 0 || id >= televisionDataBases.size()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else return ResponseEntity.ok(this.televisionDataBases.remove(id));
    }
}