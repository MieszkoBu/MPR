package com.example.Lab2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    Car findByMarka(String marka);
    Optional<Car> findById(long id);
    List<Car> findAll();
}
