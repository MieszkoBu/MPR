package com.example.Lab2;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    Car findByMarka(String marka);
    Car findById(long id);
    List<Car> findAll();
}
