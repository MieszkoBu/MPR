package com.example.Lab2;

import ch.qos.logback.core.rolling.helper.MonoTypedConverter;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import javax.management.monitor.MonitorNotification;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyRestService{
    public static final String API_URL = "http://localhost:8080";
    RestClient restClient;
    public MyRestService(){
        restClient = RestClient.builder().build();
    }

//    public Optional<Car> getCarRepository(String marka) {
//        Optional<Car> CarRepo = Optional.ofNullable(this.carRepository.findByMarka(marka));
//        if(CarRepo.isPresent()){
//            return CarRepo;
//        }else{
//            System.out.println("Nie ma takiego samochodu");
//            throw new CarExceptionHandler.CarNotFoundException();
//        }
//    }
//    public void addCarRepository(Car car){
//        this.carRepository.save(car);
//    }
    public Iterable<Car> findAll(){
        List<Car> cars = restClient
                .get()
                .uri(API_URL + "/cars")
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
        return  cars;
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
    void usun(long id){
//        Optional<Car> CarRepo = Optional.ofNullable(this.carRepository.findById(id));
//        if(CarRepo.isPresent()){
//            this.carRepository.deleteById(id);
//        }else{
//            System.out.println("Nie ma takiego samochodu");
//            throw new CarExceptionHandler.CarNotFoundException();
//        }
        ResponseEntity<Void> response = restClient
                .delete()
                .uri(API_URL + "/ucar/{id}")

    }
//    public Optional<Car> findByID(long id){
//        Optional<Car> CarRepo = Optional.ofNullable(this.carRepository.findById(id));
//        if(CarRepo.isPresent()){
//            return CarRepo;
//        }else{
//            System.out.println("Nie ma takiego samochodu");
//            throw new CarExceptionHandler.CarNotFoundException();
//        }
//    }
}
