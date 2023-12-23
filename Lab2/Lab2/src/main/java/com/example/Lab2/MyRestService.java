package com.example.Lab2;

import ch.qos.logback.core.rolling.helper.MonoTypedConverter;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import javax.management.monitor.MonitorNotification;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyRestService{
    public static final String API_URL = "http://localhost:8082";
    RestClient restClient;
    public MyRestService(){
        restClient = RestClient.builder().build();
    }
    public Iterable<Car> findAll(){
        List<Car> cars = restClient
                .get()
                .uri(API_URL + "/cars")
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
        return  cars;
    }
    public Optional<Car> findByID(long id) {
        try {
            Car car = restClient
                    .get()
                    .uri(API_URL + "/findbyId/" + id)
                    .retrieve()
                    .body(Car.class);
            return Optional.ofNullable(car);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new CarExceptionHandler.CarNotFoundException();
            }
            throw ex;
        }
    }
    void saveCar(Car car){
            ResponseEntity<Void> response = restClient
                    .post()
                    .uri(API_URL + "/Car/add")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(car)
                    .retrieve()
                    .toBodilessEntity();

    }
    public void editCar(long id, Car updatedCar) {
        try {
            restClient
                    .put()
                    .uri(API_URL + "/car/edit/" + id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(updatedCar)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new CarExceptionHandler.CarNotFoundException();
            }
            throw ex;
        }
    }
    public void deleteCar(long id) {
        try {
            restClient
                    .delete()
                    .uri(API_URL + "/ucar/" + id)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new CarExceptionHandler.CarNotFoundException();
            }
            throw ex;
        }
    }
}
