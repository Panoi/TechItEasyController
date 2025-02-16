package com.example.TechItEasyController.repositories;

import com.example.TechItEasyController.models.Television;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TelevisionRepository extends JpaRepository<Television, Long> {

    Television deleteById(long id);

    Television findByBrand(String brand);
//Sold moet overeen komen met je variabel (data) in television
    List<Television> findBySold(LocalDate date);
}
