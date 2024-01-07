package com.example.Lab2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyRestService{
    private CarRepository carRepository;

    public MyRestService(CarRepository carRepository){
        this.carRepository = carRepository;
        this.carRepository.save(new Car("a","v",2009));
        this.carRepository.save(new Car("b","v",2003));
        this.carRepository.save(new Car("c","s",2004));
        this.carRepository.save(new Car("dua","d",2009));
        this.carRepository.save(new Car("dua","df",2008));
    }
    public Optional<Car> getCarRepository(String marka) {
        Optional<Car> CarRepo = Optional.ofNullable(this.carRepository.findByMarka(marka));
        if(CarRepo.isPresent()){
            return CarRepo;
        }else{
            System.out.println("Nie ma takiego samochodu");
            throw new CarExceptionHandler.CarNotFoundException();
        }
    }
    public List<Car> cars(){
        List<Car> car = new ArrayList<>();
        car.addAll(carRepository.findAll());
        return car;
    }
    public void save(Car car){
        Optional<Car> existingCar = carRepository.findById(car.getId());
        if (existingCar.isPresent()) {
            throw new CarExceptionHandler.CarAlreadyExistExeption();
        }
        carRepository.save(car);
    }
    public void edit(Car car){
        Optional<Car> notExistingCar = carRepository.findById(car.getId());
        if(notExistingCar.isEmpty()){
            throw new CarExceptionHandler.CarNotFoundException();
        }
        carRepository.save(car);
    }
    void usun(long id){
        Optional<Car> CarRepo = this.carRepository.findById(id);
        if(CarRepo.isPresent()){
            this.carRepository.deleteById(id);
        }else{
            System.out.println("Nie ma takiego samochodu");
            throw new CarExceptionHandler.CarNotFoundException();
        }
    }
    public Optional<Car> findByID(long id){
        Optional<Car> CarRepo = this.carRepository.findById(id);
        if(CarRepo.isPresent()){
            return CarRepo;
        }else{
            System.out.println("Nie ma takiego samochodu");
            throw new CarExceptionHandler.CarNotFoundException();
        }
    }
}
